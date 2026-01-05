package com.example.ui.themes

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Orange80,
    onPrimary = OrangeOn80,
    primaryContainer = OrangeLight80,
    onPrimaryContainer = OrangeOn80,
    secondary = Blue80,
    onSecondary = BlueOn80,
    secondaryContainer = Blue80,
    onSecondaryContainer = BlueOn80,
    tertiary = OrangeLight80,
    onTertiary = OrangeLightOn80,
    tertiaryContainer = OrangeLight80,
    onTertiaryContainer = OrangeLightOn80,
    error = Error80,
    onError = ErrorOn80,
    errorContainer = Error80,
    onErrorContainer = ErrorOn80,
    background = Background80,
    onBackground = OnBackground80,
    surface = Surface80,
    onSurface = OnSurface80,
    surfaceVariant = SurfaceVariant80,
    onSurfaceVariant = OnSurfaceVariant80,
    outline = Outline80,
    outlineVariant = OutlineVariant80
)

private val LightColorScheme = lightColorScheme(
    primary = Orange40,
    onPrimary = OrangeOn40,
    primaryContainer = OrangeDark40,
    onPrimaryContainer = OrangeDarkOn40,
    secondary = Blue40,
    onSecondary = BlueOn40,
    secondaryContainer = Blue40,
    onSecondaryContainer = BlueOn40,
    tertiary = OrangeDark40,
    onTertiary = OrangeDarkOn40,
    tertiaryContainer = OrangeDark40,
    onTertiaryContainer = OrangeDarkOn40,
    error = Error40,
    onError = ErrorOn40,
    errorContainer = Error40,
    onErrorContainer = ErrorOn40,
    background = Background40,
    onBackground = OnBackground40,
    surface = Surface40,
    onSurface = OnSurface40,
    surfaceVariant = SurfaceVariant40,
    onSurfaceVariant = OnSurfaceVariant40,
    outline = Outline40,
    outlineVariant = OutlineVariant40
)

@Composable
fun CarCarAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}