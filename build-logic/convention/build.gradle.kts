/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
  `kotlin-dsl`
}

group = "dev.marlonlom.mocca.buildlogic"
version = "1.0.0"

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinJvmCompile>().configureEach {
  compilerOptions {
    jvmTarget.set(JvmTarget.fromTarget(JavaVersion.VERSION_17.majorVersion))
  }
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.compose.compiler.gradlePlugin)
  compileOnly(libs.spotless.gradlePlugin)
}

gradlePlugin {
  plugins {
    /* android app module conventions */
    register("androidCommonApp") {
      id = "mocca.android.app.common"
      implementationClass = "dev.marlonlom.mocca.plugins.apps.CommonAndroidAppPlugin"
    }
    register("androidMobileApp") {
      id = "mocca.android.app.mobile"
      implementationClass = "dev.marlonlom.mocca.plugins.apps.MobileAndroidAppPlugin"
    }
    register("androidWearosApp") {
      id = "mocca.android.app.wearos"
      implementationClass = "dev.marlonlom.mocca.plugins.apps.WearosAndroidAppPlugin"
    }
    /* android compose modules */
    register("composeCommonApp") {
      id = "mocca.compose.common"
      implementationClass = "dev.marlonlom.mocca.plugins.compose.CommonComposePlugin"
    }
    register("composeApp") {
      id = "mocca.compose.app"
      implementationClass = "dev.marlonlom.mocca.plugins.compose.ComposeAppPlugin"
    }
    register("composeLib") {
      id = "mocca.compose.lib"
      implementationClass = "dev.marlonlom.mocca.plugins.compose.ComposeLibPlugin"
    }
    /* android library modules */
    register("androidCommonLib") {
      id = "mocca.android.lib.common"
      implementationClass = "dev.marlonlom.mocca.plugins.libs.CommonAndroidLibPlugin"
    }
    register("androidMobileLib") {
      id = "mocca.android.lib.mobile"
      implementationClass = "dev.marlonlom.mocca.plugins.libs.MobileAndroidLibPlugin"
    }
    /* utility module conventions */
    register("spotless") {
      id = "mocca.spotless"
      implementationClass = "dev.marlonlom.mocca.plugins.utils.SpotlessPlugin"
    }
  }
}
