package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val MaryadaColorScheme = darkColorScheme(
    primary = DivyaGoldPrimary,
    onPrimary = DeepBlack,
    primaryContainer = CardSurfaceElevated,
    onPrimaryContainer = LuminousGoldLight,
    secondary = AntiqueGold,
    onSecondary = DeepBlack,
    secondaryContainer = CardSurfaceDark,
    onSecondaryContainer = GoldenAmber,
    tertiary = WarmGlowOrange,
    onTertiary = DeepBlack,
    background = DeepBlack,
    onBackground = TextIvoryWhite,
    surface = CardSurfaceDark,
    onSurface = TextIvoryWhite,
    surfaceVariant = CardSurfaceElevated,
    onSurfaceVariant = TextWarmSilver,
    outline = GoldBorderSubtle,
    outlineVariant = CosmicDivider
)

@Composable
fun MaryadaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = DeepBlack.toArgb()
                window.navigationBarColor = DeepBlack.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
            }
        }
    }

    MaterialTheme(
        colorScheme = MaryadaColorScheme,
        typography = Typography,
        content = content
    )
}
