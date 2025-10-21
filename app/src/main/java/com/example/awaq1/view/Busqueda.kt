package com.example.awaq1.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.awaq1.MainActivity
import com.example.awaq1.data.AppContainer
import com.example.awaq1.data.formularios.*
import com.example.awaq1.data.formularios.local.TokenManager
import com.example.awaq1.navigator.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Busqueda(navController: NavController) {
    val context = LocalContext.current as MainActivity

    // TokenManager y contenedor de dependencias
    val tokenManager = remember { TokenManager(context) }
    val appContainer = (LocalContext.current as? MainActivity)?.container ?: run {
        Log.e("Busqueda", "No se pudo obtener AppContainer desde el Context")
        return
    }

    val scope = rememberCoroutineScope()

    // userId proveniente del token (puede ser null)
    val userId by tokenManager.userId.collectAsState(initial = null)
    val userIdInt = remember(userId) { userId?.toIntOrNull() }

    // pestañas Todos / Incompletos / Completos
    var botonSeleccionado by remember { mutableStateOf(Tab.todos) }

    // flujo de todos los formularios del usuario
    val todosLosFormularios by produceState<List<FormInformation>>(initialValue = emptyList(), userId) {
        val uid = userId?.toLongOrNull()
        value = if (uid != null) {
            appContainer.usuariosRepository.getAllFormsForUser(uid).firstOrNull().orEmpty()
        } else {
            emptyList()
        }
    }

    // opciones de filtrado por tipo
    val tiposFormulario = listOf(
        "Todos los tipos",
        "Fauna en Transectos",
        "Fauna en Punto de Conteo",
        "Validación de Cobertura",
        "Parcela de Vegetación",
        "Fauna Búsqueda Libre",
        "Cámaras Trampa",
        "Variables Climáticas"
    )
    var tipoSeleccionado by remember { mutableStateOf<String?>(null) }

    // filtro combinado por tipo (drop-down) y por tab (Todos/Incompletos/Completos)
    fun formulariosFiltradosPorTipo(tab: Tab): List<FormInformation> {
        val baseList = when (tipoSeleccionado) {
            null, "Todos los tipos" -> todosLosFormularios
            "Fauna en Transectos" -> todosLosFormularios.filter { it.formulario == "form1" }
            "Fauna en Punto de Conteo" -> todosLosFormularios.filter { it.formulario == "form2" }
            "Validación de Cobertura" -> todosLosFormularios.filter { it.formulario == "form3" }
            "Parcela de Vegetación" -> todosLosFormularios.filter { it.formulario == "form4" }
            "Fauna Búsqueda Libre" -> todosLosFormularios.filter { it.formulario == "form5" }
            "Cámaras Trampa" -> todosLosFormularios.filter { it.formulario == "form6" }
            "Variables Climáticas" -> todosLosFormularios.filter { it.formulario == "form7" }
            else -> emptyList()
        }
        return when (tab) {
            Tab.todos -> baseList
            Tab.guardados -> baseList.filter { !it.completo }
            Tab.subidos -> baseList.filter { it.completo }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Busqueda", color = Color(0xFF4E7029)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color(0xFF4E7029)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFAED581))
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // tabs
                Row(
                    modifier = Modifier.padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = { botonSeleccionado = Tab.todos },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Todos",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = if (botonSeleccionado == Tab.todos) Color.Black else Color.Gray
                        )
                    }
                    TextButton(
                        onClick = { botonSeleccionado = Tab.guardados },
                        modifier = Modifier.weight(1.3f)
                    ) {
                        Text(
                            text = "Incompletos",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = if (botonSeleccionado == Tab.guardados) Color.Black else Color.Gray
                        )
                    }
                    TextButton(
                        onClick = { botonSeleccionado = Tab.subidos },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Completos",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = if (botonSeleccionado == Tab.subidos) Color.Black else Color.Gray
                        )
                    }
                }

                // drop-down por tipo
                OrdenarPorFormularioMenu(
                    tiposFormulario = tiposFormulario,
                    tipoSeleccionado = tipoSeleccionado,
                    onTipoSelected = { tipoSeleccionado = it }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // grid de tarjetas
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    reverseLayout = true,
                    modifier = Modifier
                        .padding(horizontal = 0.dp, vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    items(count = 1) { Spacer(modifier = Modifier.height(5.dp)) }
                    items(formulariosFiltradosPorTipo(botonSeleccionado)) { formInfo ->
                        FormCard(
                            formInfo = formInfo,
                            navController = navController,
                            appContainer = appContainer,
                            scope = scope,
                            userId = userIdInt
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OrdenarPorFormularioMenu(
    tiposFormulario: List<String>,
    tipoSeleccionado: String?,
    onTipoSelected: (String?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val label = if (tipoSeleccionado == null || tipoSeleccionado == "Todos los tipos") {
        "Ordenar por:"
    } else {
        "Ordenar por: $tipoSeleccionado"
    }
    Box {
        Button(
            onClick = { expanded = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFAED581)),
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text(label)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            tiposFormulario.forEach { tipo ->
                DropdownMenuItem(
                    text = { Text(tipo) },
                    onClick = {
                        onTipoSelected(if (tipo == "Todos los tipos") null else tipo)
                        expanded = false
                    }
                )
            }
        }
    }
}

enum class Tab { todos, guardados, subidos }

data class FormInformation(
    val tipo: String,
    val valorIdentificador: String,
    val primerTag: String,
    val primerContenido: String,
    val segundoTag: String,
    val segundoContenido: String,
    val formulario: String,   // "form1".."form7"
    val formId: Long,
    val fechaCreacion: String,
    val fechaEdicion: String,
    val completo: Boolean
) {
    constructor(formulario: FormularioUnoEntity) : this(
        tipo = "Transecto", valorIdentificador = formulario.transecto,
        primerTag = "Tipo", primerContenido = formulario.tipoAnimal,
        segundoTag = "Nombre", segundoContenido = formulario.nombreComun,
        formulario = "form1",
        formId = formulario.id,
        fechaCreacion = formulario.fecha,
        fechaEdicion = formulario.editado,
        completo = formulario.esCompleto()
    )
    constructor(formulario: FormularioDosEntity) : this(
        tipo = "Zona", valorIdentificador = formulario.zona,
        primerTag = "Tipo", primerContenido = formulario.tipoAnimal,
        segundoTag = "Nombre", segundoContenido = formulario.nombreComun,
        formulario = "form2",
        formId = formulario.id,
        fechaCreacion = formulario.fecha,
        fechaEdicion = formulario.editado,
        completo = formulario.esCompleto()
    )
    constructor(formulario: FormularioTresEntity) : this(
        tipo = "Código", valorIdentificador = formulario.codigo,
        primerTag = "Seguimiento", primerContenido = siONo(formulario.seguimiento),
        segundoTag = "Cambio", segundoContenido = siONo(formulario.cambio),
        formulario = "form3",
        formId = formulario.id,
        fechaCreacion = formulario.fecha,
        fechaEdicion = formulario.editado,
        completo = formulario.esCompleto()
    )
    constructor(formulario: FormularioCuatroEntity) : this(
        tipo = "Código", valorIdentificador = formulario.codigo,
        primerTag = "Cuad. A", primerContenido = formulario.quad_a,
        segundoTag = "Cuad. B", segundoContenido = formulario.quad_b,
        formulario = "form4",
        formId = formulario.id,
        fechaCreacion = formulario.fecha,
        fechaEdicion = formulario.editado,
        completo = formulario.esCompleto()
    )
    constructor(formulario: FormularioCincoEntity) : this(
        tipo = "Zona", valorIdentificador = formulario.zona,
        primerTag = "Tipo", primerContenido = formulario.tipoAnimal,
        segundoTag = "Nombre", segundoContenido = formulario.nombreComun,
        formulario = "form5",
        formId = formulario.id,
        fechaCreacion = formulario.fecha,
        fechaEdicion = formulario.editado,
        completo = formulario.esCompleto()
    )
    constructor(formulario: FormularioSeisEntity) : this(
        tipo = "Codigo", valorIdentificador = formulario.codigo,
        primerTag = "Zona", primerContenido = formulario.zona,
        segundoTag = "PlacaCamara", segundoContenido = formulario.placaCamara,
        formulario = "form6",
        formId = formulario.id,
        fechaCreacion = formulario.fecha,
        fechaEdicion = formulario.editado,
        completo = formulario.esCompleto()
    )
    constructor(formulario: FormularioSieteEntity) : this(
        tipo = "Zona", valorIdentificador = formulario.zona,
        primerTag = "Pluviosidad", primerContenido = formulario.pluviosidad,
        segundoTag = "TempMax", segundoContenido = formulario.temperaturaMaxima,
        formulario = "form7",
        formId = formulario.id,
        fechaCreacion = formulario.fecha,
        fechaEdicion = formulario.editado,
        completo = formulario.esCompleto()
    )

    fun editFormulario(navController: NavController) {
        when (formulario) {
            "form1" -> navController.navigate(route = FormUnoID(formId))
            "form2" -> navController.navigate(route = FormDosID(formId))
            "form3" -> navController.navigate(route = FormTresID(formId))
            "form4" -> navController.navigate(route = FormCuatroID(formId))
            "form5" -> navController.navigate(route = FormCincoID(formId))
            "form6" -> navController.navigate(route = FormSeisID(formId))
            "form7" -> navController.navigate(route = FormSieteID(formId))
            else -> Log.w("Busqueda", "CARD NAV NOT IMPLEMENTED FOR $formulario")
        }
    }
}

@Composable
fun FormCard(
    formInfo: FormInformation,
    navController: NavController,
    appContainer: AppContainer,
    scope: CoroutineScope,
    userId: Int?,
    modifier: Modifier = Modifier
) {
    var showEnviarDialog by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (formInfo.completo) {
                    // mostrar dialogo para enviar
                    showEnviarDialog = true
                } else {
                    // abrir para editar
                    formInfo.editFormulario(navController)
                }
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .padding(end = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "${formInfo.tipo}: ${formInfo.valorIdentificador}",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Row {
                    if (formInfo.completo) {
                        Icon(Icons.Rounded.CheckCircle, contentDescription = null, tint = Color.Green)
                    } else {
                        Icon(Icons.Rounded.Warning, contentDescription = null, tint = Color(237, 145, 33))
                    }
                    Spacer(modifier = Modifier.size(10.dp, 10.dp))
                    Text("Creado: ${formInfo.fechaCreacion}")
                }
            }
            Column {
                Text(text = "${formInfo.primerTag}: ${formInfo.primerContenido}", fontSize = 25.sp)
                Text(text = "${formInfo.segundoTag}: ${formInfo.segundoContenido}", fontSize = 25.sp)
            }
        }
    }

    if (showEnviarDialog) {
        AlertDialog(
            onDismissRequest = { showEnviarDialog = false },
            title = { Text("Formulario completo") },
            text = { Text("Este formulario ya está completo. ¿Desea enviarlo ahora?") },
            dismissButton = {
                TextButton(
                    onClick = { showEnviarDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color(0xFF4E7029)
                    )
                ) { Text("Atrás") }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showEnviarDialog = false

                        scope.launch {
                            // helper generico cargar -> enviar -> navegar
                            suspend fun <T> cargarYEnviar(
                                cargar: suspend () -> T?,
                                enviar: suspend (T, Int?) -> Result<Unit>
                            ) {
                                val entidad = withContext(Dispatchers.IO) { cargar() }
                                if (entidad == null) {
                                    Log.e("Busqueda", "No se encontró el formulario id=${formInfo.formId}")
                                    return
                                }
                                val r = withContext(Dispatchers.IO) { enviar(entidad, userId) }
                                if (r.isSuccess) {
                                    navController.navigate("home") { launchSingleTop = true }
                                } else {
                                    Log.e("Busqueda", "Falló envío: ${r.exceptionOrNull()?.message}")
                                }
                            }

                            when (formInfo.formulario) {
                                "form1" -> cargarYEnviar(
                                    cargar = { appContainer.formulariosRepository.getFormularioUnoStream(formInfo.formId).firstOrNull() },
                                    enviar  = { f, uid -> appContainer.formulariosRemoteRepository.enviarFormularioUno(f, uid) }
                                )
                                "form2" -> cargarYEnviar(
                                    cargar = { appContainer.formulariosRepository.getFormularioDosStream(formInfo.formId).firstOrNull() },
                                    enviar  = { f, uid -> appContainer.formulariosRemoteRepository.enviarFormularioDos(f, uid) }
                                )
                                "form3" -> cargarYEnviar(
                                    cargar = { appContainer.formulariosRepository.getFormularioTresStream(formInfo.formId).firstOrNull() },
                                    enviar  = { f, uid -> appContainer.formulariosRemoteRepository.enviarFormularioTres(f, uid) }
                                )
                                "form4" -> cargarYEnviar(
                                    cargar = { appContainer.formulariosRepository.getFormularioCuatroStream(formInfo.formId).firstOrNull() },
                                    enviar  = { f, uid -> appContainer.formulariosRemoteRepository.enviarFormularioCuatro(f, uid) }
                                )
                                "form5" -> cargarYEnviar(
                                    cargar = { appContainer.formulariosRepository.getFormularioCincoStream(formInfo.formId).firstOrNull() },
                                    enviar  = { f, uid -> appContainer.formulariosRemoteRepository.enviarFormularioCinco(f, uid) }
                                )
                                "form6" -> cargarYEnviar(
                                    cargar = { appContainer.formulariosRepository.getFormularioSeisStream(formInfo.formId).firstOrNull() },
                                    enviar  = { f, uid -> appContainer.formulariosRemoteRepository.enviarFormularioSeis(f, uid) }
                                )
                                "form7" -> cargarYEnviar(
                                    cargar = { appContainer.formulariosRepository.getFormularioSieteStream(formInfo.formId).firstOrNull() },
                                    enviar  = { f, uid -> appContainer.formulariosRemoteRepository.enviarFormularioSiete(f, uid) }
                                )
                                else -> Log.w("Busqueda", "Formulario desconocido: ${formInfo.formulario}")
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4E7029),
                        contentColor = Color.White
                    )
                ) { Text("Enviar") }
            }
        )
    }
}

private fun siONo(boolean: Boolean): String = if (boolean) "Sí" else "No"
