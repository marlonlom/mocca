/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

plugins {
  alias(libs.plugins.android.test)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "dev.marlonlom.mocca.macrobenchmarks.wearos"
  compileSdk = 35

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.majorVersion
  }

  defaultConfig {
    minSdk = 24
    //noinspection OldTargetApi
    targetSdk = 35

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    // This benchmark buildType is used for benchmarking, and should function like your
    // release build (for example, with minification on). It"s signed with a debug key
    // for easy local/CI testing.
    create("benchmark") {
      isDebuggable = true
      signingConfig = getByName("debug").signingConfig
      matchingFallbacks += listOf("release")
    }
  }

  targetProjectPath = ":apps:wearos"
  experimentalProperties["android.experimental.self-instrumenting"] = true
}

dependencies {
  implementation(libs.androidx.benchmark.macro.junit4)
  implementation(libs.androidx.test.ext.junit)
  implementation(libs.androidx.test.espresso.core)
  implementation(libs.androidx.test.uiautomator)
}

androidComponents {
  beforeVariants(selector().all()) {
    it.enable = it.buildType == "benchmark"
  }
}
