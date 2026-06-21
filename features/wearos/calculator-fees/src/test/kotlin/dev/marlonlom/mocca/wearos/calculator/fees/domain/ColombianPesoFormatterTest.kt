/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.fees.domain

import org.junit.Assert.assertEquals
import org.junit.Test

internal class ColombianPesoFormatterTest {

  @Test
  fun formatCOP_shouldFormatZero() {
    val result = ColombianPesoFormatter.formatCOP(0)
    assertEquals(true, result.contains("0"))
  }

  @Test
  fun formatCOP_shouldFormatPositiveValue() {
    val result = ColombianPesoFormatter.formatCOP(1500)
    assertEquals(true, result.contains("1.500") || result.contains("1500"))
  }

  @Test
  fun format_underThousand_returnsRawNumberWithSymbol() {
    val result = ColombianPesoFormatter.format(999)
    assertEquals("$999", result)
  }

  @Test
  fun format_thousands_returnsKFormat() {
    val result = ColombianPesoFormatter.format(1500)
    assertEquals("$1.5K", result)
  }

  @Test
  fun format_exactThousand_hasNoDecimal() {
    val result = ColombianPesoFormatter.format(2000)
    assertEquals("$2K", result)
  }

  @Test
  fun format_millions_returnsMFormat() {
    val result = ColombianPesoFormatter.format(2_500_000)
    assertEquals("$2.5M", result)
  }

  @Test
  fun format_exactMillion_hasNoDecimal() {
    val result = ColombianPesoFormatter.format(3_000_000)
    assertEquals("$3M", result)
  }

  @Test
  fun format_largeMillion_truncatesToOneDecimal() {
    val result = ColombianPesoFormatter.format(1_555_000)
    assertEquals("$1.5M", result)
  }

  @Test
  fun format_allowsCustomSymbol() {
    val result = ColombianPesoFormatter.format(1500, "COP ")
    assertEquals("COP 1.5K", result)
  }
}
