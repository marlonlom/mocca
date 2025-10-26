/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.settings.domain

/**
 * Settings actions data class.
 *
 * @author marlonlom
 *
 * @property onOssLicencesDisplayed Action for oss licences setting clicked.
 * @property onOpeningExternalUrl Action for opening external url.
 * @property onFeedbackDisplayed Action for feedback setting clicked.
 */
data class SettingActions(
  val onOssLicencesDisplayed: () -> Unit,
  val onOpeningExternalUrl: (String) -> Unit,
  val onFeedbackDisplayed: () -> Unit,
)
