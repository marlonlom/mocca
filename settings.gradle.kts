/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

pluginManagement {
  includeBuild("build-logic")
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "Mocca"
include(
  ":apps:mobile",
  ":features:mobile:settings"
)
include(":apps:wearos")
include(":benchmarks:macro:mobile")
include(":benchmarks:macro:wearos")
include(":features:core:calculator", ":features:core:preferences", ":features:core:ui")
