/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.extensions

import com.android.build.api.dsl.CommonExtension
import dev.marlonlom.mocca.configs.Config
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

internal fun Project.configureAndroidKotlin(
  extension: CommonExtension<*, *, *, *, *, *>
) {
  with(extension) {
    compileOptions {
      sourceCompatibility = Config.jvm.javaVersion
      targetCompatibility = Config.jvm.javaVersion
    }

    packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"

    dependencies {
      add("implementation", versionCatalog().findLibrary("androidx-core-ktx").get())
      add("implementation", versionCatalog().findLibrary("androidx-core-splashscreen").get())

      add("testImplementation", versionCatalog().findLibrary("junit").get())
      add("testImplementation", versionCatalog().findLibrary("kotlinx-coroutines-test").get())
      add("testImplementation", versionCatalog().findLibrary("mockk").get())

      add("androidTestImplementation", versionCatalog().findLibrary("androidx-test-espresso-core").get())
      add("androidTestImplementation", versionCatalog().findLibrary("androidx-test-ext-junit").get())
      add("androidTestImplementation", versionCatalog().findLibrary("google-truth").get())
    }
  }

  tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
      jvmTarget.set(JvmTarget.fromTarget(Config.jvm.kotlinJvm))
    }
  }
}
