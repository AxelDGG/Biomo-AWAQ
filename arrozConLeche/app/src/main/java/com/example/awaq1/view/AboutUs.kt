package com.example.awaq1.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.awaq1.R

@Composable
fun AboutUs(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(scaleX = 1.2f, scaleY = 1.2f)
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .padding(top = 200.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(bottom = 16.dp)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Equipo Arroz Con Leche",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "José Pablo Santos Quiroga",
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                    Text(
                        text = "Gabriel Galván García",
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                    Text(
                        text = "Omar Flores García ",
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                    Text(
                        text = "David Martínez Muraira",
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                    Text(
                        text = "Carlos Vázquez Treviño",
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }

                // Contact information
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Correo Contacto: A00836080@tec.mx",
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutUsPreview() {
    // Creating a fake NavController for the preview
    val navController = rememberNavController()
    AboutUs(navController = navController)
}
