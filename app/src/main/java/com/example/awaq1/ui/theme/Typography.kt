package com.example.awaq1.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.awaq1.R

// definicion de la font family default, en este caso va a ser poppins como en el figma de BIOMO
val AppFont = FontFamily(
    Font(R.font.poppinssemibold)
)

val ItalicAppFont = FontFamily(
    Font(R.font.poppinssemibolditalic)
)

// crear tipografia customizada
val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = AppFont,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = AppFont,
        fontSize = 14.sp
    ),
    titleLarge = TextStyle(
        fontFamily = AppFont,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle( //para los botones de la view del selectform
        fontFamily = AppFont,
        fontSize = 25.sp
    )

)

val NombreCientificoForms = TextStyle(
    fontFamily = ItalicAppFont,
    fontSize = 16.sp
)
