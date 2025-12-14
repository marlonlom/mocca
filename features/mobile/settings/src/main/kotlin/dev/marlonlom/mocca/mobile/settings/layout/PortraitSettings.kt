/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.settings.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.CardMembership
import androidx.compose.material.icons.twotone.Feedback
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.marlonlom.mocca.core.preferences.model.UserSettings
import dev.marlonlom.mocca.mobile.settings.R
import dev.marlonlom.mocca.mobile.settings.component.ColorContrastSelector
import dev.marlonlom.mocca.mobile.settings.component.SettingLink
import dev.marlonlom.mocca.mobile.settings.component.SettingSwitch
import dev.marlonlom.mocca.mobile.settings.domain.SettingActions
import dev.marlonlom.mocca.mobile.settings.slot.SettingsGroupSlot
import dev.marlonlom.mocca.mobile.ui.component.header.HeaderBar
import dev.marlonlom.mocca.mobile.ui.component.iconbutton.ExitIconButton
import dev.marlonlom.mocca.mobile.ui.util.GeneralUtil
import dev.marlonlom.mocca.mobile.ui.window.MobileWindowSize
import dev.marlonlom.mocca.mobile.ui.R as mobileUiR

private const val OS_VERSION_S = 31

/**
 * Displays the portrait layout for the settings screen.
 *
 * @author marlonlom
 *
 * @param mobileWindowSize The current mobile window size used for layout adjustments.
 * @param userSettings The current user settings to display and modify.
 * @param actions The available actions that can be performed in the settings.
 * @param showCloseButton Whether to display the close button in the UI.
 * @param onCloseButtonClicked Callback invoked when the close button is clicked.
 * @param onBooleanSettingToggled Callback invoked when a boolean setting is toggled.
 * @param onStringSettingToggled Callback invoked when a string-based setting is changed.
 */
@Composable
internal fun PortraitSettings(
  mobileWindowSize: MobileWindowSize,
  userSettings: UserSettings,
  actions: SettingActions,
  showCloseButton: Boolean,
  onCloseButtonClicked: () -> Unit,
  onBooleanSettingToggled: (String, Boolean) -> Unit,
  onStringSettingToggled: (String, String) -> Unit,
) = Column(
  modifier = Modifier
    .fillMaxWidth()
    .background(MaterialTheme.colorScheme.surface)
    .consumeWindowInsets(WindowInsets.systemBars),
  horizontalAlignment = Alignment.CenterHorizontally,
) {
  HeaderBar(
    title = { Text(text = stringResource(mobileUiR.string.text_settings)) },
    isMedium = mobileWindowSize != MobileWindowSize.MOBILE_LANDSCAPE,
    navigationIcon = {
      if (showCloseButton) {
        ExitIconButton(onButtonClicked = onCloseButtonClicked)
      }
    },
  )
  Column(
    modifier = Modifier
      .then(
        when (mobileWindowSize) {
          MobileWindowSize.MOBILE_LANDSCAPE -> Modifier.widthIn(max = 640.dp)
          else -> Modifier.fillMaxWidth()
        },
      )
      .padding(horizontal = 20.dp)
      .verticalScroll(rememberScrollState()),
  ) {
    SettingsGroupSlot(
      headerText = R.string.text_settings_appearance,
      content = {
        FlowRow(
          maxItemsInEachRow = 2,
        ) {
          SettingSwitch(
            title = stringResource(R.string.text_dark_theme),
            subtitle = if (isSystemInDarkTheme()) stringResource(R.string.hint_dark_theme) else "",
            enabled = !isSystemInDarkTheme(),
            checked = userSettings.useDarkTheme,
            onChecked = {
              onBooleanSettingToggled("dark_theme", it)
            },
          )
          SettingSwitch(
            title = stringResource(R.string.text_dynamic_colors),
            subtitle = when {
              android.os.Build.VERSION.SDK_INT >= OS_VERSION_S -> ""
              else -> stringResource(R.string.hint_dynamic_colors)
            },
            enabled = android.os.Build.VERSION.SDK_INT >= OS_VERSION_S,
            checked = userSettings.useDynamicColor,
            onChecked = {
              onBooleanSettingToggled("dynamic_colors", it)
            },
          )
          ColorContrastSelector(
            selectedColorContrast = userSettings.colorContrast,
            slotEnabled = userSettings.useDynamicColor.not(),
            onColorContrastSelected = { onStringSettingToggled("color_contrast", it) },
          )
        }
      },
    )

    SettingsGroupSlot(
      headerText = R.string.text_settings_legal,
      content = {
        val resources = LocalResources.current
        SettingLink(
          title = stringResource(R.string.text_privacy_policy),
          onClicked = {
            actions.onOpeningExternalUrl(resources.getString(R.string.url_privacy_policy))
          },
        )
        SettingLink(
          title = stringResource(R.string.text_terms_conditions),
          onClicked = {
            actions.onOpeningExternalUrl(resources.getString(R.string.utl_terms_conditions))
          },
        )
      },
    )

    SettingsGroupSlot(
      headerText = R.string.text_settings_general,
      content = {
        SettingLink(
          title = stringResource(R.string.text_oss_licences),
          icon = {
            Icon(
              imageVector = Icons.TwoTone.CardMembership,
              tint = MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.padding(horizontal = 20.dp),
              contentDescription = null,
            )
          },
          onClicked = {
            actions.onOssLicencesDisplayed()
          },
        )
        SettingLink(
          title = stringResource(R.string.text_feedback),
          icon = {
            Icon(
              imageVector = Icons.TwoTone.Feedback,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.padding(horizontal = 20.dp),
            )
          },
          onClicked = {
            actions.onFeedbackDisplayed()
          },
        )
        SettingLink(
          title = stringResource(R.string.text_app_version),
          subtitle = GeneralUtil.getAppVersionText(LocalContext.current),
          icon = {
            Icon(
              imageVector = Icons.TwoTone.Info,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.padding(horizontal = 20.dp),
            )
          },
        )
      },
    )

    Spacer(
      Modifier
        .fillMaxWidth()
        .weight(1.0f),
    )
  }
}
