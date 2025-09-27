/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("mocca.android.lib.mobile")
  id("mocca.compose.lib")
  id("kotlin-parcelize")
}

android {
  namespace = "dev.marlonlom.mocca.mobile.ui"
}

dependencies {
  implementation(project(":features:core:ui"))
  implementation(libs.androidx.browser)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.material3.wsc)
  implementation(libs.bundles.m3.adaptive)
}
