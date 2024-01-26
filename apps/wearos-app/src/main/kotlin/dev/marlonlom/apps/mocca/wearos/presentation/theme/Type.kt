/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.wearos.presentation.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Typography
import dev.marlonlom.apps.mocca.wearos.R

/**
 * Branded typography single object.
 *
 * @author marlonlom
 */
internal object BrandTypography {
  private val defaultFontFamily
    get() = FontFamily(
      Font(R.font.comfortaa_regular, FontWeight.Normal),
      Font(R.font.comfortaa_medium, FontWeight.Medium),
      Font(R.font.comfortaa_bold, FontWeight.Bold)
    )

  private val defaultTextStyle = TextStyle(
    fontFamily = defaultFontFamily,
    fontWeight = FontWeight.Normal,
  )

  val appTypography = Typography(
    defaultFontFamily = defaultFontFamily,

    display1 = defaultTextStyle.copy(
      fontWeight = FontWeight.Medium,
      fontSize = 40.sp,
      lineHeight = 46.sp,
      letterSpacing = 0.5.sp
    ),
    display2 = defaultTextStyle.copy(
      fontWeight = FontWeight.Medium,
      fontSize = 34.sp,
      lineHeight = 40.sp,
      letterSpacing = 1.sp
    ),
    display3 = defaultTextStyle.copy(
      fontWeight = FontWeight.Medium,
      fontSize = 30.sp,
      lineHeight = 36.sp,
      letterSpacing = 0.8.sp
    ),
    title1 = defaultTextStyle.copy(
      fontWeight = FontWeight.Medium,
      fontSize = 24.sp,
      lineHeight = 28.sp,
      letterSpacing = 0.2.sp
    ),
    title2 = defaultTextStyle.copy(
      fontWeight = FontWeight.Medium,
      fontSize = 20.sp,
      lineHeight = 24.sp,
      letterSpacing = 0.2.sp
    ),
    title3 = defaultTextStyle.copy(
      fontWeight = FontWeight.Medium,
      fontSize = 16.sp,
      lineHeight = 20.sp,
      letterSpacing = 0.2.sp
    ),
    body1 = defaultTextStyle.copy(
      fontSize = 16.sp,
      lineHeight = 20.sp,
      letterSpacing = 0.18.sp
    ),
    body2 = defaultTextStyle.copy(
      fontSize = 14.sp,
      lineHeight = 18.sp,
      letterSpacing = 0.2.sp
    ),
    button = defaultTextStyle.copy(
      fontWeight = FontWeight.Bold,
      fontSize = 15.sp,
      lineHeight = 19.sp,
      letterSpacing = 0.38.sp
    ),
    caption1 = defaultTextStyle.copy(
      fontWeight = FontWeight.Medium,
      fontSize = 14.sp,
      lineHeight = 18.sp,
      letterSpacing = 0.1.sp
    ),
    caption2 = defaultTextStyle.copy(
      fontWeight = FontWeight.Medium,
      fontSize = 12.sp,
      lineHeight = 16.sp,
      letterSpacing = 0.1.sp
    ),
    caption3 = defaultTextStyle.copy(
      fontWeight = FontWeight.Medium,
      fontSize = 10.sp,
      lineHeight = 14.sp,
      letterSpacing = 0.1.sp
    ),
  )
}
