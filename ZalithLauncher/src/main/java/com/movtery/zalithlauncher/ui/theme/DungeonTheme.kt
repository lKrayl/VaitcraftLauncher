/*
 * Vaitcraft - based on Zalith Launcher 2
 * Copyright (C) 2025 MovTery <movtery228@qq.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/gpl-3.0.txt>.
 */

package com.movtery.zalithlauncher.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.movtery.zalithlauncher.R
import com.movtery.zalithlauncher.setting.AllSettings

/** Tema "Dungeon": piedra oscura, oro envejecido y runas moradas */
object Dungeon {
    val Abyss = Color(0xFF0E0716)
    val Slate = Color(0xFF170C26)
    val SlatePanel = Color(0xFF22123A)
    val Stone = Color(0xFF2E1B4B)
    val StoneLight = Color(0xFF5B4580)
    val Gold = Color(0xFFC9A24B)
    val GoldBright = Color(0xFFE8C766)
    val GoldDark = Color(0xFF7A5C1E)
    val Purple = Color(0xFF9D00FF)
    val RuneDeep = Color(0xFF3C0F6B)
    val RuneMid = Purple
    val RuneGlow = Color(0xFFC77DFF)
    val Parchment = Color(0xFFE9E4D4)
    val TextMuted = Color(0xFFB0A5C4)
}

internal val dungeonScheme = darkColorScheme(
    primary = Dungeon.Gold,
    onPrimary = Dungeon.Abyss,
    primaryContainer = Color(0xFF3A2E12),
    onPrimaryContainer = Dungeon.GoldBright,
    inversePrimary = Dungeon.GoldDark,
    secondary = Dungeon.RuneMid,
    onSecondary = Color.White,
    secondaryContainer = Dungeon.RuneDeep,
    onSecondaryContainer = Color(0xFFE6C2FF),
    tertiary = Dungeon.RuneGlow,
    onTertiary = Dungeon.Abyss,
    tertiaryContainer = Color(0xFF2A0F4D),
    onTertiaryContainer = Dungeon.RuneGlow,
    background = Dungeon.Abyss,
    onBackground = Dungeon.Parchment,
    surface = Dungeon.Slate,
    onSurface = Dungeon.Parchment,
    surfaceVariant = Dungeon.SlatePanel,
    onSurfaceVariant = Dungeon.TextMuted,
    surfaceTint = Dungeon.Gold,
    inverseSurface = Dungeon.Parchment,
    inverseOnSurface = Dungeon.Slate,
    error = Color(0xFFE0574B),
    onError = Dungeon.Abyss,
    errorContainer = Color(0xFF5C1A15),
    onErrorContainer = Color(0xFFFFDAD5),
    outline = Dungeon.StoneLight,
    outlineVariant = Dungeon.GoldDark,
    scrim = Color.Black,
    surfaceDim = Dungeon.Abyss,
    surfaceBright = Dungeon.Stone, //tarjetas = placas de piedra
    surfaceContainerLowest = Dungeon.Abyss,
    surfaceContainerLow = Color(0xFF120A1F),
    surfaceContainer = Dungeon.Slate, //fondo de la app
    surfaceContainerHigh = Dungeon.SlatePanel,
    surfaceContainerHighest = Color(0xFF2B1A47),
)

/** Esquinas biseladas, como piedra tallada */
val DungeonShapes = Shapes(
    extraSmall = CutCornerShape(3.dp),
    small = CutCornerShape(4.dp),
    medium = CutCornerShape(6.dp),
    large = CutCornerShape(8.dp),
    extraLarge = CutCornerShape(12.dp),
)

/** Cinzel (SIL OFL 1.1, ver licenses/OFL-Cinzel.txt) */
private val Cinzel = FontFamily(
    Font(R.font.cinzel_variable, FontWeight.Normal),
    Font(R.font.cinzel_variable, FontWeight.Bold),
)

private val baseTypography = Typography()

val DungeonTypography = baseTypography.copy(
    displaySmall = baseTypography.displaySmall.copy(fontFamily = Cinzel, fontWeight = FontWeight.Bold),
    headlineLarge = baseTypography.headlineLarge.copy(fontFamily = Cinzel, fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
    headlineMedium = baseTypography.headlineMedium.copy(fontFamily = Cinzel, fontWeight = FontWeight.Bold, letterSpacing = 1.sp),
    headlineSmall = baseTypography.headlineSmall.copy(fontFamily = Cinzel, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp),
    titleLarge = baseTypography.titleLarge.copy(fontFamily = Cinzel, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp),
    titleMedium = baseTypography.titleMedium.copy(fontFamily = Cinzel, fontWeight = FontWeight.Bold),
    titleSmall = baseTypography.titleSmall.copy(fontFamily = Cinzel, fontWeight = FontWeight.Bold),
    labelLarge = baseTypography.labelLarge.copy(fontFamily = Cinzel, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp),
    labelMedium = baseTypography.labelMedium.copy(fontFamily = Cinzel, fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp),
    labelSmall = baseTypography.labelSmall.copy(fontFamily = Cinzel, fontWeight = FontWeight.Normal, letterSpacing = 0.4.sp),
)

@Composable
fun isDungeonTheme(): Boolean = AllSettings.launcherColorTheme.state == ColorThemeType.DUNGEON

/** Marco dorado de las tarjetas (solo con el tema Dungeon) */
@Composable
fun cardBorder(): BorderStroke? =
    if (isDungeonTheme()) BorderStroke(2.dp, Brush.verticalGradient(listOf(Dungeon.GoldBright, Dungeon.GoldDark)))
    else null

@Composable
fun actionButtonShape(): Shape =
    if (isDungeonTheme()) CutCornerShape(6.dp) else ButtonDefaults.shape

@Composable
fun actionButtonBorder(enabled: Boolean = true): BorderStroke? = when {
    !isDungeonTheme() -> null
    enabled -> BorderStroke(2.dp, Brush.verticalGradient(listOf(Dungeon.Gold, Dungeon.GoldDark)))
    else -> BorderStroke(2.dp, SolidColor(Dungeon.StoneLight))
}
