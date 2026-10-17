package com.example

import com.example.viewmodel.FlashKalpeViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

  @Test
  fun testFormatAmount() {
    assertEquals("50.000", FlashKalpeViewModel.formatAmount(50000L))
    assertEquals("1.450.000", FlashKalpeViewModel.formatAmount(1450000L))
    assertEquals("408.111", FlashKalpeViewModel.formatAmount(408111L))
    assertEquals("0", FlashKalpeViewModel.formatAmount(0L))
  }

  @Test
  fun testCardControls() {
    val vm = FlashKalpeViewModel()
    assertFalse(vm.isCardFrozen.value)
    vm.toggleFreezeCard()
    assertTrue(vm.isCardFrozen.value)
    vm.toggleFreezeCard()
    assertFalse(vm.isCardFrozen.value)

    assertFalse(vm.isCvvVisible.value)
    vm.toggleCvv()
    assertTrue(vm.isCvvVisible.value)
  }

  @Test
  fun testRechargeFlow() {
    val vm = FlashKalpeViewModel()
    val initialBalance = vm.balance.value
    vm.performRecharge(50000L, "Wave Sénégal")
    assertEquals(initialBalance + 50000L, vm.balance.value)
    assertEquals("Recharge Wave Sénégal", vm.transactions.value.first().title)
  }

  @Test
  fun testWoyofalPayment() {
    val vm = FlashKalpeViewModel()
    val initialBalance = vm.balance.value
    val token = vm.payWoyofal(10000L, "0429 1844 910")
    assertTrue(token.isNotEmpty())
    assertEquals(initialBalance - 10000L, vm.balance.value)
  }
}
