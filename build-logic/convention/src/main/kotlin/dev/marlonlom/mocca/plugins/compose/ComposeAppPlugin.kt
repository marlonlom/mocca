/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.plugins.compose

import com.android.build.api.dsl.ApplicationExtension
import dev.marlonlom.mocca.extensions.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * A convention plugin for configuring Jetpack Compose applications.
 *
 * @author marlonlom
 *
 */
class ComposeAppPlugin : Plugin<Project> {

  override fun apply(project: Project) {
    with(project) {
      pluginManager.apply("mocca.compose.common")
      pluginManager.apply("com.android.application")

      extensions.getByType<ApplicationExtension>().also {
        configureAndroidCompose(it)
      }
    }
  }

}
