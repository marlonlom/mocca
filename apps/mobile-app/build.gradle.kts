/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  id("com.google.android.gms.oss-licenses-plugin")
}

android {
  namespace = "dev.marlonlom.apps.mocca"
  compileSdk = 34

  defaultConfig {
    applicationId = "dev.marlonlom.apps.mocca"
    minSdk = 24
    //noinspection EditedTargetSdkVersion
    targetSdk = 34
    versionCode = 1
    versionName = "1.0.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
    create("benchmark") {
      initWith(buildTypes.getByName("release"))
      signingConfig = signingConfigs.getByName("debug")
      matchingFallbacks += listOf("release")
      isDebuggable = false
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.majorVersion
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.8"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

  implementation(project(":features:core:calculator"))

  val composeBom = platform(libs.androidx.compose.bom)
  implementation(composeBom)
  androidTestImplementation(composeBom)

  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.browser)
  implementation(libs.androidx.compose.material.icons.extended)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.material3.wsc)
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.googlefonts)
  implementation(libs.androidx.compose.ui.graphics)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.core.splashscreen)
  implementation(libs.androidx.datastore.preferences)
  implementation(libs.androidx.lifecycle.runtime.compose)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.lifecycle.viewmodel.compose)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.navigation.runtime.ktx)
  implementation(libs.alorma.compose.settings.m3)
  implementation(libs.google.oss.licenses)
  implementation(libs.jakewharton.timber)

  testImplementation(libs.junit)

  androidTestImplementation(libs.androidx.compose.ui.test.junit4)
  androidTestImplementation(libs.androidx.test.ext.junit)
  androidTestImplementation(libs.androidx.test.espresso.core)
  androidTestImplementation(libs.google.truth)

  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.test.manifest)
}
