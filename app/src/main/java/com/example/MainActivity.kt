package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MainTab
import com.example.model.SubScreen
import com.example.ui.components.BottomNavBar
import com.example.ui.components.FlashHeader
import com.example.ui.components.PinPadDialog
import com.example.ui.components.ReceiptScreen
import com.example.ui.screens.BillsWoyofalScreen
import com.example.ui.screens.GoldCardScreen
import com.example.ui.screens.HistoryAnalyticsScreen
import com.example.ui.screens.QrScannerScreen
import com.example.ui.screens.RechargeScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.TontineScreen
import com.example.ui.screens.WalletHomeScreen
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.FlashKalpeTheme
import com.example.ui.theme.GoldDark
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.FlashKalpeViewModel

class MainActivity : ComponentActivity() {

  private val viewModel: FlashKalpeViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      FlashKalpeTheme {
        FlashKalpeApp(viewModel = viewModel)
      }
    }
  }
}

@Composable
fun FlashKalpeApp(viewModel: FlashKalpeViewModel) {
  val currentTab by viewModel.currentTab.collectAsState()
  val subScreens by viewModel.subScreenStack.collectAsState()
  val feedbackMessage by viewModel.feedbackMessage.collectAsState()
  val activeSubScreen = subScreens.lastOrNull() ?: SubScreen.None

  var showHelpDialog by remember { mutableStateOf(false) }

  // System back press handling
  BackHandler(enabled = subScreens.isNotEmpty() || currentTab != MainTab.CARTE_GOLD) {
    if (subScreens.isNotEmpty()) {
      viewModel.closeSubScreen()
    } else if (currentTab != MainTab.CARTE_GOLD) {
      viewModel.navigateToTab(MainTab.CARTE_GOLD)
    }
  }

  val headerTitle = when (activeSubScreen) {
    is SubScreen.QrScanner -> "Scanner & Payer"
    is SubScreen.Recharge -> "Recharger le Compte"
    is SubScreen.Receipt -> "Reçu de Paiement"
    else -> when (currentTab) {
      MainTab.CARTE_GOLD -> "Flash Pay Transfert"
      MainTab.ACCUEIL -> "FlashKalpe"
      MainTab.COFFRES -> "Tontines & Épargne"
      MainTab.FACTURES -> "Factures & Woyofal"
      MainTab.HISTORIQUE -> "Activité & Statistiques"
      MainTab.REGLAGES -> "Réglages & Sécurité"
    }
  }

  Scaffold(
    modifier = Modifier.fillMaxSize().testTag("flashkalpe_scaffold"),
    topBar = {
      FlashHeader(
        title = headerTitle,
        canGoBack = activeSubScreen !is SubScreen.None,
        onBackClick = { viewModel.closeSubScreen() },
        onHelpClick = { showHelpDialog = true },
        onProfileClick = { viewModel.navigateToTab(MainTab.REGLAGES) }
      )
    },
    bottomBar = {
      if (activeSubScreen is SubScreen.None) {
        BottomNavBar(
          currentTab = currentTab,
          onTabSelected = { viewModel.navigateToTab(it) }
        )
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      // Main Body
      if (activeSubScreen is SubScreen.None) {
        AnimatedContent(targetState = currentTab, label = "tab_content") { tab ->
          when (tab) {
            MainTab.CARTE_GOLD -> GoldCardScreen(viewModel = viewModel)
            MainTab.ACCUEIL -> WalletHomeScreen(viewModel = viewModel)
            MainTab.COFFRES -> TontineScreen(viewModel = viewModel)
            MainTab.FACTURES -> BillsWoyofalScreen(viewModel = viewModel)
            MainTab.HISTORIQUE -> HistoryAnalyticsScreen(viewModel = viewModel)
            MainTab.REGLAGES -> SettingsScreen(viewModel = viewModel)
          }
        }
      } else {
        when (val screen = activeSubScreen) {
          is SubScreen.QrScanner -> QrScannerScreen(viewModel = viewModel)
          is SubScreen.Recharge -> RechargeScreen(viewModel = viewModel)
          is SubScreen.Receipt -> ReceiptScreen(
            receipt = screen,
            onClose = { viewModel.closeSubScreen() },
            onShare = { viewModel.showFeedback("Reçu partagé via WhatsApp") },
            onDownloadPdf = { viewModel.showFeedback("Reçu PDF téléchargé avec succès") }
          )
          else -> {}
        }
      }

      // PIN Pad Overlay Dialog
      if (activeSubScreen is SubScreen.PinKeypad) {
        PinPadDialog(
          onDismiss = { viewModel.closeSubScreen() },
          onPinVerified = {
            viewModel.showFeedback("Authentification réussie !")
            viewModel.closeSubScreen()
          }
        )
      }

      // Feedback Toast / Capsule
      AnimatedVisibility(
        visible = feedbackMessage != null,
        enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
        modifier = Modifier
          .align(Alignment.TopCenter)
          .padding(top = 16.dp, start = 20.dp, end = 20.dp)
      ) {
        feedbackMessage?.let { msg ->
          Box(
            modifier = Modifier
              .shadow(8.dp, RoundedCornerShape(50.dp))
              .clip(RoundedCornerShape(50.dp))
              .background(TextPrimary)
              .padding(horizontal = 18.dp, vertical = 10.dp)
          ) {
            Text(
              text = msg,
              color = SurfaceWhite,
              fontSize = 12.sp,
              fontWeight = FontWeight.SemiBold
            )
          }
        }
      }
    }
  }

  // Help Dialog
  if (showHelpDialog) {
    AlertDialog(
      onDismissRequest = { showHelpDialog = false },
      title = {
        Text("FlashKalpe Assistance", fontWeight = FontWeight.Bold, color = TextPrimary)
      },
      text = {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(
            text = "Bienvenue sur FlashKalpe, votre portefeuille et carte virtuelle d'élite pour l'Afrique de l'Ouest.",
            fontSize = 13.sp,
            color = TextPrimary
          )
          Text(
            text = "• Carte Flash Gold : Paiements mondiaux et retrait DAB sans contact\n• Woyofal Senelec : Achat instantané de crédits électricité 24/7 sans frais\n• Tontines : Cagnottes collectives sécurisées par séquestre bancaire BCEAO\n• Passerelles : Retrait et recharge instantanés Wave & Orange Money",
            fontSize = 12.sp,
            color = TextSecondary,
            lineHeight = 17.sp
          )
        }
      },
      confirmButton = {
        Button(
          onClick = { showHelpDialog = false },
          colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimary)
        ) {
          Text("Compris", color = SurfaceWhite, fontWeight = FontWeight.Bold)
        }
      },
      shape = RoundedCornerShape(18.dp),
      containerColor = SurfaceWhite
    )
  }
}
