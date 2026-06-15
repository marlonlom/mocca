/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("mocca.compose.app")
  id("mocca.android.app.wearos")
}

dependencies {

  implementation(project(":features:core:calculator"))
  implementation(project(":features:wearos:ui"))

  val composeBom = platform(libs.androidx.compose.bom)
  implementation(composeBom)
  androidTestImplementation(composeBom)

  implementation(libs.androidx.compose.ui.tooling)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.wear.compose.foundation)
  implementation(libs.androidx.wear.compose.material3)
  implementation(libs.androidx.wear.compose.navigation)
  implementation(libs.google.play.services.wearable)
}
