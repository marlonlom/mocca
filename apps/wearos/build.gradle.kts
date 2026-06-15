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
}
