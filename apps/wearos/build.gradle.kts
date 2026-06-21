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
  implementation(project(":features:wearos:calculator-fees"))
  implementation(project(":features:wearos:calculator-input"))
  implementation(project(":features:wearos:calculator-output"))
  implementation(project(":features:wearos:onboarding"))
  implementation(project(":features:wearos:ui"))
}
