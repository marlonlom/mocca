/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.plugins.libs

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

/**
 * A convention plugin for configuring common Android libraries.
 *
 * @author marlonlom
 */
class CommonAndroidLibPlugin : Plugin<Project> {

  override fun apply(project: Project) {
    with(project) {
      with(pluginManager) {
        apply("com.android.library")
        apply("mocca.koin")
        apply("mocca.spotless")
      }

      dependencies.add("implementation", project(":features:core:ui"))

      extensions.configure<LibraryExtension> {
        defaultConfig.vectorDrawables.useSupportLibrary = true
        buildTypes {
          release {
            isMinifyEnabled = false
            proguardFiles(
              getDefaultProguardFile("proguard-android-optimize.txt"),
              "proguard-rules.pro",
            )
          }
        }
      }

      tasks.withType<Test> {
        jvmArgs("-XX:+EnableDynamicAgentLoading")
      }
    }
  }

}
