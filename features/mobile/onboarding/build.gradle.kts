/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("mocca.android.lib.mobile")
  id("mocca.compose.lib")
}

android {
  namespace = "dev.marlonlom.mocca.mobile.onboarding"
}

dependencies {
  implementation(project(":features:core:ui"))
  implementation(project(":features:mobile:ui"))
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.material3.wsc)
  implementation(libs.bundles.m3.adaptive)
}
