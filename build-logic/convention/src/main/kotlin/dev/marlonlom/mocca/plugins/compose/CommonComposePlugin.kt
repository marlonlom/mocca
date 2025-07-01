/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.plugins.compose

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A convention plugin for configuring common Jetpack Compose settings across projects.
 *
 * @author marlonlom
 *
 */
class CommonComposePlugin : Plugin<Project> {

  override fun apply(project: Project) {
    with(project) {
      pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
    }
  }

}
