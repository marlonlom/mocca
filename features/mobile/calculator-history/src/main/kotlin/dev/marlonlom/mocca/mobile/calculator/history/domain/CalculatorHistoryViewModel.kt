/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.mobile.calculator.history.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marlonlom.mocca.core.database.datasource.LocalDataSource
import dev.marlonlom.mocca.core.database.entities.SuccessfulCalculationHistory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date

/**
 * ViewModel responsible for managing and exposing calculator history UI state.
 *
 * @author marlonlom
 *
 * @property historyDataSource Local data source used to retrieve stored calculation history.
 */
class CalculatorHistoryViewModel(private val historyDataSource: LocalDataSource) : ViewModel() {

  /**
   * Represents the UI state of the settings screen.
   *
   * It emits [CalculatorHistoryUiState.Success] with the current settings,
   * or [CalculatorHistoryUiState.Loading] while initially loading.
   */
  val uiState: StateFlow<CalculatorHistoryUiState> = historyDataSource.getAllSuccessCalculationHistory()
    .map { historicalData -> mapToUiState(historyList = historicalData) }
    .catch { emit(CalculatorHistoryUiState.Empty) }
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = CalculatorHistoryUiState.Loading,
    )

  /**
   * Deletes a single successful calculation history entry by its identifier.
   *
   * @param historyId Unique identifier of the history item to remove.
   */
  fun deleteHistoryItem(historyId: Long) {
    viewModelScope.launch {
      historyDataSource.deleteSuccessCalculationHistory(calculationHistoryId = historyId)
    }
  }

  /** Clears all stored successful calculation history entries. */
  fun clearAllHistory() {
    viewModelScope.launch {
      historyDataSource.deleteAllSuccessCalculationHistory()
    }
  }

  /**
   * Maps a list of [SuccessfulCalculationHistory] entities to a
   * corresponding [CalculatorHistoryUiState].
   *
   * Returns [CalculatorHistoryUiState.Empty] if the list is empty,
   * otherwise returns [CalculatorHistoryUiState.Success] with mapped domain items.
   */
  private fun mapToUiState(historyList: List<SuccessfulCalculationHistory>): CalculatorHistoryUiState {
    if (historyList.isEmpty()) return CalculatorHistoryUiState.Empty
    return CalculatorHistoryUiState.Success(
      historicItems = historyList.map {
        SuccessfulCalculationDomainData(
          calculationId = it.id,
          createdAt = it.createdAt ?: Date(),
          amount = it.amount,
          fixedFee = it.fixedFee,
          variableFee = it.variableFee,
          total = it.total,
        )
      },
    )
  }
}
