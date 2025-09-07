/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.di

import dev.marlonlom.mocca.core.preferences.di.preferencesKoinModule
import dev.marlonlom.mocca.feats.settings.SettingsViewModel
import dev.marlonlom.mocca.ui.main.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Main mobile application Koin module.
 *
 * @author marlonlom
 */
val appKoinModule = module {
  includes(preferencesKoinModule)
  viewModelOf(::SettingsViewModel)
  viewModelOf(::MainViewModel)
}
