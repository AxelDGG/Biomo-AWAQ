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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.awaq1.MainActivity
import com.example.awaq1.data.formularios.*
import com.example.awaq1.navigator.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Busqueda(navController: NavController) {
    val context = LocalContext.current as MainActivity
    val appContainer = context.container
    var botonSeleccionado by remember { mutableStateOf(Tab.todos) }

    val forms1: List<FormularioUnoEntity> by appContainer.usuariosRepository.getAllFormularioUnoForUserID(
        context.accountInfo.user_id
    ).collectAsState(initial = emptyList())
    val forms2: List<FormularioDosEntity> by appContainer.usuariosRepository.getAllFormularioDosForUserID(
        context.accountInfo.user_id
    ).collectAsState(initial = emptyList())
    val forms3: List<FormularioTresEntity> by appContainer.usuariosRepository.getAllFormularioTresForUserID(
        context.accountInfo.user_id
    ).collectAsState(initial = emptyList())
    val forms4: List<FormularioCuatroEntity> by appContainer.usuariosRepository.getAllFormularioCuatroForUserID(
        context.accountInfo.user_id
    ).collectAsState(initial = emptyList())
    val forms5: List<FormularioCincoEntity> by appContainer.usuariosRepository.getAllFormularioCincoForUserID(
        context.accountInfo.user_id
    ).collectAsState(initial = emptyList())
    val forms6: List<FormularioSeisEntity> by appContainer.usuariosRepository.getAllFormularioSeisForUserID(
        context.accountInfo.user_id
    ).collectAsState(initial = emptyList())
    val forms7: List<FormularioSieteEntity> by appContainer.usuariosRepository.getAllFormularioSieteForUserID(
        context.accountInfo.user_id
    ).collectAsState(initial = emptyList())

    // Tipos de formulario con opción para quitar filtro
    val tiposFormulario = listOf(
        "Todos los tipos", // Esta opción quita el filtrado
        "Fauna en Transectos",
        "Fauna en Punto de Conteo",
        "Validación de Cobertura",
        "Parcela de Vegetación",
        "Fauna Búsqueda Libre",
        "Cámaras Trampa",
        "Variables Climáticas"
    )
    var tipoSeleccionado by remember { mutableStateOf<String?>(null) }

    // Devuelve la lista filtrada según la TAB y tipoSelecccionado
    fun formulariosFiltradosPorTipo(tab: Tab): List<FormInformation> {
        val baseList: List<FormInformation> = when (tipoSeleccionado) {
            null, "Todos los tipos" -> (
                    forms1.map { FormInformation(it) } +
                            forms2.map { FormInformation(it) } +
                            forms3.map { FormInformation(it) } +
                            forms4.map { FormInformation(it) } +
                            forms5.map { FormInformation(it) } +
                            forms6.map { FormInformation(it) } +
                            forms7.map { FormInformation(it) }
                    )
            "Fauna en Transectos" -> forms1.map { FormInformation(it) }
            "Fauna en Punto de Conteo" -> forms2.map { FormInformation(it) }
            "Validación de Cobertura" -> forms3.map { FormInformation(it) }
            "Parcela de Vegetación" -> forms4.map { FormInformation(it) }
            "Fauna Búsqueda Libre" -> forms5.map { FormInformation(it) }
            "Cámaras Trampa" -> forms6.map { FormInformation(it) }
            "Variables Climáticas" -> forms7.map { FormInformation(it) }
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
                title = { Text(text = "Busqueda") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFAED581))
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
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
                // Botones de pestañas
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
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
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
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
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
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = if (botonSeleccionado == Tab.subidos) Color.Black else Color.Gray
                        )
                    }
                }
                // Menú desplegable de tipos de formulario
                OrdenarPorFormularioMenu(
                    tiposFormulario = tiposFormulario,
                    tipoSeleccionado = tipoSeleccionado,
                    onTipoSelected = { tipoSeleccionado = it }
                )
                Spacer(modifier = Modifier.height(10.dp))
                // Grilla de ítems
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    reverseLayout = true,
                    modifier = Modifier
                        .padding(horizontal = 0.dp, vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    items(count = 1) { Spacer(modifier = Modifier.height(10.dp)) }
                    items(formulariosFiltradosPorTipo(botonSeleccionado)) { formCard ->
                        formCard.verCard(navController)
                    }
                }
            }
        }
    }
}

// Menú desplegable. Por defecto dice “Ordenar por:” y cambia el texto al seleccionar
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
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
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

enum class Tab {
    todos, guardados, subidos
}

// --- DATA CLASS Y CONSTRUCTORES ---
data class FormInformation(
    val tipo: String,
    val valorIdentificador: String,
    val primerTag: String,
    val primerContenido: String,
    val segundoTag: String,
    val segundoContenido: String,
    val formulario: String,
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

    fun editFormulario(navController: NavController) {
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
    fun verCard(navController: NavController, modifier: Modifier = Modifier) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding()
                .clickable { this.editFormulario(navController) },
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
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                    Text(
                        text = "$segundoTag: $segundoContenido",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

private fun siONo(boolean: Boolean): String = if (boolean) "Sí" else "No"
