/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.plugins.libs

import com.android.build.api.dsl.LibraryExtension
import dev.marlonlom.mocca.configs.Config
import dev.marlonlom.mocca.extensions.configureMobileAndroidKotlin
import dev.marlonlom.mocca.extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * A convention plugin for configuring Android Mobile libraries.
 *
 * @author marlonlom
 */
class MobileAndroidLibPlugin : Plugin<Project> {

  override fun apply(project: Project) {
    with(project) {
      pluginManager.apply("mocca.android.lib.common")

      extensions.configure<LibraryExtension> {
        namespace = Config.mobile.nameSpace
        compileSdk = Config.mobile.compileSdkVersion

        defaultConfig {
          minSdk = Config.mobile.minSdkVersion
          testInstrumentationRunner = Config.mobile.testInstrumentationRunner
        }

        configureMobileAndroidKotlin(this)

        dependencies {
          val vc = versionCatalog()
          add("implementation", vc.findLibrary("androidx-compose-material3").get())
          add("implementation", vc.findLibrary("androidx-compose-material3-wsc").get())
          add("implementation", vc.findBundle("m3-adaptive").get())
        }
      }
    }
  }

}
