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
 * A convention plugin specifically for configuring mobile Android application conventions.
 *
 * @author marlonlom
 */
class MobileAndroidAppPlugin : Plugin<Project> {

  override fun apply(project: Project) {
    with(project) {
      pluginManager.apply("mocca.android.app.common")
      extensions.configure<ApplicationExtension> {
        namespace = Config.mobile.nameSpace
        compileSdk = Config.mobile.compileSdkVersion

        defaultConfig.apply {
          applicationId = Config.mobile.applicationId
          minSdk = Config.mobile.minSdkVersion
          targetSdk = Config.mobile.targetSdkVersion
          versionCode = Config.mobile.versionCode
          versionName = Config.mobile.versionName
          testInstrumentationRunner = Config.mobile.testInstrumentationRunner
        }
      }
    }
  }
}
