/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.core.preferences.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.test.TestScope

/**
 * A singleton object that provides a testable DataStore<Preferences> instance.
 *
 * This object ensures that only one instance of the DataStore is created and used
 * throughout the test environment. It uses a [TestScope] to manage the coroutine
 * context for the DataStore.
 *
 * @author marlonlom
 */
internal object TestablePreferencesDataStore {

  /** Singleton instance of the DataStore<Preferences>. */
  private var instance: DataStore<Preferences>? = null

  /**
   * Returns the singleton instance of the DataStore<Preferences>.
   *
   * If an instance has not been created yet, this function will create one
   * using [PreferenceDataStoreFactory]. The DataStore is scoped to the
   * provided [testCoroutineScope].
   *
   * @param testCoroutineScope The [TestScope] to be used by the DataStore.
   * @return The singleton [DataStore<Preferences>] instance.
   */
  fun getInstance(testCoroutineScope: TestScope): DataStore<Preferences> = instance ?: synchronized(this) {
    instance ?: PreferenceDataStoreFactory.create(
      scope = testCoroutineScope,
      produceFile = {
        InstrumentationRegistry.getInstrumentation()
          .targetContext.preferencesDataStoreFile(name = "testable-mocca-settings")
      },
    ).also { instance = it }
  }
}
