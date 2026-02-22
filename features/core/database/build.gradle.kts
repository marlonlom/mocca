/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("mocca.android.lib.mobile")
  id("mocca.compose.lib")
  alias(libs.plugins.google.devtools.ksp)
}

android {
  namespace = "dev.marlonlom.mocca.core.database"

  defaultConfig {
    consumerProguardFiles("consumer-rules.pro")
  }
}

dependencies {
  implementation(libs.androidx.appcompat)
  implementation(libs.bundles.database.room)

  ksp(libs.androidx.room.compiler)
}
