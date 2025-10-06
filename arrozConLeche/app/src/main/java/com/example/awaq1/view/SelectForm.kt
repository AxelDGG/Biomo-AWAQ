package com.example.awaq1.view

import android.graphics.Paint.Align
import android.os.Build
import android.widget.Button
import android.widget.Space
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.awaq1.R
import com.example.awaq1.navigator.FormCincoID
import com.example.awaq1.navigator.FormCuatroID
import com.example.awaq1.navigator.FormDosID
import com.example.awaq1.navigator.FormSeisID
import com.example.awaq1.navigator.FormSieteID
import com.example.awaq1.navigator.FormTresID
import com.example.awaq1.navigator.FormUnoID

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewSelectFormulario() {
    SelectFormularioScreen(rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectFormularioScreen(navController: NavController) {
    Scaffold(
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.b),
                contentDescription = "Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = { navController.navigate("home") },
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color(0xFF4E7029)
                ),
                modifier = Modifier.padding(30.dp).size(60.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                FormChooseButton(FormUnoID(), "Fauna en Transectos", navController)
                FormChooseButton(FormDosID(), "Fauna en Punto de Conteo", navController)
                FormChooseButton(FormTresID(), "Validación de Cobertura", navController)
                FormChooseButton(FormCuatroID(), "Parcela de Vegetación", navController)
                FormChooseButton(FormCincoID(), "Fauna Busqueda Libre", navController)
                FormChooseButton(FormSeisID(), "Camaras Trampa", navController)
                FormChooseButton(FormSieteID(), "Variables Climaticas", navController)
            }
        }
    }
}

@Composable
fun FormChooseButton(route: Any, text: String, navController: NavController) {
    Button(
        onClick = { navController.navigate(route) },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4E7029),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(30),
        modifier = Modifier
            .width(300.dp) // Fixed width and height
    ) {
        Text(
            text,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}