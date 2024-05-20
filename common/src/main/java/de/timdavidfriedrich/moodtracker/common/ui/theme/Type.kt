package de.timdavidfriedrich.moodtracker.common.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import de.timdavidfriedrich.moodtracker.common.R

val LondrinaSolidFamily = FontFamily(
    Font(R.font.londrinasolid_thin, FontWeight.Thin),
    Font(R.font.londrinasolid_thin, FontWeight.Light),
    Font(R.font.londrinasolid_light, FontWeight.Normal),
    Font(R.font.londrinasolid_light, FontWeight.Medium),
    Font(R.font.londrinasolid_regular, FontWeight.SemiBold),
    Font(R.font.londrinasolid_regular, FontWeight.Bold),
    Font(R.font.londrinasolid_black, FontWeight.Black),
)

private val defaultTypography = Typography()
private val defaultFont = LondrinaSolidFamily
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = defaultFont),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = defaultFont),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = defaultFont),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = defaultFont),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = defaultFont),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = defaultFont),

    titleLarge = TextStyle(
        fontFamily = LondrinaSolidFamily,
        fontWeight = FontWeight.Black,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = defaultFont),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = defaultFont),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = defaultFont),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = defaultFont),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = defaultFont),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = defaultFont),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = defaultFont),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = defaultFont)

)
