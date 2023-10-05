/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil

/**
 * Settings screen route composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeUtil Window size class.
 * @param userPreferences User preferences ui state value.
 * @param onBooleanSettingChanged Action for boolean setting changed.
 * @param onOssLicencesSettingLinkClicked Action for oss licences setting clicked.
 * @param onOpeningExternalUrlSettingClicked Action for opening external url.
 * @param onFeedbackSettingLinkClicked Action for feedback setting clicked.
 */
@Composable
fun SettingsRoute(
  windowSizeUtil: WindowSizeUtil,
  userPreferences: UserPreferences,
  onBooleanSettingChanged: (String, Boolean) -> Unit,
  onOssLicencesSettingLinkClicked: () -> Unit,
  onOpeningExternalUrlSettingClicked: (String) -> Unit,
  onFeedbackSettingLinkClicked: () -> Unit
) {

  val gridCells = GridCells.Fixed(2)

  val lazyVerticalListHorizontalPadding = when {
    windowSizeUtil.isTabletLandscape -> PaddingValues(horizontal = 100.dp, vertical = 20.dp)
    windowSizeUtil.isTabletWidth -> PaddingValues(horizontal = 80.dp)
    else -> PaddingValues(horizontal = 20.dp)
  }

  LazyVerticalGrid(
    modifier = Modifier
      .fillMaxWidth()
      .padding(lazyVerticalListHorizontalPadding),
    columns = gridCells,
    verticalArrangement = Arrangement.SpaceBetween,
    horizontalArrangement = Arrangement.Center
  ) {
    settingsHeadline()
    darkThemeSettingSlot(windowSizeUtil, userPreferences, onBooleanSettingChanged)
    dynamicColorsSettingSlot(userPreferences, onBooleanSettingChanged)
    settingsGroupTitle(R.string.text_settings_title_legal)
    aboutEfectySettingSlot(windowSizeUtil, onOpeningExternalUrlSettingClicked, userPreferences)
    ossLicencesSettingSlot(onOssLicencesSettingLinkClicked)
    settingsGroupTitle(R.string.text_settings_title_app_info)
    feedbackSettingSlot(onFeedbackSettingLinkClicked)
    appVersionSettingSlot(userPreferences)
  }
}

