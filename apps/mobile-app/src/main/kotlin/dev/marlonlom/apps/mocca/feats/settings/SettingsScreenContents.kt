/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.apps.mocca.feats.settings

import android.os.Build
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Feedback
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.OpenInNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alorma.compose.settings.storage.base.rememberBooleanSettingState
import com.alorma.compose.settings.ui.SettingsMenuLink
import com.alorma.compose.settings.ui.SettingsSwitch
import dev.marlonlom.apps.mocca.R
import dev.marlonlom.apps.mocca.ui.util.WindowSizeUtil

/**
 * Settings headline section for lazy vertical grid.
 *
 * @author marlonlom
 *
 */
internal fun LazyGridScope.settingsHeadline() {
  item(span = { GridItemSpan(maxLineSpan) }) {
    Text(
      text = stringResource(R.string.text_settings_headline),
      style = MaterialTheme.typography.headlineLarge,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .fillMaxWidth()
        .paddingFromBaseline(top = 40.dp, bottom = 20.dp)
    )
  }
}

/**
 * Dark themeSetting slot for lazy vertical grid.
 *
 * @author marlonlom
 *
 * @param windowSizeUtil Window size utility.
 * @param userPreferences User preferences.
 * @param onBooleanSettingChanged Action for dark theme setting changed.
 */
internal fun LazyGridScope.darkThemeSettingSlot(
  windowSizeUtil: WindowSizeUtil,
  userPreferences: UserPreferences,
  onBooleanSettingChanged: (String, Boolean) -> Unit
) {
  item(span = { GridItemSpan(if (windowSizeUtil.isMobileLandscape) 1 else maxCurrentLineSpan) }) {
    val darkThemeBooleanState = rememberBooleanSettingState(
      if (isSystemInDarkTheme()) true else userPreferences.darkTheme
    )
    SettingsSwitch(
      modifier = Modifier.fillMaxWidth(),
      title = { Text(stringResource(R.string.text_settings_label_dark_theme)) },
      subtitle = {
        if (isSystemInDarkTheme()) {
          Text(stringResource(R.string.text_settings_hint_system_dark_theme))
        }
      },
      state = darkThemeBooleanState,
      onCheckedChange = { toggle ->
        onBooleanSettingChanged("dark_theme", toggle)
      },
      enabled = !isSystemInDarkTheme()
    )
  }
}

/**
 * Dynamic colorsSetting slot for lazy vertical grid.
 *
 * @author marlonlom
 *
 * @param userPreferences User preferences.
 * @param onBooleanSettingChanged Action for dynamic colors setting changed.
 */
internal fun LazyGridScope.dynamicColorsSettingSlot(
  userPreferences: UserPreferences,
  onBooleanSettingChanged: (String, Boolean) -> Unit
) {
  item(span = { GridItemSpan(maxCurrentLineSpan) }) {
    SettingsSwitch(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 0.dp),
      title = {
        Text(stringResource(R.string.text_settings_label_dynamic_color))
      },
      subtitle = {
        Text(
          text = stringResource(R.string.text_settings_hint_dynamic_color),
          fontStyle = FontStyle.Italic,
          style = MaterialTheme.typography.labelSmall
        )
      },
      state = rememberBooleanSettingState(userPreferences.dynamicColors),
      onCheckedChange = { toggle ->
        onBooleanSettingChanged("dynamic_colors", toggle)
      },
      enabled = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    )
  }
}

/**
 * Settings group title slot section for lazy vertical grid.
 *
 * @author marlonlom
 *
 * @param titleTextRes Title text string resource id.
 */
internal fun LazyGridScope.settingsGroupTitle(
  @StringRes titleTextRes: Int
) {
  item(span = { GridItemSpan(maxLineSpan) }) {
    Text(
      text = stringResource(titleTextRes),
      style = MaterialTheme.typography.titleMedium,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .fillMaxWidth()
        .paddingFromBaseline(top = 40.dp, bottom = 0.dp)
    )
  }
}

/**
 * Url about Efecty setting slot for lazy vertical grid.
 *
 * @author marlonlom
 *
 * @param windowSizeUtil Window size utility.
 * @param onOpeningExternalUrlSettingClicked Action for opening external url.
 * @param userPreferences User preferences.
 */
internal fun LazyGridScope.aboutEfectySettingSlot(
  windowSizeUtil: WindowSizeUtil,
  onOpeningExternalUrlSettingClicked: (String) -> Unit,
  userPreferences: UserPreferences
) {
  item(span = {
    GridItemSpan(
      if (windowSizeUtil.isMobileLandscape) 1 else maxCurrentLineSpan
    )
  }) {
    SettingsMenuLink(
      modifier = Modifier.fillMaxWidth(),
      title = {
        Text(stringResource(R.string.text_settings_label_about_efecty))
      },
      icon = {
        Icon(
          imageVector = Icons.Rounded.OpenInNew,
          contentDescription = null
        )
      },
      subtitle = {
        Text(
          text = stringResource(R.string.text_settings_hint_about_efecty),
          fontStyle = FontStyle.Italic,
          style = MaterialTheme.typography.labelSmall
        )
      },
      onClick = {
        onOpeningExternalUrlSettingClicked(userPreferences.aboutEfectyUrl)
      }
    )
  }
}

/**
 * Third party licencesSetting slot for lazy vertical grid.
 *
 * @author marlonlom
 *
 * @param onOssLicencesSettingLinkClicked Action for oss licences setting clicked.
 */
internal fun LazyGridScope.ossLicencesSettingSlot(
  onOssLicencesSettingLinkClicked: () -> Unit
) {
  item(span = { GridItemSpan(maxCurrentLineSpan) }) {
    SettingsMenuLink(
      modifier = Modifier.fillMaxWidth(),
      title = {
        Text(stringResource(R.string.text_settings_label_oss_licences))
      },
      onClick = {
        onOssLicencesSettingLinkClicked()
      }
    )
  }
}


/**
 * Application version setting slot.
 *
 * @author marlonlom
 *
 * @param userPreferences User preferences.
 */
internal fun LazyGridScope.appVersionSettingSlot(
  userPreferences: UserPreferences
) {
  item(span = { GridItemSpan(maxCurrentLineSpan) }) {
    SettingsMenuLink(
      modifier = Modifier.fillMaxWidth(),
      title = {
        Text(stringResource(R.string.text_settings_label_app_version))
      },
      subtitle = {
        Text(
          text = userPreferences.appVersion,
          style = MaterialTheme.typography.labelMedium
        )
      },
      icon = {
        Icon(
          imageVector = Icons.Rounded.Info,
          contentDescription = null
        )
      },
      onClick = { }
    )
  }
}

/**
 * Feedback slot for lazy vertical grid.
 *
 * @author marlonlom
 *
 * @param onFeedbackSettingLinkClicked
 */
internal fun LazyGridScope.feedbackSettingSlot(
  onFeedbackSettingLinkClicked: () -> Unit
) {
  item(span = { GridItemSpan(maxCurrentLineSpan) }) {
    SettingsMenuLink(
      modifier = Modifier.fillMaxWidth(),
      title = {
        Text(stringResource(R.string.text_settings_label_feedback))
      },
      icon = {
        Icon(
          imageVector = Icons.Rounded.Feedback,
          contentDescription = null
        )
      },
      onClick = {
        onFeedbackSettingLinkClicked()
      }
    )
  }
}
