/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.datastore.preferences.preferencesDataStore
import dev.marlonlom.mocca.feats.settings.SettingsRepository
import dev.marlonlom.mocca.feats.settings.UserPreferences
import timber.log.Timber

/** Datastore instance for user settings. */
val Context.dataStore by preferencesDataStore("mocca_settings")

/**
 * Mocca application class.
 *
 * @author marlonlom
 *
 */
class MoccaApp : Application() {

  override fun onCreate() {
    super.onCreate()
    setupTimber()
    saveDefaultSettings()
  }

  private fun saveDefaultSettings() {
    SettingsRepository(this.dataStore).saveDefaults(
      UserPreferences(
        aboutEfectyUrl = this.getString(R.string.text_settings_efecty_about_url),
        appVersion = BuildConfig.VERSION_NAME,
        darkTheme = false,
        dynamicColors = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
      )
    )
  }


  /** Setup timber logging feature. */
  private fun setupTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

}
