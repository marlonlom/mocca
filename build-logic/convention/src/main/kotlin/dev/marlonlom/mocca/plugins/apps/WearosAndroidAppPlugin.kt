/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.plugins.apps

import com.android.build.api.dsl.ApplicationExtension
import dev.marlonlom.mocca.configs.Config
import dev.marlonlom.mocca.extensions.configureWearosAndroidKotlin
import dev.marlonlom.mocca.extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * A convention plugin specifically for configuring wearos Android application conventions.
 *
 * @author marlonlom
 */
class WearosAndroidAppPlugin : Plugin<Project> {

  override fun apply(project: Project) {
    with(project) {
      pluginManager.apply("mocca.android.app.common")
      val vc = versionCatalog()
      dependencies {
        add("implementation", vc.findLibrary("androidx-wear-compose-foundation").get())
        add("implementation", vc.findLibrary("androidx-wear-compose-material3").get())
        add("implementation", vc.findLibrary("androidx-wear-compose-navigation").get())
        add("implementation", vc.findLibrary("google-play-services-wearable").get())
      }
      extensions.configure<ApplicationExtension> {
        namespace = Config.wearos.nameSpace
        compileSdk = Config.wearos.compileSdkVersion

        defaultConfig.apply {
          applicationId = Config.wearos.applicationId
          minSdk = Config.wearos.minSdkVersion
          targetSdk = Config.wearos.targetSdkVersion
          versionCode = Config.wearos.versionCode
          versionName = Config.wearos.versionName
          testInstrumentationRunner = Config.wearos.testInstrumentationRunner
        }

        configureWearosAndroidKotlin(this)
      }
    }
  }
}
