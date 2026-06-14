/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("mocca.android.lib.mobile")
  id("mocca.compose.lib")
}

android {
  namespace = "dev.marlonlom.mocca.mobile.calculator.history"
}

dependencies {
  implementation(project(":features:core:calculator"))
  implementation(project(":features:core:database"))
  implementation(project(":features:mobile:ui"))
}
