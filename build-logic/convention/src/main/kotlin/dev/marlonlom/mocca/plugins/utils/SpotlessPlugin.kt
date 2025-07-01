/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.plugins.utils

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.ProjectLayout
import org.gradle.kotlin.dsl.configure

/**
 * A convention plugin that applies the Spotless plugin and configures it.
 *
 * @author marlonlom
 */
class SpotlessPlugin : Plugin<Project> {

  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply("com.diffplug.spotless")
      extensions.configure<SpotlessExtension> {
        configureKotlin(layout, rootProject)
        configureKotlinScript(rootProject)
        configureXml(rootProject)
      }
    }
  }
}

/**
 * Configures Spotless for Kotlin source files.
 *
 * @param layout The project layout to use for configuring file paths.
 * @param rootProject The root project, useful for accessing root-level configurations or files.
 */
private fun SpotlessExtension.configureKotlin(layout: ProjectLayout, rootProject: Project) {
  kotlin {
    target("**/*.kt")
    targetExclude("${layout.buildDirectory}/**/*.kt")
    ktlint().editorConfigOverride(
      mapOf(
        "android" to "true",
        "charset" to "utf-8",
        "end_of_line" to "lf",
        "indent_style" to "space",
        "indent_size" to 2,
        "insert_final_newline" to true,
        "max_line_length" to 120,
        "trim_trailing_whitespace" to true,
        "ij_continuation_indent_size" to 2,
        "ij_kotlin_name_count_to_use_star_import" to 999,
        "ij_kotlin_name_count_to_use_star_import_for_members" to 999,
        "ktlint_function_naming_ignore_when_annotated_with" to "Composable"
      ),
    )
    trimTrailingWhitespace()
    leadingTabsToSpaces()
    endWithNewline()
    licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
  }
}

/**
 * Configures Spotless for Kotlin script files (e.g., build.gradle.kts).
 *
 * @param rootProject The root project, useful for accessing root-level configurations or files.
 */
private fun SpotlessExtension.configureKotlinScript(rootProject: Project) {
  kotlinGradle {
    target("**/*.kts")
    targetExclude("**/build/**/*.kts")
    ktlint().editorConfigOverride(
      mapOf(
        "charset" to "utf-8",
        "end_of_line" to "lf",
        "indent_style" to "space",
        "indent_size" to 2,
        "insert_final_newline" to true,
        "max_line_length" to 120,
        "trim_trailing_whitespace" to true,
      )
    )
    trimTrailingWhitespace()
    leadingTabsToSpaces()
    endWithNewline()
    licenseHeaderFile(rootProject.file("spotless/copyright.kts"), "(^(?![\\/ ]\\*).*$)")
  }
}

/**
 * Configures Spotless for XML files.
 *
 * @param rootProject The root project, useful for accessing root-level configurations or files.
 */
private fun SpotlessExtension.configureXml(rootProject: Project) {
  format("xml") {
    target("**/*.xml")
    targetExclude("**/build/**/*.xml")
    trimTrailingWhitespace()
    leadingTabsToSpaces()
    endWithNewline()
    licenseHeaderFile(rootProject.file("spotless/copyright.xml"), "(<[^!?])")
  }
}
