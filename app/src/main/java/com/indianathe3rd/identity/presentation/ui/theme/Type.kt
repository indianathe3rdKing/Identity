package com.indianathe3rd.identity.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.indianathe3rd.identity.R

// Set of Material typography styles to start with
val fredokaFontFamily = FontFamily(
    Font(R.font.fredoka)
)

val plusJakartaFontFamily = FontFamily(
    Font(R.font.plus_jakarta_sans),
    Font(R.font.plus_jakarta_sans_italic)
)

val playpenFontFamily = FontFamily(
    Font(R.font.playpen_sans)
)

val sueEllenFontFamily = FontFamily(
    Font(R.font.sue_ellen_francisco_regular, FontWeight.Normal)
)

val sfProFontFamily = FontFamily(
    Font(R.font.sfpro_ultralight_italic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.sfpro_thin_italic, FontWeight.Thin, FontStyle.Italic),
    Font(R.font.sfpro_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.sfpro_regular, FontWeight.Normal),
    Font(R.font.sfpro_semibold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.sfpro_medium, FontWeight.Medium),
    Font(R.font.sfpro_heavy_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.sfpro_bold, FontWeight.Bold),
    Font(R.font.sfpro_black_italic, FontWeight.Black, FontStyle.Italic)
)


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override*/,
    titleLarge = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 15.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        fontFamily = playpenFontFamily
        ,
        fontWeight = FontWeight.W600,
        fontSize = 12.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = sfProFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    )

)