/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("mocca.compose.lib")
  id("mocca.android.lib.wearos")
  id("kotlin-parcelize")
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "dev.marlonlom.mocca.wearos.ui"
}

dependencies {
  implementation(libs.androidx.nav3.runtime)
  implementation(libs.androidx.nav3.ui)
  implementation(libs.androidx.wear.compose.nav3)
  implementation(libs.kotlinx.serialization.json)
}
