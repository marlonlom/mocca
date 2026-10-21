/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("mocca.android.lib.wearos")
  id("mocca.compose.lib")
}

android {
  namespace = "dev.marlonlom.mocca.wearos.calculator.tile"
}

dependencies {
  implementation(project(":features:core:calculator"))
  implementation(libs.androidx.wear.tiles)
  implementation(libs.androidx.wear.protolayout)
  implementation(libs.androidx.wear.protolayout.material)
  implementation(libs.androidx.wear.protolayout.material3)
  implementation(libs.androidx.wear.protolayout.expression)
  debugImplementation(libs.androidx.wear.tiles.renderer)
  testImplementation(libs.androidx.wear.tiles.testing)
}
