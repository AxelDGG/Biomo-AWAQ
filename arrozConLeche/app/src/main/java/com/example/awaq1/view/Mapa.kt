package com.example.awaq1.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.awaq1.R


@Composable
fun Mapa(navController: NavController){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)){
            Image(
                painter = painterResource(id = R.drawable.rectangle_623map),  // Reference to your drawable image
                contentDescription = "Map Image",
                modifier = Modifier
                    .fillMaxSize(),  // Adjust height as needed
                contentScale = ContentScale.Crop)
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(Color(0xFFB4D68F))
                .padding(5.dp)
            ){
                Row(modifier = Modifier
                    .offset(y = 15.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.leading_iconarrow),  // Your present icon resource
                        contentDescription = "Arrow Icon",
                        modifier = Modifier
                            .size(80.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "Mi Ubicación", style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )
                    )

                    Spacer(modifier = Modifier.width(100.dp))

                    Image(
                        painter = painterResource(id = R.drawable.trailing_icontripledots),  // Your present icon resource
                        contentDescription = "Dots Icon",
                        modifier = Modifier
                            .size(80.dp),
                        contentScale = ContentScale.Fit
                    )
                }

            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(y = 750.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Button(onClick = {
                    navController.navigate("settings")
                }, modifier = Modifier
                    .width(300.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4E7029))
                ) {
                    Text(text="GUARDAR", style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ))
                }
            }
        }
    }
}
