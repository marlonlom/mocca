/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("mocca.android.lib.wearos")
  id("mocca.compose.lib")
}

android {
  namespace = "dev.marlonlom.mocca.wearos.calculator.input"
}

dependencies {
  implementation(project(":features:core:calculator"))
  implementation(project(":features:wearos:ui"))
}
