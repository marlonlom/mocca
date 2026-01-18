/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

import dev.marlonlom.mocca.configs.Config
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
  id("com.android.library")
  id("mocca.compose.lib")
}

android {
  namespace = "dev.marlonlom.mocca.core.ui"
  compileSdk = Config.mobile.compileSdkVersion

  compileOptions {
    sourceCompatibility = Config.jvm.javaVersion
    targetCompatibility = Config.jvm.javaVersion
  }
}

tasks.withType<KotlinJvmCompile>().configureEach {
  compilerOptions {
    jvmTarget.set(JvmTarget.fromTarget(Config.jvm.kotlinJvm))
  }
}
