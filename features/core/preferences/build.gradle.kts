/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("mocca.android.lib.mobile")
  id("mocca.compose.lib")
}

android {
  namespace = "dev.marlonlom.mocca.core.preferences"
}

dependencies {
  implementation(libs.androidx.datastore.preferences)
}
