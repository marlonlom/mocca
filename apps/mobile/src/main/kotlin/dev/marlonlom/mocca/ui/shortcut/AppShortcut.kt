/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.shortcut

/**
 * Application shortcuts enumeration representing supported actions
 * triggered from system app shortcuts.
 *
 * @property id The string identifier matching static shortcuts configuration.
 *
 * @author marlonlom
 */
enum class AppShortcut(val id: String) {

  /** View fee calculations shortcut. */
  VIEW_FEES("view_fees"),
  ;

  companion object {

    /**
     * Returns the [AppShortcut] corresponding to the given string [id], or null if not found.
     *
     * @param id The shortcut identifier string.
     * @return The matching [AppShortcut] enum value, or null.
     */
    fun fromId(id: String?): AppShortcut? = entries.find { it.id == id }
  }
}
