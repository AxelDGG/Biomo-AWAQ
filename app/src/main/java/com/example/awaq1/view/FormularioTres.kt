package com.example.awaq1.view

import android.net.Uri
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.awaq1.MainActivity
import kotlinx.coroutines.runBlocking
import androidx.compose.material.icons.Icons
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.IconToggleButton
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.awaq1.R
import com.example.awaq1.ViewModels.CameraViewModel
import com.example.awaq1.data.formularios.FormularioTresEntity
import com.example.awaq1.data.formularios.ImageEntity
import com.example.awaq1.data.formularios.Ubicacion
import com.example.awaq1.data.formularios.local.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewForm3() {
    ObservationFormTres(rememberNavController())
}

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ObservationFormTres(navController: NavController, formularioId: Long = 0L) {
    val activity = LocalContext.current as? MainActivity ?: return
    val appContainer = activity.container
    val tokenManager = TokenManager(activity)
    val userId by tokenManager.userId.collectAsState(initial = null)

    var location by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    val ubicacion = Ubicacion(activity)
    val scope = rememberCoroutineScope()

    val cameraViewModel: CameraViewModel = viewModel()

    var codigo by remember { mutableStateOf("") }
    var clima by remember { mutableStateOf("") }
    var temporada by remember { mutableStateOf("Verano/Seca") }
    var seguimiento by remember { mutableStateOf(true) }
    var cambio by remember { mutableStateOf(true) }
    var cobertura: String by remember { mutableStateOf("") }
    var tipoCultivo: String by remember { mutableStateOf("") }
    var disturbio: String by remember { mutableStateOf("") }
    var observaciones: String by remember { mutableStateOf("") }
    var showCamera by remember { mutableStateOf(false) }
    val savedImageUris = remember { mutableStateOf(mutableListOf<Uri>()) }
    var fecha by remember { mutableStateOf("") }
    var editado by remember { mutableStateOf("") }
    var ubicaciontxt by remember { mutableStateOf("") }

    LaunchedEffect(formularioId) {
        if (formularioId != 0L) {
            val formulario = appContainer.formulariosRepository
                .getFormularioTresStream(formularioId).first()

            if (formulario != null) {
                codigo = formulario.codigo
                clima = formulario.clima
                temporada = formulario.temporada
                seguimiento = formulario.seguimiento
                cambio = formulario.cambio
                cobertura = formulario.cobertura
                tipoCultivo = formulario.tipoCultivo
                disturbio = formulario.disturbio
                observaciones = formulario.observaciones
                fecha = formulario.fecha
                editado = formulario.editado
                location = formulario.latitude?.let { lat ->
                    formulario.longitude?.let { lon -> lat to lon }
                }
                val storedImages = appContainer.formulariosRepository
                    .getImagesByFormulario(formularioId, "Formulario3")
                    .first()
                savedImageUris.value = storedImages.mapNotNull {
                    runCatching { Uri.parse(it.imageUri) }.getOrNull()
                }.toMutableList()
            } else {
                Log.e("Formulario1Loading", "No se pudo obtener formulario1 id=$formularioId")
            }
        }
    }

    if(location == null){
        LaunchedEffect(Unit) {
            activity.requestLocationPermission()
            if (ubicacion.hasLocationPermission()) {
                location = ubicacion.obtenerCoordenadas()
                if (location != null) {
                    location?.let { Log.d("ObservationForm", "Location retrieved: Lat=${it.first}, Long=${it.second}") }
                } else {
                    Log.d("ObservationForm", "Location is null")
                }
            } else {
                Log.d("ObservationForm", "Location permission required but not granted.")
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Formulario de Observación",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = (-0.03).em,
                            color = Color(0xFF4E7029)
                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color(0xFF4E7029)
                )
            )
        },
        content = { paddingValues ->
            if (showCamera) {
                CameraWindow(
                    activity = activity,
                    cameraViewModel = cameraViewModel,
                    savedImageUris = savedImageUris, // Pass state
                    onClose = { showCamera = false },
                    onGalleryClick = { /* Optional: Handle gallery selection */ }
                )
            }  else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .padding(16.dp)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        location?.let { (latitude, longitude) ->
                            Text("Ubicacion Actual: Lati: $latitude, Long: $longitude")
                        } ?: Text("Buscando ubicacion...")

                        OutlinedTextField(
                            value = ubicaciontxt,
                            onValueChange = {},
                            label = { Text("Ubicación Actual") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = codigo,
                            onValueChange = { codigo = it },
                            label = { Text("Código") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text("Estado del Tiempo:")
                        FlowRow (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            maxItemsInEachRow = 3
                            //verticalAlignment = Alignment.CenterVertically
                        ) {
                            val weatherOptions = listOf("Soleado", "Parcialmente Nublado", "Lluvioso")
                            val weatherIcons = listOf(
                                R.drawable.sunny, // Add sunny icon in your drawable resources
                                R.drawable.cloudy, // Add partly cloudy icon in your drawable resources
                                R.drawable.rainy // Add rainy icon in your drawable resources
                            )

                            weatherOptions.forEachIndexed { index, option ->
                                IconToggleButton(
                                    checked = clima == option,
                                    onCheckedChange = { clima = option },
                                    modifier = Modifier.size(150.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .border(
                                                width = 2.dp,
                                                color = if (clima == option) Color(0xFF4E7029) else Color.Transparent,
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                            .padding(8.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = weatherIcons[index]),
                                            contentDescription = option,
                                            modifier = Modifier.requiredSize(95.dp)
                                        )
                                    }
                                }
                            }
                        }
                        Text("Época")
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val seasonOptions = listOf("Verano/Seca", "Invierno/Lluviosa")
                            seasonOptions.forEach { option ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = temporada == option,
                                        onClick = { temporada = option },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = Color(0xFF4E7029),
                                            unselectedColor = Color.Gray
                                        )
                                    )
                                    Text(option, modifier = Modifier.padding(start = 8.dp))
                                }
                            }
                        }
                        // SI o NO
                        Text("Seguimiento")
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = seguimiento,
                                    onClick = { seguimiento = true },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color(0xFF4E7029),
                                        unselectedColor = Color.Gray
                                    )
                                )
                                Text("Sí", modifier = Modifier.padding(start = 8.dp))
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = !seguimiento,
                                    onClick = { seguimiento = false },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color(0xFF4E7029),
                                        unselectedColor = Color.Gray
                                    )
                                )
                                Text("No", modifier = Modifier.padding(start = 8.dp))
                            }
                        }

                        // SI o NO
                        Text("Cambió")
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = cambio,
                                    onClick = { cambio = true },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color(0xFF4E7029),
                                        unselectedColor = Color.Gray
                                    )
                                )
                                Text("Sí", modifier = Modifier.padding(start = 8.dp))
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = !cambio,
                                    onClick = { cambio = false },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color(0xFF4E7029),
                                        unselectedColor = Color.Gray
                                    )
                                )
                                Text("No", modifier = Modifier.padding(start = 8.dp))
                            }
                        }

                        Text("Cobertura")
                        val coberturaOptions =
                            listOf("BD", "RA", "RB", "PA", "PL", "CP", "CT", "VH", "TD", "IF")
                        if (cobertura == "") {
                            cobertura = coberturaOptions[0]
                        }
                        Column {
                            coberturaOptions.forEach { option ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = cobertura == option,
                                        onClick = { cobertura = option },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = Color(0xFF4E7029),
                                            unselectedColor = Color.Gray
                                        )
                                    )
                                    Text(option, modifier = Modifier.padding(start = 8.dp))
                                }
                            }
                        }

                        OutlinedTextField(
                            value = tipoCultivo,
                            onValueChange = { tipoCultivo = it },
                            label = { Text("Tipos de cultivo") },
                            // Es un numero lo de esta entrada?
                            // keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            // TODO: Aclarar, cambiar schema si es necesario
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text("Disturbio")
                        val disturbioOptions = listOf(
                            "Inundación",
                            "Quema",
                            "Tala",
                            "Erosión",
                            "Minería",
                            "Carretera",
                            "Más plantas acuáticas",
                            "Otro"
                        )
                        if (disturbio == "") {
                            disturbio = disturbioOptions[0]
                        }
                        Column {
                            disturbioOptions.forEach { option ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = disturbio == option,
                                        onClick = { disturbio = option },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = Color(0xFF4E7029),
                                            unselectedColor = Color.Gray
                                        )
                                    )
                                    Text(option, modifier = Modifier.padding(start = 8.dp))
                                }
                            }
                        }


                        // Camera Button
                        Button(
                            onClick = { showCamera = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4E7029),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Take Photo",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Tomar Foto")
                        }

                        // Log.d("ObservationForm", "savedImageUri: ${savedImageUri.value}")

                        // Display the saved image
                        savedImageUris.value.forEach { uri ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(model = uri),
                                    contentDescription = "Saved Image",
                                    modifier = Modifier.size(100.dp)
                                )
                                Button(onClick = {
                                    savedImageUris.value = savedImageUris.value.toMutableList().apply { remove(uri) }
                                },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent // Removes background color
                                    ),
                                    elevation = null // Removes shadow/elevation for a completely flat button
                                ) {
                                    Text(text = "X",
                                        color = Color.Red,
                                        fontSize = 20.sp)
                                }
                            }
                        }

                        // Observaciones
                        OutlinedTextField(
                            value = observaciones,
                            onValueChange = { observaciones = it },
                            label = { Text("Observaciones") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            maxLines = 4
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = { navController.navigate("elegir_reporte") },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF4E7029),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(50),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    "Atras",
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }

                            Button(
                                onClick = {
                                    if (fecha.isNullOrEmpty()) {
                                        fecha = getCurrentDate()
                                    }
                                    editado = getCurrentDate()
                                    val formulario = FormularioTresEntity(
                                        codigo = codigo,
                                        clima = clima,
                                        temporada = temporada,
                                        seguimiento = seguimiento,
                                        cambio = cambio,
                                        cobertura = cobertura,
                                        tipoCultivo = tipoCultivo,
                                        disturbio = disturbio,
                                        observaciones = observaciones,
                                        latitude = location?.first,
                                        longitude = location?.second,
                                        fecha = fecha,
                                        editado = editado
                                    ).withID(formularioId)

                                    val currentUserId = userId

                                    if (currentUserId != null) {
                                        scope.launch(Dispatchers.IO) {
                                            try {
                                                val formId = appContainer.usuariosRepository.insertUserWithFormularioTres(
                                                    currentUserId.toLong(),
                                                    formulario
                                            )
                                                appContainer.formulariosRepository.deleteImagesByFormulario(
                                                    formularioId = formId,
                                                    formularioType = "Formulario3",
                                                )

                                                savedImageUris.value.forEach { uri ->
                                                    appContainer.formulariosRepository.insertImage(
                                                        ImageEntity(
                                                            formularioId = formId,
                                                            formularioType = "Formulario3",
                                                            imageUri = uri.toString()
                                                        )
                                                    )
                                                }

                                                withContext(Dispatchers.Main) {
                                                    navController.navigate("home")
                                                }
                                            } catch (t: Throwable) {
                                                Log.e("Formulario3", "Error guardando formulario", t)
                                                withContext(Dispatchers.Main) {
                                                }
                                            }
                                        }
                                        navController.navigate("home")
                                    } else {
                                        Log.e("Formulario3", "No se pudo enviar: userId nulo")
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF4E7029),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(50),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    "Enviar",
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}
