/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("mocca.compose.lib")
  id("mocca.android.lib.mobile")
  id("kotlin-parcelize")
}

android {
  namespace = "dev.marlonlom.mocca.mobile.ui"
}

dependencies {
  implementation(libs.androidx.browser)
}
