package com.example.awaq1.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.awaq1.MainActivity
import com.example.awaq1.data.formularios.FormularioCincoEntity
import com.example.awaq1.data.formularios.FormularioCuatroEntity
import com.example.awaq1.data.formularios.FormularioDosEntity
import com.example.awaq1.data.formularios.FormularioSeisEntity
import com.example.awaq1.data.formularios.FormularioSieteEntity
import com.example.awaq1.data.formularios.FormularioTresEntity
import com.example.awaq1.data.formularios.FormularioUnoEntity
import com.example.awaq1.data.formularios.Ubicacion
import com.example.awaq1.navigator.FormCincoID
import com.example.awaq1.navigator.FormCuatroID
import com.example.awaq1.navigator.FormDosID
import com.example.awaq1.navigator.FormSeisID
import com.example.awaq1.navigator.FormSieteID
import com.example.awaq1.navigator.FormTresID
import com.example.awaq1.navigator.FormUnoID
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController


@Composable
fun Home(navController: NavController) {
    val context = LocalContext.current as MainActivity
    var location by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    val ubicacion = Ubicacion(context)
    if(location == null){
        LaunchedEffect(Unit) {
            context.requestLocationPermission()
            if (ubicacion.hasLocationPermission()) {
                location = ubicacion.obtenerCoordenadas()
                if (location != null) {
                    Log.d("LogIn", "Location retrieved: Lat=${location!!.first}, Long=${location!!.second}")
                } else {
                    Log.d("LogIn", "Location is null")
                }
            } else {
                Log.d("ObservationForm", "Location permission required but not granted.")
            }
        }
    }
    //Username para saludo
    val nombre = context.accountInfo.username.substringBefore("@")

    val appContainer = context.container
    val forms1: List<FormularioUnoEntity> by appContainer.usuariosRepository.getAllFormularioUnoForUserID(
        context.accountInfo.user_id
    )
        .collectAsState(initial = emptyList())
    val forms2: List<FormularioDosEntity> by appContainer.usuariosRepository.getAllFormularioDosForUserID(
        context.accountInfo.user_id
    )
        .collectAsState(initial = emptyList())
    val forms3: List<FormularioTresEntity> by appContainer.usuariosRepository.getAllFormularioTresForUserID(
        context.accountInfo.user_id
    )
        .collectAsState(initial = emptyList())
    val forms4: List<FormularioCuatroEntity> by appContainer.usuariosRepository.getAllFormularioCuatroForUserID(
        context.accountInfo.user_id
    )
        .collectAsState(initial = emptyList())
    val forms5: List<FormularioCincoEntity> by appContainer.usuariosRepository.getAllFormularioCincoForUserID(
        context.accountInfo.user_id
    )
        .collectAsState(initial = emptyList())
    val forms6: List<FormularioSeisEntity> by appContainer.usuariosRepository.getAllFormularioSeisForUserID(
        context.accountInfo.user_id
    )
        .collectAsState(initial = emptyList())
    val forms7: List<FormularioSieteEntity> by appContainer.usuariosRepository.getAllFormularioSieteForUserID(
        context.accountInfo.user_id
    )
        .collectAsState(initial = emptyList())
    val count by appContainer.formulariosRepository.getAllFormulariosCount()
        .collectAsState(initial = 0)

    Scaffold(
        bottomBar = {
            Column() {
                BottomNavigationBar(navController) // agregar una top bar de ser posible para poner el perfil
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFFFFF))
                    .padding(paddingValues)
            ) {
                // Header
                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(bottomStart = 2000.dp, bottomEnd = 2000.dp))
                            .background(Color(0xFFCDE4B4)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            var fontSize by remember { mutableStateOf(50.sp) }

                            Text(
                                text = "Hola",
                                fontSize = fontSize,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF4E7029),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                text = "$nombre!",
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF4E7029),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }

                    // Boton de agregar reporte
                    IconButton(
                        onClick = {
                            navController.navigate("elegir_reporte") {
                                popUpTo("home") { inclusive = false }
                            }
                        },
                        modifier = Modifier
                            .size(80.dp)
                            .offset(y = 40.dp)
                            //.padding(vertical = 10.dp)
                            .background(Color(0xFF4CAF50), CircleShape) // Green background circle
                            .align(Alignment.BottomCenter)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Reporte",
                            tint = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(28.dp))
                // Dashboard Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Tablero",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333),
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Stats Row
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        StatsColumn(label = "Total", count = count, color = Color.Black)
                    }

                    // Forms Grid
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        reverseLayout = true,
                        modifier = Modifier
                            .padding(horizontal = 0.dp, vertical = 8.dp)
                            .fillMaxWidth()
                    ) {
                        items(count = 1) {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        items(forms1) { form ->
                            val formCard = FormInfo(form)
                            formCard.DisplayCard(navController)
                        }
                        items(forms2) { form ->
                            val formCard = FormInfo(form)
                            formCard.DisplayCard(navController)
                        }
                        items(forms3) { form ->
                            val formCard = FormInfo(form)
                            formCard.DisplayCard(navController)
                        }
                        items(forms4) { form ->
                            val formCard = FormInfo(form)
                            formCard.DisplayCard(navController)
                        }
                        items(forms5) { form ->
                            val formCard = FormInfo(form)
                            formCard.DisplayCard(navController)
                        }
                        items(forms6) { form ->
                            val formCard = FormInfo(form)
                            formCard.DisplayCard(navController)
                        }
                        items(forms7) { form ->
                            val formCard = FormInfo(form)
                            formCard.DisplayCard(navController)
                        }

                    }
                }
            }
        }
    )
}

