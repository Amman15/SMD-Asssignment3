package com.example.datastorageapp.ui.theme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
private val LightColorPalette = lightColors(
    primary = lightPurple,
    primaryVariant = offWhite,
    secondary = WhiteColor
)
@Composable
fun DataStorageAppTheme(
    content: @Composable () -> Unit
) {
    val colors =
        LightColorPalette
    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}