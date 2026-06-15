/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.extensions

import com.android.build.api.dsl.CommonExtension
import dev.marlonlom.mocca.configs.Config
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

/**
 * Extension function on [DependencyHandlerScope] to automatically register android ui testing
 * dependencies from the project's version catalog.
 *
 * It iterates through a predefined list of test library aliases
 * and safely adds them to the "androidTestImplementation" configuration if they exist in the catalog.
 *
 * @param project The [Project] instance used to fetch the version catalog.
 */
private fun DependencyHandlerScope.addAndroidTestDependencies(project: Project) {
  val catalog = project.versionCatalog()
  listOf("google-truth", "androidx-test-espresso-core", "androidx-test-ext-junit").forEach { alias ->
    catalog.findLibrary(alias).ifPresent { library ->
      add("androidTestImplementation", library)
    }
  }
}

/**
 * Extension function on [DependencyHandlerScope] to automatically register core unit testing
 * dependencies from the project's version catalog.
 *
 * It iterates through a predefined list of test library aliases
 * and safely adds them to the "testImplementation" configuration if they exist in the catalog.
 *
 * @param project The [Project] instance used to fetch the version catalog.
 */
private fun DependencyHandlerScope.addTestDependencies(project: Project) {
  val catalog = project.versionCatalog()
  listOf("junit", "kotlinx-coroutines-test", "mockk").forEach { alias ->
    catalog.findLibrary(alias).ifPresent { library ->
      add("testImplementation", library)
    }
  }
}

/**
 * Configures common Kotlin and JVM settings for Android modules.
 *
 * This function standardizes the Kotlin compiler options, JVM target versions,
 * and Java toolchain settings across Application and Library extensions.
 *
 * @param extension The Android [CommonExtension] to be configured with Kotlin-specific settings.
 */
private fun Project.configureCommonAndroidKotlin(
  extension: CommonExtension
) {
  with(extension) {
    compileOptions.sourceCompatibility = Config.jvm.javaVersion
    compileOptions.targetCompatibility = Config.jvm.javaVersion

    packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"

    dependencies {
      add("implementation", versionCatalog().findLibrary("androidx-core-splashscreen").get())
      add("implementation", versionCatalog().findLibrary("androidx-lifecycle-runtime-ktx").get())
      addTestDependencies(this@configureCommonAndroidKotlin)
      addAndroidTestDependencies(this@configureCommonAndroidKotlin)
    }
  }

  tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
      jvmTarget.set(JvmTarget.fromTarget(Config.jvm.kotlinJvm))
    }
  }
}

/**
 * Configures common Kotlin and JVM settings for Mobile modules.
 *
 * @author marlonlom
 *
 * @param extension The Android [CommonExtension] to be configured with Kotlin-specific settings.
 */
internal fun Project.configureMobileAndroidKotlin(
  extension: CommonExtension
) {
  configureCommonAndroidKotlin(extension)
  dependencies {
    add("implementation", versionCatalog().findLibrary("androidx-core-ktx").get())
  }
}

/**
 * Configures common Kotlin and JVM settings for Wearos modules.
 *
 * @author marlonlom
 *
 * @param extension The Android [CommonExtension] to be configured with Kotlin-specific settings.
 */
internal fun Project.configureWearosAndroidKotlin(
  extension: CommonExtension
) {
  configureCommonAndroidKotlin(extension)
}
