/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
  extension: CommonExtension<*, *, *, *, *, *>,
) {
  val vc = versionCatalog()
  with(extension) {
    buildFeatures.compose = true
    dependencies {
      val composeBom = platform(vc.findLibrary("androidx-compose-bom").get())
      add("implementation", composeBom)
      add("androidTestImplementation", composeBom)

      add("implementation", vc.findLibrary("androidx-activity-compose").get())
      add("implementation", vc.findLibrary("androidx-compose-material-icons-extended").get())
      add("implementation", vc.findLibrary("androidx-compose-ui").get())
      add("implementation", vc.findLibrary("androidx-compose-ui-graphics").get())
      add("implementation", vc.findLibrary("androidx-compose-ui-tooling-preview").get())

      add("androidTestImplementation", vc.findLibrary("androidx-compose-ui-test-junit4").get())

      add("debugImplementation", vc.findLibrary("androidx-compose-ui-tooling").get())
      add("debugImplementation", vc.findLibrary("androidx-compose-ui-test-manifest").get())

    }
  }
}
