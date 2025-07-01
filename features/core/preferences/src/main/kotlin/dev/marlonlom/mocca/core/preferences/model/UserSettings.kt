/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.preferences.model

/**
 * Data class representing the user's application settings.
 * <br><br>
 * This class holds various user-configurable options that affect the
 * application's behavior and appearance.
 *
 * @author marlonlom
 *
 * @property isOnboarding Indicates whether the user has completed the onboarding process.
 * @property useDarkTheme Indicates whether the user has enabled the dark theme.
 * @property useDynamicColor Indicates whether the user has enabled dynamic colors.
 * @property colorContrast The selected color contrast preference for the user.
 */
data class UserSettings(
  val isOnboarding: Boolean,
  val useDarkTheme: Boolean,
  val useDynamicColor: Boolean,
  val colorContrast: String = UserColorContrasts.STANDARD.name,
)
