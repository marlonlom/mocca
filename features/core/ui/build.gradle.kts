import dev.marlonlom.mocca.configs.Config

/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("com.android.library")
  id("kotlin-android")
  id("mocca.compose.lib")
}

android {
  namespace = "dev.marlonlom.mocca.core.ui"
  compileSdk = Config.mobile.compileSdkVersion
}
