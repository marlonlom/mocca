/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.ui.navigation

import org.junit.Assert.assertEquals
import org.junit.Test

internal class NavigationRouteTest {

  @Test
  fun `Should assert home destination route`() {
    val route = NavigationRoute.Home
    assertEquals(NavigationRoute.Home, route)
  }

  @Test
  fun `Should assert fees listing destination route`() {
    val route = NavigationRoute.ViewFees
    assertEquals(NavigationRoute.ViewFees, route)
  }

  @Test
  fun `Should assert calculator input destination route`() {
    val route = NavigationRoute.Calculator
    assertEquals(NavigationRoute.Calculator, route)
  }

  @Test
  fun `Should assert result destination route with amount`() {
    val route = NavigationRoute.Result("12345")
    assertEquals("12345", route.amountText)
  }
}
