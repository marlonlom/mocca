/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */

@file:Suppress("UnstableApiUsage")

plugins {
  id("mocca.android.macrobenchmark")
}

android {
  namespace = "dev.marlonlom.mocca.macrobenchmarks.wearos"
  targetProjectPath = ":apps:wearos"
}
