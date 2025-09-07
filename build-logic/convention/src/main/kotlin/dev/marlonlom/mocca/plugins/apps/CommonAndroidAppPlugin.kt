/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.plugins.apps

import com.android.build.api.dsl.ApplicationExtension
import dev.marlonlom.mocca.extensions.configureAndroidKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

/**
 * A convention plugin that applies common configurations for Android application modules.
 *
 * @author marlonlom
 */
class CommonAndroidAppPlugin : Plugin<Project> {

  override fun apply(project: Project) {
    with(project) {
      with(pluginManager) {
        apply("com.android.application")
        apply("kotlin-android")
        apply("mocca.koin")
        apply("mocca.spotless")
      }
      dependencies.add("implementation", project(":features:core:ui"))
      extensions.configure<ApplicationExtension> {
        defaultConfig.vectorDrawables.useSupportLibrary = true
        buildFeatures.buildConfig = true
        configureAndroidKotlin(this)

        buildTypes {

          getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
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
      }

      tasks.withType<Test> {
        jvmArgs("-XX:+EnableDynamicAgentLoading")
      }
    }
  }

}