@Composable
fun StatsColumn(label: String, count: Int, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "$count",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = label,
            fontSize = 24.sp,
            color = Color.Gray
        )
    }
}

// Se ve algo así
// +---------------+
// | tipo:valorId  |
// | pTag: pCont   |
// | sTag: sCont   |
// +---------------+

data class FormInfo(
    val tipo: String, // Descripcion del tipo de formulario (una sola palabra)
    val valorIdentificador: String, // Valor que se muestra junto tipo
    val primerTag: String, // Tag del primer valor a mostrar como preview del formulario
    val primerContenido: String, // El valor a mostrar junto al primer tag
    val segundoTag: String,
    val segundoContenido: String,

    val formulario: String, // Indicador de tipo de formulario, para luego acceder
    val formId: Long,
    val fechaCreacion: String,
    val fechaEdicion: String,
    val completo: Boolean
) {
    constructor(formulario: FormularioUnoEntity) : this(
        tipo = "Transecto", formulario.transecto,
        primerTag = "Tipo", formulario.tipoAnimal,
        segundoTag = "Nombre", formulario.nombreComun,
        formulario = "form1",
        formId = formulario.id,
        fechaCreacion = formulario.fecha,
        fechaEdicion = formulario.editado,
        completo = formulario.esCompleto()
    )

    constructor(formulario: FormularioDosEntity) : this(
        tipo = "Zona", formulario.zona,
        primerTag = "Tipo", formulario.tipoAnimal,
        segundoTag = "Nombre", formulario.nombreComun,
        formulario = "form2",
        formId = formulario.id,
        fechaCreacion = formulario.fecha,
        fechaEdicion = formulario.editado,
        completo = formulario.esCompleto()
    )

    constructor(formulario: FormularioTresEntity) : this(
        tipo = "Código", formulario.codigo,
        primerTag = "Seguimiento", siONo(formulario.seguimiento),
        segundoTag = "Cambio", siONo(formulario.cambio),
        formulario = "form3",
        formId = formulario.id,
        fechaCreacion = formulario.fecha,
        fechaEdicion = formulario.editado,
        completo = formulario.esCompleto()
    )

    constructor(formulario: FormularioCuatroEntity) : this(
        tipo = "Código", formulario.codigo,
        primerTag = "Cuad. A", formulario.quad_a,
        segundoTag = "Cuad. B", formulario.quad_b,
        formulario = "form4",
        formId = formulario.id,
        fechaCreacion = formulario.fecha,
        fechaEdicion = formulario.editado,
        completo = formulario.esCompleto()
    )

    constructor(formulario: FormularioCincoEntity) : this(
        tipo = "Zona", formulario.zona,
        primerTag = "Tipo", formulario.tipoAnimal,
        segundoTag = "Nombre", formulario.nombreComun,
        formulario = "form5",
        formId = formulario.id,
        fechaCreacion = formulario.fecha,
        fechaEdicion = formulario.editado,
        completo = formulario.esCompleto()
    )

    constructor(formulario: FormularioSeisEntity) : this(
        tipo = "Codigo", formulario.codigo,
        primerTag = "Zona", formulario.zona,
        segundoTag = "PlacaCamara", formulario.placaCamara,
        formulario = "form6",
        formId = formulario.id,
        fechaCreacion = formulario.fecha,
        fechaEdicion = formulario.editado,
        completo = formulario.esCompleto()
    )

    constructor(formulario: FormularioSieteEntity) : this(
        tipo = "Zona", formulario.zona,
        primerTag = "Pluviosidad", formulario.pluviosidad,
        segundoTag = "TempMax", formulario.temperaturaMaxima,
        formulario = "form7",
        formId = formulario.id,
        fechaCreacion = formulario.fecha,
        fechaEdicion = formulario.editado,
        completo = formulario.esCompleto()
    )

    fun goEditFormulario(navController: NavController) {
        Log.d("HOME_CLICK_ACTION", "Click en $this")
        when (formulario) {
            "form1" -> navController.navigate(route = FormUnoID(formId))
            "form2" -> navController.navigate(route = FormDosID(formId))
            "form3" -> navController.navigate(route = FormTresID(formId))
            "form4" -> navController.navigate(route = FormCuatroID(formId))
            "form5" -> navController.navigate(route = FormCincoID(formId))
            "form6" -> navController.navigate(route = FormSeisID(formId))
            "form7" -> navController.navigate(route = FormSieteID(formId))
            else -> throw Exception("CARD NAVIGATION NOT IMPLEMENTED FOR $formulario")
        }
    }

    @Composable
    fun DisplayCard(navController: NavController, modifier: Modifier = Modifier) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding()
                .clickable { this.goEditFormulario(navController) },
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .padding(end = 40.dp),
                Arrangement.SpaceBetween

            ) {
                Column {

                    Text(
                        text = "$tipo: $valorIdentificador",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black

                    )

                    Row {
                        if (completo) {
                            Icon(
                                imageVector = Icons.Rounded.CheckCircle,
                                contentDescription = null,
                                tint = Color.Green
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Rounded.Warning,
                                contentDescription = null,
                                tint = Color(237, 145, 33)
                            )
                        }

                        Spacer(modifier = Modifier.size(10.dp, 10.dp))
                        Text("Creado: $fechaCreacion")
                    }
                }
                Column {
                    Text(
                        text = "$primerTag: $primerContenido",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )

                    Text(
                        text = "$segundoTag: $segundoContenido",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

private fun siONo(boolean: Boolean): String = if (boolean) "Sí" else "No"
