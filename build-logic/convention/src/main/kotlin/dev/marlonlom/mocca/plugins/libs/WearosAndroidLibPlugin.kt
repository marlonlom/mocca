/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.plugins.libs

import com.android.build.api.dsl.LibraryExtension
import dev.marlonlom.mocca.configs.Config
import dev.marlonlom.mocca.extensions.configureWearosAndroidKotlin
import dev.marlonlom.mocca.extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * A convention plugin for configuring Android WearOS libraries.
 *
 * @author marlonlom
 */
class WearosAndroidLibPlugin : Plugin<Project> {

  override fun apply(project: Project) {
    with(project) {
      pluginManager.apply("mocca.android.lib.common")
      dependencies {
        val vc = versionCatalog()
        add("implementation", vc.findLibrary("androidx-wear-compose-foundation").get())
        add("implementation", vc.findLibrary("androidx-wear-compose-material3").get())
      }
      extensions.configure<LibraryExtension> {
        namespace = Config.wearos.nameSpace
        compileSdk = Config.wearos.compileSdkVersion

        defaultConfig {
          minSdk = Config.wearos.minSdkVersion
          testInstrumentationRunner = Config.wearos.testInstrumentationRunner
        }

        configureWearosAndroidKotlin(this)
      }
    }
  }

}
