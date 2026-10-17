package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.BillProvider
import com.example.model.CardModel
import com.example.model.MainTab
import com.example.model.SubScreen
import com.example.model.TontineMember
import com.example.model.TransactionItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class FlashKalpeViewModel : ViewModel() {

  private val _currentTab = MutableStateFlow(MainTab.CARTE_GOLD)
  val currentTab: StateFlow<MainTab> = _currentTab.asStateFlow()

  private val _subScreenStack = MutableStateFlow<List<SubScreen>>(emptyList())
  val subScreenStack: StateFlow<List<SubScreen>> = _subScreenStack.asStateFlow()

  val currentSubScreen: StateFlow<SubScreen> = MutableStateFlow(SubScreen.None)

  // Balances
  private val _balance = MutableStateFlow(408_111L)
  val balance: StateFlow<Long> = _balance.asStateFlow()

  // Card Controls
  private val _isCardFrozen = MutableStateFlow(false)
  val isCardFrozen: StateFlow<Boolean> = _isCardFrozen.asStateFlow()

  private val _isCvvVisible = MutableStateFlow(false)
  val isCvvVisible: StateFlow<Boolean> = _isCvvVisible.asStateFlow()

  private val _cvvCode = MutableStateFlow("649")
  val cvvCode: StateFlow<String> = _cvvCode.asStateFlow()

  private val _cvvCountdown = MutableStateFlow(39)
  val cvvCountdown: StateFlow<Int> = _cvvCountdown.asStateFlow()

  // Spending Limits
  private val _monthlyCardSpent = MutableStateFlow(1_450_000L)
  val monthlyCardSpent: StateFlow<Long> = _monthlyCardSpent.asStateFlow()

  private val _monthlyCardLimit = MutableStateFlow(3_000_000L)
  val monthlyCardLimit: StateFlow<Long> = _monthlyCardLimit.asStateFlow()

  private val _monthlyAtmSpent = MutableStateFlow(350_000L)
  val monthlyAtmSpent: StateFlow<Long> = _monthlyAtmSpent.asStateFlow()

  private val _monthlyAtmLimit = MutableStateFlow(1_000_000L)
  val monthlyAtmLimit: StateFlow<Long> = _monthlyAtmLimit.asStateFlow()

  // Authorization Toggles
  private val _ecommerceEnabled = MutableStateFlow(true)
  val ecommerceEnabled: StateFlow<Boolean> = _ecommerceEnabled.asStateFlow()

  private val _internationalEnabled = MutableStateFlow(true)
  val internationalEnabled: StateFlow<Boolean> = _internationalEnabled.asStateFlow()

  private val _atmEnabled = MutableStateFlow(true)
  val atmEnabled: StateFlow<Boolean> = _atmEnabled.asStateFlow()

  private val _nfcEnabled = MutableStateFlow(true)
  val nfcEnabled: StateFlow<Boolean> = _nfcEnabled.asStateFlow()

  // Multi-card deck
  val cards = listOf(
    CardModel(
      id = "gold",
      title = "FlashKalpe Gold VIP",
      subtitle = "World Elite Gold",
      cardNumberMasked = "•••• •••• •••• 8924",
      holderName = "NATASHA DIALLO",
      expiryDate = "08/28",
      balance = 408_111L,
      isVirtual = false,
      typeBadge = "Physique & Virtuelle"
    ),
    CardModel(
      id = "emerald",
      title = "Flash Emerald Virtual",
      subtitle = "Abonnements & Shopping",
      cardNumberMasked = "•••• •••• •••• 4102",
      holderName = "NATASHA DIALLO",
      expiryDate = "11/27",
      balance = 85_000L,
      isVirtual = true,
      typeBadge = "Virtuelle Web"
    ),
    CardModel(
      id = "tontine",
      title = "Coffre Tontine Flash",
      subtitle = "Cercle Teranga Dakar",
      cardNumberMasked = "•••• •••• •••• 1092",
      holderName = "NATASHA DIALLO",
      expiryDate = "05/29",
      balance = 350_000L,
      isVirtual = true,
      typeBadge = "Tontine Digitale"
    )
  )

  private val _activeCardIndex = MutableStateFlow(0)
  val activeCardIndex: StateFlow<Int> = _activeCardIndex.asStateFlow()

  // Transactions list
  private val _transactions = MutableStateFlow(
    listOf(
      TransactionItem("1", "Amadou Ba", "FlashKalpe Direct", "Aujourd'hui 14:28", 25_000L, true, "receive", "Validé"),
      TransactionItem("2", "Woyofal Senelec", "Compteur #0429 1844 910", "Hier 20:10", 10_000L, false, "bill", "Code: 4492-8012"),
      TransactionItem("3", "Café de Rome Dakar", "Paiement Marchand QR", "24 Mai", 14_500L, false, "bill", "Reçu #CR-904"),
      TransactionItem("4", "Tontine Entrepreneurs", "Cotisation Tournante #04", "05 Mai", 100_000L, false, "transfer", "Validé"),
      TransactionItem("5", "Recharge Wave Sénégal", "Approvisionnement Mobile Money", "01 Mai", 150_000L, true, "receive", "Validé")
    )
  )
  val transactions: StateFlow<List<TransactionItem>> = _transactions.asStateFlow()

  // Tontine Members
  val tontineMembers = listOf(
    TontineMember(1, "Mariama Ba", "Février", 1_000_000L, "done"),
    TontineMember(2, "Ousmane Sow", "Mars", 1_000_000L, "done"),
    TontineMember(3, "Ibrahima Fall", "Avril", 1_000_000L, "done"),
    TontineMember(4, "Amadou Sall", "Mai", 1_000_000L, "current"),
    TontineMember(5, "Fatou Ndiaye", "Juin", 1_000_000L, "next"),
    TontineMember(6, "Natasha Diallo", "Juillet", 1_000_000L, "you", isYou = true),
    TontineMember(7, "Cheikh Anta", "Août", 1_000_000L, "pending"),
    TontineMember(8, "Awa Diop", "Septembre", 1_000_000L, "pending"),
    TontineMember(9, "Moustapha Gaye", "Octobre", 1_000_000L, "pending"),
    TontineMember(10, "Seynabou Thiam", "Novembre", 1_000_000L, "pending")
  )

  // Pending Bills
  val pendingBills = listOf(
    BillProvider(
      id = "seneau",
      name = "Sen'Eau Distribution",
      subtitle = "Police 82194 • Facture Mai 2024",
      category = "water",
      dueAmount = 14_850L,
      dueDate = "28 Mai",
      reference = "SN-849120"
    ),
    BillProvider(
      id = "canal",
      name = "Canal+ Sénégal",
      subtitle = "Décodeur Salon • Formule Evasion",
      category = "media",
      dueAmount = 15_000L,
      dueDate = "15 Juin",
      reference = "CP-99014"
    )
  )

  // Toast / Feedback message
  private val _feedbackMessage = MutableStateFlow<String?>(null)
  val feedbackMessage: StateFlow<String?> = _feedbackMessage.asStateFlow()

  init {
    // Dynamic CVV countdown loop
    viewModelScope.launch {
      while (true) {
        delay(1000)
        if (_cvvCountdown.value > 1) {
          _cvvCountdown.value -= 1
        } else {
          _cvvCountdown.value = 60
          _cvvCode.value = Random.nextInt(100, 999).toString()
        }
      }
    }
  }

  fun navigateToTab(tab: MainTab) {
    _currentTab.value = tab
    _subScreenStack.value = emptyList()
  }

  fun openSubScreen(screen: SubScreen) {
    _subScreenStack.value = _subScreenStack.value + screen
  }

  fun closeSubScreen() {
    val current = _subScreenStack.value
    if (current.isNotEmpty()) {
      _subScreenStack.value = current.dropLast(1)
    }
  }

  fun setActiveCardIndex(index: Int) {
    _activeCardIndex.value = index
  }

  fun toggleFreezeCard() {
    _isCardFrozen.value = !_isCardFrozen.value
    showFeedback(if (_isCardFrozen.value) "Carte temporairement gelée" else "Carte réactivée avec succès")
  }

  fun toggleCvv() {
    _isCvvVisible.value = !_isCvvVisible.value
  }

  fun toggleEcommerce() {
    _ecommerceEnabled.value = !_ecommerceEnabled.value
  }

  fun toggleInternational() {
    _internationalEnabled.value = !_internationalEnabled.value
  }

  fun toggleAtm() {
    _atmEnabled.value = !_atmEnabled.value
  }

  fun toggleNfc() {
    _nfcEnabled.value = !_nfcEnabled.value
  }

  fun updateMonthlySpendingLimit(newLimit: Long) {
    _monthlyCardLimit.value = newLimit
    showFeedback("Plafond mis à jour : ${formatAmount(newLimit)} FCFA")
  }

  fun performRecharge(amount: Long, method: String) {
    _balance.value += amount
    val newTx = TransactionItem(
      id = System.currentTimeMillis().toString(),
      title = "Recharge $method",
      subtitle = "Approvisionnement instantané",
      date = "À l'instant",
      amount = amount,
      isCredit = true,
      category = "receive",
      status = "Validé"
    )
    _transactions.value = listOf(newTx) + _transactions.value
    showFeedback("Compte rechargé de +${formatAmount(amount)} FCFA")
    closeSubScreen()
  }

  fun payWoyofal(amount: Long, meter: String): String {
    if (_balance.value >= amount) {
      _balance.value -= amount
      _monthlyCardSpent.value += amount
      val token = "${Random.nextInt(1000, 9999)}-${Random.nextInt(1000, 9999)}-${Random.nextInt(1000, 9999)}-${Random.nextInt(1000, 9999)}"
      val newTx = TransactionItem(
        id = System.currentTimeMillis().toString(),
        title = "Senelec Woyofal",
        subtitle = "Compteur #$meter",
        date = "À l'instant",
        amount = amount,
        isCredit = false,
        category = "bill",
        status = "Code: $token"
      )
      _transactions.value = listOf(newTx) + _transactions.value
      showFeedback("Achat Woyofal réussi ! Token : $token")
      return token
    } else {
      showFeedback("Solde insuffisant pour cette opération")
      return ""
    }
  }

  fun payMerchantQr(amount: Long, merchantName: String) {
    if (_balance.value >= amount) {
      _balance.value -= amount
      _monthlyCardSpent.value += amount
      val cashback = (amount * 0.025).toLong()
      val ref = "TXN-${Random.nextInt(100000, 999999)}"
      val newTx = TransactionItem(
        id = System.currentTimeMillis().toString(),
        title = merchantName,
        subtitle = "Paiement Marchand QR",
        date = "À l'instant",
        amount = amount,
        isCredit = false,
        category = "bill",
        status = "Réf: $ref"
      )
      _transactions.value = listOf(newTx) + _transactions.value
      // Navigate to receipt
      closeSubScreen()
      openSubScreen(
        SubScreen.Receipt(
          title = "Paiement Marchand Réussi",
          amount = amount,
          merchantName = merchantName,
          category = "Restaurant & Lounge Privilège",
          reference = ref,
          cashback = cashback
        )
      )
    } else {
      showFeedback("Solde insuffisant pour ce paiement")
    }
  }

  fun triggerOpposition() {
    _isCardFrozen.value = true
    showFeedback("Demande d'opposition transmise au centre d'autorisation GIM-UEMOA")
  }

  fun showFeedback(msg: String) {
    _feedbackMessage.value = msg
    viewModelScope.launch {
      delay(3000)
      if (_feedbackMessage.value == msg) {
        _feedbackMessage.value = null
      }
    }
  }

  companion object {
    fun formatAmount(amount: Long): String {
      val str = amount.toString()
      val sb = StringBuilder()
      var count = 0
      for (i in str.length - 1 downTo 0) {
        sb.append(str[i])
        count++
        if (count % 3 == 0 && i != 0) {
          sb.append('.')
        }
      }
      return sb.reverse().toString()
    }
  }
}
