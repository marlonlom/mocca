/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
  id("mocca.android.app.mobile")
  id("mocca.compose.app")
  id("com.google.android.gms.oss-licenses-plugin")
}

dependencies {

  implementation(project(":features:core:calculator"))
  implementation(project(":features:core:database"))
  implementation(project(":features:core:preferences"))
  implementation(project(":features:mobile:calculator-fees"))
  implementation(project(":features:mobile:calculator-history"))
  implementation(project(":features:mobile:calculator-input"))
  implementation(project(":features:mobile:calculator-output"))
  implementation(project(":features:mobile:onboarding"))
  implementation(project(":features:mobile:settings"))
  implementation(project(":features:mobile:ui"))
}
