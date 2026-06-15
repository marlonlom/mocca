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
        val vc = versionCatalog()
        val koinBom = platform(vc.findLibrary("koin-bom").get())
        add("implementation", koinBom)
        add("implementation", vc.findBundle("koin").get())
      }
    }
  }
}
