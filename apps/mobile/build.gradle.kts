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
  implementation(project(":features:core:preferences"))
  implementation(project(":features:mobile:onboarding"))
  implementation(project(":features:mobile:calculator-input"))
  implementation(project(":features:mobile:calculator-output"))
  implementation(project(":features:mobile:ui"))

  val composeBom = platform(libs.androidx.compose.bom)
  implementation(composeBom)
  androidTestImplementation(composeBom)

  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.browser)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.material3.wsc)
  implementation(libs.androidx.compose.ui.googlefonts)
  implementation(libs.androidx.datastore.preferences)
  implementation(libs.androidx.lifecycle.runtime.compose)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.lifecycle.viewmodel.compose)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.navigation.runtime.ktx)
  implementation(libs.androidx.window)
  implementation(libs.google.oss.licenses)
  implementation(libs.jakewharton.timber)

  androidTestImplementation(libs.androidx.window.testing)
}
