/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.preferences.model

/**
 * Represents the available color contrast options that a user can select.
 *
 * @author marlonlom
 */
enum class UserColorContrasts {

  /** Standard contrast, the default system contrast. */
  STANDARD,

  /** Medium contrast, a level between standard and high. */
  MEDIUM,

  /** High contrast, for users who need stronger visual differentiation. */
  HIGH,
}
