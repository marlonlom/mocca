/*
 * Copyright 2025 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.plugins.utils

import dev.marlonlom.mocca.extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * A convention plugin that applies the Koin plugin and configures it.
 * @author marlonlom
 */
class KoinPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    with(project) {
      dependencies {
        val composeBom = platform(versionCatalog().findLibrary("koin-bom").get())
        add("implementation", composeBom)
        add("implementation", versionCatalog().findBundle("koin").get())
      }
    }
  }
}
