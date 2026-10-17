package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.SubScreen
import com.example.ui.components.AuthorizationChannelsCard
import com.example.ui.components.CardActionButtons
import com.example.ui.components.DynamicCvvBanner
import com.example.ui.components.EmergencyOppositionButton
import com.example.ui.components.GoldCardView
import com.example.ui.components.PrestigeMetalCard
import com.example.ui.components.SpendingLimitsCard
import com.example.ui.components.StatusChipsRow
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.GoldDark
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.TextPrimary
import com.example.viewmodel.FlashKalpeViewModel

/**
 * Screen matching the screenshot uploaded by the user:
 * FlashKalpe - Carte Flash Gold
 */
@Composable
fun GoldCardScreen(
  viewModel: FlashKalpeViewModel,
  modifier: Modifier = Modifier
) {
  val isFrozen by viewModel.isCardFrozen.collectAsState()
  val isCvvVisible by viewModel.isCvvVisible.collectAsState()
  val cvvCode by viewModel.cvvCode.collectAsState()
  val cvvCountdown by viewModel.cvvCountdown.collectAsState()

  val cardSpent by viewModel.monthlyCardSpent.collectAsState()
  val cardLimit by viewModel.monthlyCardLimit.collectAsState()
  val atmSpent by viewModel.monthlyAtmSpent.collectAsState()
  val atmLimit by viewModel.monthlyAtmLimit.collectAsState()

  val ecommerceEnabled by viewModel.ecommerceEnabled.collectAsState()
  val internationalEnabled by viewModel.internationalEnabled.collectAsState()
  val atmEnabled by viewModel.atmEnabled.collectAsState()
  val nfcEnabled by viewModel.nfcEnabled.collectAsState()

  var showLimitDialog by remember { mutableStateOf(false) }
  var showOppositionConfirm by remember { mutableStateOf(false) }
  var showPrestigeInfo by remember { mutableStateOf(false) }

  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
      .padding(horizontal = 16.dp, vertical = 12.dp)
      .testTag("gold_card_screen"),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. Status Chips Row (VIP Gold + Biométrie Active)
    StatusChipsRow()

    // 2. Physical / Virtual Gold Card Container
    GoldCardView(
      isFrozen = isFrozen,
      isCvvVisible = isCvvVisible,
      cvvCode = cvvCode,
      onToggleCvv = { viewModel.toggleCvv() }
    )

    // 3. Dynamic CVV Refresh Banner
    DynamicCvvBanner(countdownSeconds = cvvCountdown)

    // 4. Quick Action Controls: Geler, Code PIN, Wallet
    CardActionButtons(
      isFrozen = isFrozen,
      onToggleFreeze = { viewModel.toggleFreezeCard() },
      onOpenPin = { viewModel.openSubScreen(SubScreen.PinKeypad) },
      onOpenWallet = { viewModel.showFeedback("Wallet Apple Pay / Google Wallet synchronisé") }
    )

    // 5. Spending Limits Panel
    SpendingLimitsCard(
      cardSpent = cardSpent,
      cardLimit = cardLimit,
      atmSpent = atmSpent,
      atmLimit = atmLimit,
      onModifyClick = { showLimitDialog = true }
    )

    // 6. Channel Security Authorization Toggles
    AuthorizationChannelsCard(
      ecommerceEnabled = ecommerceEnabled,
      internationalEnabled = internationalEnabled,
      atmEnabled = atmEnabled,
      nfcEnabled = nfcEnabled,
      onToggleEcommerce = { viewModel.toggleEcommerce() },
      onToggleInternational = { viewModel.toggleInternational() },
      onToggleAtm = { viewModel.toggleAtm() },
      onToggleNfc = { viewModel.toggleNfc() }
    )

    // 7. Prestige Black Metal Tier Upgrade
    PrestigeMetalCard(
      onDiscoverClick = { showPrestigeInfo = true }
    )

    // 8. Emergency Opposition Button
    EmergencyOppositionButton(
      onClick = { showOppositionConfirm = true }
    )

    Spacer(modifier = Modifier.height(60.dp))
  }

  // Dialog: Modify Spending Limit
  if (showLimitDialog) {
    var tempLimit by remember { mutableFloatStateOf(cardLimit.toFloat()) }

    AlertDialog(
      onDismissRequest = { showLimitDialog = false },
      title = {
        Text(
          text = "Modifier le plafond mensuel",
          fontWeight = FontWeight.Bold,
          fontSize = 17.sp,
          color = TextPrimary
        )
      },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
          Text(
            text = "Ajustez le plafond des paiements par carte et web en fonction de vos dépenses prévues.",
            fontSize = 13.sp,
            color = GoldDark
          )

          Text(
            text = "${FlashKalpeViewModel.formatAmount(tempLimit.toLong())} FCFA",
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            color = EmeraldPrimary
          )

          Slider(
            value = tempLimit,
            onValueChange = { tempLimit = it },
            valueRange = 500_000f..10_000_000f,
            steps = 19,
            colors = SliderDefaults.colors(
              thumbColor = EmeraldPrimary,
              activeTrackColor = EmeraldPrimary
            )
          )

          Text(
            text = "Plafond maximum autorisé : 10.000.000 FCFA (Tier 3 BCEAO)",
            fontSize = 11.sp,
            color = GoldDark
          )
        }
      },
      confirmButton = {
        Button(
          onClick = {
            viewModel.updateMonthlySpendingLimit(tempLimit.toLong())
            showLimitDialog = false
          },
          colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary)
        ) {
          Text("Appliquer", color = SurfaceWhite, fontWeight = FontWeight.Bold)
        }
      },
      dismissButton = {
        TextButton(onClick = { showLimitDialog = false }) {
          Text("Annuler", color = GoldDark)
        }
      },
      shape = RoundedCornerShape(20.dp),
      containerColor = SurfaceWhite
    )
  }

  // Dialog: Opposition Confirmation
  if (showOppositionConfirm) {
    AlertDialog(
      onDismissRequest = { showOppositionConfirm = false },
      title = {
        Text(
          text = "Opposition Carte FlashKalpe",
          fontWeight = FontWeight.Bold,
          color = TextPrimary
        )
      },
      text = {
        Text(
          text = "Confirmez-vous la mise en opposition définitive de votre carte Mastercard Gold (•••• 8924) ? Un nouveau numéro vous sera instantanément délivré.",
          fontSize = 13.sp,
          color = TextPrimary
        )
      },
      confirmButton = {
        Button(
          onClick = {
            viewModel.triggerOpposition()
            showOppositionConfirm = false
          },
          colors = ButtonDefaults.buttonColors(containerColor = com.example.ui.theme.ErrorRed)
        ) {
          Text("Confirmer l'opposition", color = SurfaceWhite, fontWeight = FontWeight.Bold)
        }
      },
      dismissButton = {
        TextButton(onClick = { showOppositionConfirm = false }) {
          Text("Annuler", color = GoldDark)
        }
      },
      shape = RoundedCornerShape(20.dp),
      containerColor = SurfaceWhite
    )
  }

  // Dialog: Prestige Black Metal
  if (showPrestigeInfo) {
    AlertDialog(
      onDismissRequest = { showPrestigeInfo = false },
      title = {
        Text(
          text = "Prestige Black Metal 18g",
          fontWeight = FontWeight.Bold,
          color = TextPrimary
        )
      },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(
            text = "L'expérience financière ultime pour l'Afrique de l'Ouest :",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary
          )
          Text(
            text = "• Acier inoxydable noir brossé gravé au laser\n• Accès illimité aux salons LoungeKey (AIBD Dakar, Abidjan, Paris...)\n• Conciergerie privée FlashKalpe disponible 24/7 sur WhatsApp\n• Plafond de paiement étendu jusqu'à 25.000.000 FCFA\n• Assurance voyage et rapatriement intégrale",
            fontSize = 12.sp,
            color = GoldDark,
            lineHeight = 18.sp
          )
        }
      },
      confirmButton = {
        Button(
          onClick = {
            viewModel.showFeedback("Demande de passage au statut Black Metal enregistrée !")
            showPrestigeInfo = false
          },
          colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary)
        ) {
          Text("Commander l'édition Métal", color = SurfaceWhite, fontWeight = FontWeight.Bold)
        }
      },
      dismissButton = {
        TextButton(onClick = { showPrestigeInfo = false }) {
          Text("Fermer", color = GoldDark)
        }
      },
      shape = RoundedCornerShape(20.dp),
      containerColor = SurfaceWhite
    )
  }
}
