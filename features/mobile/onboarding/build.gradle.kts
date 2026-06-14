/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("mocca.compose.lib")
  id("mocca.android.lib.mobile")
}

android {
  namespace = "dev.marlonlom.mocca.mobile.onboarding"
}

dependencies {
  implementation(project(":features:mobile:ui"))
}
