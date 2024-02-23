/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.dataStore
import dev.marlonlom.apps.mocca.feats.settings.slots.AboutSettingsSlot
import dev.marlonlom.apps.mocca.feats.settings.slots.AppearanceSettingsSlot
import dev.marlonlom.apps.mocca.feats.settings.slots.LegalNotesSettingsSlot
import dev.marlonlom.apps.mocca.ui.main.MainActions
import dev.marlonlom.apps.mocca.ui.main.scaffold.ScaffoldInnerContentType
import dev.marlonlom.apps.mocca.ui.main.scaffold.isExpandedWidth
import dev.marlonlom.apps.mocca.ui.main.scaffold.isMediumWidth
import dev.marlonlom.apps.mocca.ui.util.WindowSizeInfo

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
  settingsViewModel: SettingsViewModel = viewModel(
    factory = SettingsViewModel.factory(
      SettingsRepository(LocalContext.current.dataStore)
    )
  ),
) {

  val userPreferences by settingsViewModel.settingsUiState.collectAsStateWithLifecycle()

  val horizontalPadding = when {
    (windowSizeInfo.indicateInnerContent is ScaffoldInnerContentType.TwoPane).and(windowSizeInfo.windowSizeClass.isExpandedWidth)
      .and(windowSizeInfo.isTabletLandscape) -> 80.dp

    (windowSizeInfo.indicateInnerContent is ScaffoldInnerContentType.TwoPane).and(windowSizeInfo.windowSizeClass.isMediumWidth) -> 20.dp
    else -> 20.dp
  }

  Column(
    modifier = Modifier
      .padding(horizontal = horizontalPadding)
      .verticalScroll(rememberScrollState()),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 40.dp, bottom = 20.dp),
      text = stringResource(R.string.text_settings_headline),
      style = MaterialTheme.typography.headlineLarge,
      fontWeight = FontWeight.Bold
    )
    AppearanceSettingsSlot(
      userPreferences = userPreferences,
      onBooleanSettingChanged = settingsViewModel::toggleBooleanPreference
    )
    LegalNotesSettingsSlot(
      userPreferences = userPreferences,
      onOssLicencesSettingLinkClicked = mainActions.onOssLicencesSettingLinkClicked
    )
    AboutSettingsSlot(
      userPreferences = userPreferences,
      onFeedbackSettingLinkClicked = mainActions.onFeedbackSettingLinkClicked
    )
  }
}
