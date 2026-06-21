/*
 * Copyright 2026 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.plugins.benchmarks

import com.android.build.api.dsl.TestExtension
import com.android.build.api.variant.TestAndroidComponentsExtension
import dev.marlonlom.mocca.configs.Config
import dev.marlonlom.mocca.extensions.versionCatalog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

/**
 * A convention plugin for configuring Android Macrobenchmark modules.
 *
 * @author marlonlom
 */
class AndroidMacroBenchmarkPlugin : Plugin<Project> {

  @Suppress("UnstableApiUsage")
  override fun apply(project: Project) {
    with(project) {
      with(pluginManager) {
        apply("com.android.test")
        apply("mocca.spotless")
      }

      extensions.configure<TestExtension> {
        compileSdk = Config.mobile.compileSdkVersion

        compileOptions {
          sourceCompatibility = Config.jvm.javaVersion
          targetCompatibility = Config.jvm.javaVersion
        }

        defaultConfig {
          minSdk = Config.mobile.minSdkVersion
          targetSdk = Config.mobile.targetSdkVersion
          testInstrumentationRunner = Config.mobile.testInstrumentationRunner
        }

        buildTypes {
          create("benchmark") {
            isDebuggable = true
            signingConfig = getByName("debug").signingConfig
            matchingFallbacks += listOf("release")
          }
        }

        experimentalProperties["android.experimental.self-instrumenting"] = true
      }

      tasks.withType<KotlinJvmCompile>().configureEach {
        compilerOptions {
          jvmTarget.set(JvmTarget.fromTarget(Config.jvm.kotlinJvm))
        }
      }

      val libs = versionCatalog()
      dependencies {
        add("implementation", libs.findLibrary("androidx-benchmark-macro-junit4").get())
        add("implementation", libs.findLibrary("androidx-test-ext-junit").get())
        add("implementation", libs.findLibrary("androidx-test-espresso-core").get())
        add("implementation", libs.findLibrary("androidx-test-uiautomator").get())
      }

      extensions.configure<TestAndroidComponentsExtension> {
        beforeVariants(selector().all()) {
          it.enable = it.buildType == "benchmark"
        }
      }
    }
  }
}
