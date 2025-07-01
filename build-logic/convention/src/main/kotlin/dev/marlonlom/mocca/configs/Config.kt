/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

package dev.marlonlom.mocca.configs

import org.gradle.api.JavaVersion

/**
 * Top-level configuration object holding Android and JVM build settings.
 *
 * @author marlonlom
 */
object Config {

  /** Configuration for Android-specific build parameters. */
  val mobile = AndroidConfig(
    minSdkVersion = 24
  )

  /** Configuration for Wearos-specific build parameters. */
  val wearos = AndroidConfig(
    minSdkVersion = 30
  )

  /** Configuration for JVM-related build parameters. */
  val jvm = JvmConfig()
}

/**
 * Holds configuration values related to the Android build environment.
 *
 * @author marlonlom
 *
 * @property minSdkVersion The minimum Android SDK version the app supports.
 * @property targetSdkVersion The target Android SDK version the app is optimized for.
 * @property compileSdkVersion The Android SDK version used to compile the app.
 * @property applicationId The unique package name used to identify the app on the device and Play Store.
 * @property versionCode An integer value that represents the version of the application code.
 * @property versionName A human-readable version string shown to users.
 * @property nameSpace The Android namespace used for resolving packages in Compose and XML resources.
 * @property testInstrumentationRunner The fully qualified class name of the instrumentation test runner.
 */
data class AndroidConfig(
  val minSdkVersion: Int,
  val targetSdkVersion: Int = 35,
  val compileSdkVersion: Int = 35,
  val applicationId: String = "dev.marlonlom.mocca",
  val versionCode: Int = 1,
  val versionName: String = "1.0.0",
  val nameSpace: String = applicationId,
  val testInstrumentationRunner: String = "androidx.test.runner.AndroidJUnitRunner"
)

/**
 * Holds configuration values for the Java and Kotlin compilation targets.
 *
 * @author marlonlom
 *
 * @property javaVersion The Java version used to compile the source code (e.g., Java 17).
 * @property kotlinJvm The JVM bytecode version targeted by the Kotlin compiler (e.g., "17").
 */
data class JvmConfig(
  val javaVersion: JavaVersion = JavaVersion.VERSION_17,
  val kotlinJvm: String = JavaVersion.VERSION_17.majorVersion,
)
