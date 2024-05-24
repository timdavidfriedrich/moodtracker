package de.timdavidfriedrich.moodtracker.common.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import de.timdavidfriedrich.moodtracker.common.R

val LondrinaSolidFamily = FontFamily(
    Font(R.font.londrinasolid_thin, FontWeight.Thin),
    Font(R.font.londrinasolid_light, FontWeight.Light),
    Font(R.font.londrinasolid_regular, FontWeight.Normal),
    Font(R.font.londrinasolid_black, FontWeight.Black),
)

val RubikFamily = FontFamily(
    Font(R.font.rubik_light, FontWeight.Light),
    Font(R.font.rubik_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.rubik_regular, FontWeight.Normal),
    Font(R.font.rubik_regular_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.rubik_medium, FontWeight.Medium),
    Font(R.font.rubik_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.rubik_semibold, FontWeight.SemiBold),
    Font(R.font.rubik_semibold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.rubik_bold, FontWeight.Bold),
    Font(R.font.rubik_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.rubik_extrabold, FontWeight.ExtraBold),
    Font(R.font.rubik_extrabold_italic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.rubik_black, FontWeight.Black),
    Font(R.font.rubik_black_italic, FontWeight.Black, FontStyle.Italic),
)

val bodyFontFamily = RubikFamily
val displayFontFamily = LondrinaSolidFamily
val baseline = Typography()

val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
)