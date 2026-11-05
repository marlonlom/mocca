/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("mocca.android.lib.wearos")
  id("mocca.compose.lib")
}

android {
  namespace = "dev.marlonlom.mocca.wearos.calculator.widget"
}

dependencies {
  implementation(project(":features:core:calculator"))
  implementation(libs.androidx.compose.remote.creation.compose)
  implementation(libs.androidx.compose.remote.core)
  implementation(libs.androidx.glance.wear)
  implementation(libs.androidx.glance.wear.core)
  implementation(libs.androidx.glance.wear.tooling.preview)
  implementation(libs.androidx.wear.compose.remote.material3)
  debugImplementation(libs.androidx.compose.remote.tooling.preview)
  debugImplementation(libs.androidx.wear.compose.ui.tooling)
  debugImplementation(libs.androidx.wear.tiles.tooling.preview)
  debugImplementation(libs.androidx.wear.tiles.renderer)
}
