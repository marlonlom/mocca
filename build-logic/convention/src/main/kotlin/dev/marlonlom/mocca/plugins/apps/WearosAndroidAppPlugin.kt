/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.plugins.apps

import com.android.build.api.dsl.ApplicationExtension
import dev.marlonlom.mocca.configs.Config
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * A convention plugin specifically for configuring wearos Android application conventions.
 *
 * @author marlonlom
 */
class WearosAndroidAppPlugin : Plugin<Project> {

  override fun apply(project: Project) {
    with(project) {
      pluginManager.apply("mocca.android.app.common")
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
      }
    }
  }
}
