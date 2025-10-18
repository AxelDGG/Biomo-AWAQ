package com.example.awaq1.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
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
import com.example.awaq1.data.formularios.local.TokenManager
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlin.collections.emptyList


@Composable
fun Home(navController: NavController) {
    val context = LocalContext.current
    val mainActivity = context as? MainActivity
    var location by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    val ubicacion = Ubicacion(context)
    if(location == null){
        LaunchedEffect(Unit) {
            mainActivity?.requestLocationPermission()
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

    // Obtenemos el ID de usuario desde el TokenManager
    val tokenManager = remember { TokenManager(context) }
    val userId by tokenManager.userId.collectAsState(initial = null)

    // Usamos un texto temporal para el nombre.
    val nombre = "usuario"

    val appContainer = (context as MainActivity).container
    val usuariosRepository = appContainer.usuariosRepository
    val userIdLong = userId?.toLongOrNull()

    // Creamos Flows vacios para cuando el userId aun no esta disponible
    val emptyFlow1 = remember { flowOf(emptyList<FormularioUnoEntity>()) }
    val emptyFlow2 = remember { flowOf(emptyList<FormularioDosEntity>()) }
    val emptyFlow3 = remember { flowOf(emptyList<FormularioTresEntity>()) }
    val emptyFlow4 = remember { flowOf(emptyList<FormularioCuatroEntity>()) }
    val emptyFlow5 = remember { flowOf(emptyList<FormularioCincoEntity>()) }
    val emptyFlow6 = remember { flowOf(emptyList<FormularioSeisEntity>()) }
    val emptyFlow7 = remember { flowOf(emptyList<FormularioSieteEntity>()) }


    val forms1: List<FormularioUnoEntity> by (if (userIdLong != null) usuariosRepository.getAllFormularioUnoForUserID(userIdLong) else emptyFlow1)
        .collectAsState(initial = emptyList())
    val forms2: List<FormularioDosEntity> by (if (userIdLong != null) usuariosRepository.getAllFormularioDosForUserID(userIdLong) else emptyFlow2)
        .collectAsState(initial = emptyList())
    val forms3: List<FormularioTresEntity> by (if (userIdLong != null) usuariosRepository.getAllFormularioTresForUserID(userIdLong) else emptyFlow3)
        .collectAsState(initial = emptyList())
    val forms4: List<FormularioCuatroEntity> by (if (userIdLong != null) usuariosRepository.getAllFormularioCuatroForUserID(userIdLong) else emptyFlow4)
        .collectAsState(initial = emptyList())
    val forms5: List<FormularioCincoEntity> by (if (userIdLong != null) usuariosRepository.getAllFormularioCincoForUserID(userIdLong) else emptyFlow5)
        .collectAsState(initial = emptyList())
    val forms6: List<FormularioSeisEntity> by (if (userIdLong != null) usuariosRepository.getAllFormularioSeisForUserID(userIdLong) else emptyFlow6)
        .collectAsState(initial = emptyList())
    val forms7: List<FormularioSieteEntity> by (if (userIdLong != null) usuariosRepository.getAllFormularioSieteForUserID(userIdLong) else emptyFlow7)
        .collectAsState(initial = emptyList())

    val count = forms1.size + forms2.size + forms3.size + forms4.size +
            forms5.size + forms6.size + forms7.size

    val incompleteCount = forms1.count { !it.esCompleto() } + forms2.count { !it.esCompleto() } +
            forms3.count { !it.esCompleto() } + forms4.count { !it.esCompleto() } +
            forms5.count { !it.esCompleto() } + forms6.count { !it.esCompleto() } +
            forms7.count { !it.esCompleto() }

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
                            .background(Color(0xFF4E7029), CircleShape) // Green background circle
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

                    Spacer(modifier = Modifier.height(50.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        CircularDeterminateIndicator(count = count, incompleteCount = incompleteCount)
                    }

                    Spacer(modifier = Modifier.height(80.dp))

                    // Stats Row
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                       Spacer(modifier = Modifier.width(50.dp))
                        Box(modifier = Modifier.padding( start = 50.dp, end = 83.dp, top = 30.dp, bottom = 30.dp)) {
                            StatsColumn(label = "Total", count = count, color = Color.Gray)
                        }
                        Box(modifier = Modifier.padding(start = 20.dp, end = 30.dp, bottom = 30.dp, top = 30.dp)) {
                            StatsColumn(label = "Incompletos", count = incompleteCount, color = Color.Gray
                            )
                        }
                        Box(modifier = Modifier.padding(  start = 60.dp, end = 60.dp, top = 30.dp, bottom = 30.dp)) {
                            StatsColumn(label = "Guardados", count = count - incompleteCount, color = Color.Gray
                            )

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
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = label,
            fontSize = 28.sp,
            color = color
        )
    }
}

@Composable
fun CircularDeterminateIndicator(count: Int, incompleteCount: Int){

    val progreso  = if (count > 0) (count - incompleteCount) / count.toFloat() else 0f
    val porcentaje = (progreso * 100).toInt()
    Box(
        contentAlignment = Alignment.Center
        ) {
        CircularProgressIndicator(
                progress = { progreso },
                modifier = Modifier.size(400.dp),
                color = Color(0xFF4E7029),
                strokeWidth = 30.dp,
                trackColor = Color.LightGray
            )
            Text(
                text = "$porcentaje%",
                color = Color(0xFF4E7029),
                fontSize = 50.sp,
                fontWeight = FontWeight.W900)
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
    val primerContenido: String
)
