/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.ui.navigation

import dev.marlonlom.mocca.wearos.ui.navigation.NavigationRoutes.Home
import dev.marlonlom.mocca.wearos.ui.navigation.NavigationRoutes.Result
import org.junit.Assert.assertEquals
import org.junit.Test

internal class NavigationRoutesTest {

  @Test
  fun `Should assert home destination route`() {
    assertEquals("calculator_input", Home.route)
  }

  @Test
  fun `Should assert empty results destination route`() {
    assertEquals("calculator_results/", Result.makeRoute(""))
  }

  @Test
  fun `Should assert negative amount results destination route`() {
    assertEquals("calculator_results/-123", Result.makeRoute("-123"))
  }

  @Test
  fun `Should assert valid amount results destination route`() {
    assertEquals("calculator_results/12345", Result.makeRoute("12345"))
  }
}
