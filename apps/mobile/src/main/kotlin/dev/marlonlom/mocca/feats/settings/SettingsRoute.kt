/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.feats.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.marlonlom.mocca.dataStore
import dev.marlonlom.mocca.feats.settings.parts.SettingsHeaderText
import dev.marlonlom.mocca.feats.settings.slots.AboutSettingsSlot
import dev.marlonlom.mocca.feats.settings.slots.AppearanceSettingsSlot
import dev.marlonlom.mocca.feats.settings.slots.LegalNotesSettingsSlot
import dev.marlonlom.mocca.ui.main.MainActions
import dev.marlonlom.mocca.ui.main.scaffold.ScaffoldInnerContentType
import dev.marlonlom.mocca.ui.main.scaffold.isCompactHeight
import dev.marlonlom.mocca.ui.main.scaffold.isExpandedWidth
import dev.marlonlom.mocca.ui.main.scaffold.isMediumWidth
import dev.marlonlom.mocca.ui.util.WindowSizeInfo

/**
 * Settings screen route composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeInfo Window size info.
 * @param mainActions Main actions data class.
 * @param settingsViewModel Settings viewmodel default instance.
 */
@Composable
fun SettingsRoute(
  windowSizeInfo: WindowSizeInfo,
  mainActions: MainActions,
  onBackNavigationAction: () -> Unit = {},
  settingsViewModel: SettingsViewModel = viewModel(
    factory = SettingsViewModel.factory(
      SettingsRepository(LocalContext.current.dataStore),
    ),
  ),
) {
  BackHandler {
    onBackNavigationAction()
  }

  val userPreferences by settingsViewModel.settingsUiState.collectAsStateWithLifecycle(
    lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current,
  )

  val horizontalPadding = getSettingsContentHorizontalPadding(windowSizeInfo)

  Column(
    modifier = Modifier
      .verticalScroll(rememberScrollState())
      .padding(horizontal = horizontalPadding),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    SettingsHeaderText(windowSizeInfo)
    AppearanceSettingsSlot(
      userPreferences = userPreferences,
      onBooleanSettingChanged = settingsViewModel::toggleBooleanPreference,
    )
    LegalNotesSettingsSlot(
      userPreferences = userPreferences,
      onOssLicencesSettingLinkClicked = mainActions.onOssLicencesSettingLinkClicked,
    )
    AboutSettingsSlot(
      userPreferences = userPreferences,
      onFeedbackSettingLinkClicked = mainActions.onFeedbackSettingLinkClicked,
    )
  }
}

/**
 * Returns settings content horizontal padding value using [WindowSizeInfo].
 *
 * @param wsi Window size info.
 *
 * @return Horizontal padding value as DP.
 */
@Composable
private fun getSettingsContentHorizontalPadding(wsi: WindowSizeInfo): Dp = when {
  (wsi.scaffoldInnerContentType is ScaffoldInnerContentType.SinglePane).and(
    wsi.windowSizeClass.isCompactHeight,
  ) -> 100.dp

  (wsi.scaffoldInnerContentType is ScaffoldInnerContentType.SinglePane).and(wsi.windowSizeClass.isMediumWidth) -> 60.dp

  (wsi.scaffoldInnerContentType is ScaffoldInnerContentType.TwoPane).and(wsi.windowSizeClass.isExpandedWidth)
    .and(wsi.windowSizeClass.isCompactHeight.not()).and(wsi.isTabletLandscape) -> 40.dp

  (wsi.scaffoldInnerContentType is ScaffoldInnerContentType.TwoPane).and(wsi.windowSizeClass.isExpandedWidth)
    .and(wsi.isTabletLandscape) -> 80.dp

  (wsi.scaffoldInnerContentType is ScaffoldInnerContentType.TwoPane).and(wsi.windowSizeClass.isMediumWidth) -> 20.dp

  else -> 20.dp
}
