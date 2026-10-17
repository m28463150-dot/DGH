package com.example.model

enum class MainTab(val title: String, val iconName: String) {
  ACCUEIL("Accueil", "account_balance_wallet"),
  CARTE_GOLD("Carte Gold", "credit_card"),
  COFFRES("Coffres", "savings"),
  FACTURES("Factures", "electric_bolt"),
  HISTORIQUE("Activité", "bar_chart"),
  REGLAGES("Réglages", "tune")
}

sealed class SubScreen {
  object None : SubScreen()
  object QrScanner : SubScreen()
  object Recharge : SubScreen()
  object PinKeypad : SubScreen()
  data class Receipt(
    val title: String,
    val amount: Long,
    val merchantName: String,
    val category: String,
    val reference: String,
    val cashback: Long
  ) : SubScreen()
  object OppositionConfirm : SubScreen()
}

data class CardModel(
  val id: String,
  val title: String,
  val subtitle: String,
  val cardNumberMasked: String,
  val holderName: String,
  val expiryDate: String,
  val balance: Long,
  val currency: String = "FCFA",
  val isVirtual: Boolean = false,
  val typeBadge: String = "World Elite Gold"
)

data class TransactionItem(
  val id: String,
  val title: String,
  val subtitle: String,
  val date: String,
  val amount: Long,
  val isCredit: Boolean,
  val category: String, // transfer, receive, bill
  val status: String = "Validé"
)

data class TontineMember(
  val turn: Int,
  val name: String,
  val month: String,
  val amount: Long,
  val status: String, // done, current, next, you, pending
  val isYou: Boolean = false
)

data class BillProvider(
  val id: String,
  val name: String,
  val subtitle: String,
  val category: String, // elec, water, media, public
  val dueAmount: Long? = null,
  val dueDate: String? = null,
  val reference: String? = null
)
