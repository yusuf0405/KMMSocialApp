package com.joseph.kmmsocialapp.android.common.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Blue = Color(0xFF1E80F8)
val MediumBlue = Color(0xFF63C5DA)
val LargeBlue = Color(0xFF2E8AF6)

val Gray = Color(0xFFF3F3F4)


val Black = Color(0xFF000000)
val PrimaryBlack = Color(0xFF181A1C)

val Black87 = Color(0xFF18191A)
val DarkGray = Color(0xFF999A9A)

val Black54 = Color(0xFF373B3F)
val Black24 = Color(0xFF242526)


val White = Color(0xFFFFFFFF)

val White87 = Color(0xFFE2E2E2)
val LightGray = Color(0xFF727477)

val White36 = Color(0xFFE5E5E5)
val White76 = Color(0xFFF5F5F5)

val LightPurple = Color(0xFFF62E8E)
val MediumPurple = Color(0xFFD224BE)
val LargePurple = Color(0xFFAC1AF0)
val SecondaryDarkGray = Color(0xFF323436)

internal val LightColors = lightColors(
    primary = Blue,
    primaryVariant = Blue,
    background = White,
    onBackground = Black87,
    surface = White,
    onSurface = Black87,
    secondary = DarkGray
)

internal val DarkColors = darkColors(
    primary = Blue,
    primaryVariant = Blue,
    background = Black87,
    onBackground = White87,
    surface = Black24,
    onSurface = White87,
    secondary = LightGray
)