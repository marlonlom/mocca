/*
 * Copyright 2023 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.twopane

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.dataStore
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorPortraitScreen
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorUiState
import dev.marlonlom.apps.mocca.feats.calculator.CalculatorViewModel
import dev.marlonlom.apps.mocca.feats.settings.SettingsRepository
import dev.marlonlom.apps.mocca.feats.settings.SettingsRoute
import dev.marlonlom.apps.mocca.feats.settings.SettingsViewModel
import dev.marlonlom.apps.mocca.ui.util.CustomTabsOpener
import dev.marlonlom.apps.mocca.ui.util.FeedbackOpener
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil
import timber.log.Timber

/**
 * Two pane layout that contains Calculator and Settings screen combination composable ui.
 *
 * @author marlonlom
 *
 * @param windowSizeUtil Window size utility.
 */
@Composable
fun CalculatorAndSettingsRoute(
  windowSizeUtil: WindowSizeUtil,
  calculatorViewModel: CalculatorViewModel = viewModel(factory = CalculatorViewModel.Factory),
  settingsViewModel: SettingsViewModel = viewModel(factory = SettingsViewModel.factory(SettingsRepository(LocalContext.current.dataStore)))
) {

  val context = LocalContext.current

  Row(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier
        .fillMaxWidth(0.4f)
        .padding(bottom = 20.dp)
    ) {
      val calculationState by calculatorViewModel.uiState.collectAsState()

      val calculationAmount = when (calculationState) {
        is CalculatorUiState.WithFailure -> (calculationState as CalculatorUiState.WithFailure).amount
        is CalculatorUiState.WithSuccess -> (calculationState as CalculatorUiState.WithSuccess).amount
        is CalculatorUiState.Empty -> ""
      }
      val amountTextState = remember { mutableStateOf(calculationAmount) }

      CalculatorPortraitScreen(
        windowSizeUtil = windowSizeUtil,
        amountTextState = amountTextState,
        calculationState = calculationState,
        doCalculation = calculatorViewModel::calculate,
        onClearedAmountText = calculatorViewModel::reset
      )
    }

    val settingsUiState by settingsViewModel.settingsUiState.collectAsState()

    SettingsRoute(
      windowSizeUtil = windowSizeUtil,
      userPreferences = settingsUiState,
      onBooleanSettingChanged = settingsViewModel::toggleBooleanPreference,
      onOssLicencesSettingLinkClicked = {
        OssLicensesMenuActivity.setActivityTitle(context.getString(R.string.text_settings_label_oss_licences))
        context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
      },
      onOpeningExternalUrlSettingClicked = { urlText ->
        Timber.d("[AppContent] opening external url: $urlText")
        if (urlText.isNotEmpty()) {
          CustomTabsOpener.openUrl(context, urlText)
        }
      },
      onFeedbackSettingLinkClicked = {
        Timber.d("[AppContent] opening feedback window")
        FeedbackOpener.rate(context)
      }
    )
  }
}
