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
  ":features:core:calculator",
  ":features:core:database",
  ":features:core:preferences",
  ":features:core:ui"
)
include(
  ":apps:mobile",
  ":features:mobile:calculator-input",
  ":features:mobile:calculator-output",
  ":features:mobile:onboarding",
  ":features:mobile:settings",
  ":features:mobile:ui"
)
include(":benchmarks:macro:mobile")
include(":apps:wearos")
include(":benchmarks:macro:wearos")
