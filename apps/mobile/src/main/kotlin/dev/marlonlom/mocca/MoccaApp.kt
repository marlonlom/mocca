/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca

import android.app.Application
import dev.marlonlom.mocca.di.appKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

/**
 * Mocca application class.
 *
 * @author marlonlom
 *
 */
class MoccaApp : Application() {

  override fun onCreate() {
    super.onCreate()
    this.setupKoin()
    setupTimber()
  }

  /** Setup Koin module context feature. */
  private fun setupKoin() {
    startKoin {
      androidContext(this@MoccaApp)
      androidLogger(Level.DEBUG)
      modules(appKoinModule)
    }
  }

  /** Setup timber logging feature. */
  private fun setupTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}
