/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.shortcut

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class AppShortcutTest {

  @Test
  fun `given AppShortcut entries when accessed then contains expected values and ids`() {
    assertEquals(1, AppShortcut.entries.size)
    assertEquals("view_fees", AppShortcut.VIEW_FEES.id)
  }

  @Test
  fun `given valid shortcut id when fromId is called then returns corresponding app shortcut`() {
    val result = AppShortcut.fromId("view_fees")
    assertEquals(AppShortcut.VIEW_FEES, result)
  }

  @Test
  fun `given unknown shortcut id when fromId is called then returns null`() {
    val result = AppShortcut.fromId("invalid_shortcut_id")
    assertNull(result)
  }

  @Test
  fun `given null id when fromId is called then returns null`() {
    val result = AppShortcut.fromId(null)
    assertNull(result)
  }

  @Test
  fun `given empty string id when fromId is called then returns null`() {
    val result = AppShortcut.fromId("")
    assertNull(result)
  }
}
