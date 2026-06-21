/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("mocca.compose.lib")
  id("mocca.android.lib.wearos")
}

android {
  namespace = "dev.marlonlom.mocca.wearos.onboarding"
}

dependencies {
  implementation(project(":features:wearos:ui"))
}
