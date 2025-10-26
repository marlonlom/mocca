/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.settings.di

import dev.marlonlom.mocca.mobile.settings.domain.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Settings mobile ui Koin module.
 *
 * @author marlonlom
 */
val settingsMobileKoinModule = module {
  viewModelOf(::SettingsViewModel) bind SettingsViewModel::class
}
