/*
 * Copyright 2024 Marlonlom
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.marlonlom.mocca.wearos.calculator.fees.domain

import dev.marlonlom.mocca.calculator.Calculator
import dev.marlonlom.mocca.calculator.model.OrderFees
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

internal class CalculatorFeesProviderTest {

  @Test
  fun provideFees_shouldReturnNonEmptyListMappedFromOrderFees() {
    val result = CalculatorFeesProvider.provideFees()
    assertNotNull(result)
    assertEquals(OrderFees.entries.size, result.size)
  }

  @Test
  fun provideFees_shouldCorrectlyMapEnumPropertiesToDomainData() {
    val result = CalculatorFeesProvider.provideFees()
    OrderFees.entries.forEachIndexed { index, expectedEnum ->
      val actualDomainData = result[index]
      assertEquals(expectedEnum.min, actualDomainData.min, 0.0)
      assertEquals(expectedEnum.max, actualDomainData.max, 0.0)
      assertEquals(expectedEnum.fixedFee, actualDomainData.fixedFee, 0.0)
      assertEquals(expectedEnum.haveVariableFee, actualDomainData.haveVariableFee)
    }
  }

  @Test
  fun provideFees_whenHaveVariableFeeIsTrue_shouldApplyVariableFeeFactor() {
    val result = CalculatorFeesProvider.provideFees()
    val itemsWithVariableFee = result.filter { it.haveVariableFee }
    assertTrue("Test setup warning: No OrderFees entry has haveVariableFee = true", itemsWithVariableFee.isNotEmpty())
    itemsWithVariableFee.forEach { domainData ->
      assertEquals(Calculator.VARIABLE_FEE_FACTOR, domainData.variableFeeFactor, 0.0)
    }
  }

  @Test
  fun provideFees_whenHaveVariableFeeIsFalse_shouldApplyZeroVariableFeeFactor() {
    val result = CalculatorFeesProvider.provideFees()
    val itemsWithoutVariableFee = result.filter { !it.haveVariableFee }
    assertTrue(
      "Test setup warning: No OrderFees entry has haveVariableFee = false",
      itemsWithoutVariableFee.isNotEmpty(),
    )
    itemsWithoutVariableFee.forEach { domainData ->
      assertEquals(0.0, domainData.variableFeeFactor, 0.0)
    }
  }
}
