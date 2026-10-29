/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.ui.main

import dev.marlonlom.mocca.core.preferences.model.UserColorContrasts
import dev.marlonlom.mocca.core.preferences.model.UserSettings
import dev.marlonlom.mocca.core.preferences.repository.PreferencesRepository
import dev.marlonlom.mocca.ui.shortcut.AppShortcut
import dev.marlonlom.mocca.ui.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@OptIn(ExperimentalCoroutinesApi::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
internal class MainViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  private val repository = mockk<PreferencesRepository>()
  private lateinit var viewModel: MainViewModel

  private val defaultUserSettings = UserSettings(
    isOnboarding = true,
    useDarkTheme = false,
    useDynamicColor = false,
    colorContrast = UserColorContrasts.STANDARD.name,
  )

  @Before
  fun setUp() {
    coEvery { repository.preferencesFlow } returns flowOf(defaultUserSettings)
    viewModel = MainViewModel(repository)
  }

  @Test
  fun `given initial preferences when uiState collected then emits Success with shouldOpenFees as false`() = runTest {
    val results = mutableListOf<MainUiState>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.uiState.toList(results)
    }
    advanceUntilIdle()
    assertTrue(results.last() is MainUiState.Success)
    val successState = results.last() as MainUiState.Success
    assertEquals(defaultUserSettings, successState.userData)
    assertFalse(successState.shouldOpenFees)
    job.cancel()
  }

  @Test
  fun `given setOnboardingComplete called then toggles is_onboarding setting in repository`() = runTest {
    coEvery { repository.toggleBooleanSetting("is_onboarding", false) } returns Unit
    viewModel.setOnboardingComplete()
    advanceUntilIdle()
    coVerify(exactly = 1) { repository.toggleBooleanSetting("is_onboarding", false) }
  }

  @Test
  fun `given valid view_fees shortcutId when handleShortcut called then updates shouldOpenFees to true`() = runTest {
    val results = mutableListOf<MainUiState>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.uiState.toList(results)
    }
    viewModel.handleShortcut(AppShortcut.VIEW_FEES.id)
    advanceUntilIdle()
    assertTrue(results.last() is MainUiState.Success)
    val successState = results.last() as MainUiState.Success
    assertTrue(successState.shouldOpenFees)
    job.cancel()
  }

  @Test
  fun `given invalid or null shortcutId when handleShortcut called then shouldOpenFees remains false`() = runTest {
    val results = mutableListOf<MainUiState>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.uiState.toList(results)
    }
    viewModel.handleShortcut("invalid_shortcut_id")
    viewModel.handleShortcut(null)
    advanceUntilIdle()
    assertTrue(results.last() is MainUiState.Success)
    val successState = results.last() as MainUiState.Success
    assertFalse(successState.shouldOpenFees)
    job.cancel()
  }

  @Test
  fun `given shouldOpenFees is true when consumeShortcut called then resets shouldOpenFees to false`() = runTest {
    val results = mutableListOf<MainUiState>()
    val job = launch(UnconfinedTestDispatcher()) {
      viewModel.uiState.toList(results)
    }
    viewModel.handleShortcut(AppShortcut.VIEW_FEES.id)
    advanceUntilIdle()
    assertTrue((results.last() as MainUiState.Success).shouldOpenFees)
    viewModel.consumeShortcut()
    advanceUntilIdle()
    assertFalse((results.last() as MainUiState.Success).shouldOpenFees)
    job.cancel()
  }
}
