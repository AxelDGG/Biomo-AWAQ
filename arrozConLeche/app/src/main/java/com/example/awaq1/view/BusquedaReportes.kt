package com.example.awaq1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.awaq1.MainActivity
import com.example.awaq1.R
import com.example.awaq1.data.formularios.FormularioUnoEntity
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObservationListScreen(navController: NavController) {
    val context = LocalContext.current as MainActivity
    val appContainer = context.container
    val formsList: List<FormularioUnoEntity> by appContainer.formulariosRepository.getAllFormularioUnosStream()
        .collectAsState(initial = emptyList())

    var completoSelected by remember { mutableStateOf(false) }
    var incompletoSelected by remember { mutableStateOf(false) }
    var subidoSelected by remember { mutableStateOf(false) }
    var noSubidoSelected by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Observaciones") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.leading_iconarrow),
                            contentDescription = "Atrás"
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
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterButton(
                        text = "Completo",
                        selected = completoSelected,
                        onSelected = { completoSelected = it }
                    )
                    FilterButton(
                        text = "Incompleto",
                        selected = incompletoSelected,
                        onSelected = { incompletoSelected = it }
                    )
                    FilterButton(
                        text = "Subido",
                        selected = subidoSelected,
                        onSelected = { subidoSelected = it }
                    )
                    FilterButton(
                        text = "No Subido",
                        selected = noSubidoSelected,
                        onSelected = { noSubidoSelected = it }
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    items(formsList) { form ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable {
                                    // navController.navigate("formDetail/${formsList.}")
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White  // Color de fondo blanco
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 4.dp  // Añade un poco de elevación para mejor contraste
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = "Transecto: ${form.transecto}",
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = Color.Black  // Texto negro para mejor contraste
                                )
                                Text(
                                    text = "Tipo de Animal: ${form.tipoAnimal}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black
                                )
                                Text(
                                    text = "Nombre Común: ${form.nombreComun}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black
                                )
                                Text(
                                    text = "Observaciones: ${form.observaciones}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilterButton(
    text: String,
    selected: Boolean,
    onSelected: (Boolean) -> Unit
) {
    Button(
        onClick = { onSelected(!selected) },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color(0xFF7CB342) else Color(0xFFAED581),
            contentColor = if (selected) Color.White else Color.Black
        ),
        modifier = Modifier.height(40.dp)
    ) {
        Text(text)
    }
}