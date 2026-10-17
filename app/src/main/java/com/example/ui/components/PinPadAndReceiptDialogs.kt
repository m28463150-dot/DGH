package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.SubScreen
import com.example.ui.theme.BorderOutline
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldPrimaryContainer
import com.example.ui.theme.GoldAmber
import com.example.ui.theme.GoldContainer
import com.example.ui.theme.GoldDark
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.FlashKalpeViewModel

/**
 * Tactile PIN Pad Keypad Dialog matching HTML mockup 9.
 */
@Composable
fun PinPadDialog(
  onDismiss: () -> Unit,
  onPinVerified: () -> Unit,
  modifier: Modifier = Modifier
) {
  var enteredPin by remember { mutableStateOf("742") }
  val maxDigits = 4

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Surface(
      modifier = modifier
        .fillMaxSize()
        .background(Color(0xFFF8FAFC)),
      color = Color(0xFFF8FAFC)
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
      ) {
        // Top row
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            modifier = Modifier
              .clip(RoundedCornerShape(50.dp))
              .background(SurfaceWhite)
              .border(1.dp, BorderOutline, RoundedCornerShape(50.dp))
              .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(Icons.Default.Shield, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(14.dp))
            Text("HSM TIER 4 • CHIFFRÉ", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
          }

          IconButton(onClick = onDismiss) {
            Icon(Icons.Default.Close, contentDescription = "Fermer", tint = TextPrimary)
          }
        }

        // Header Prompt
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text("Confirmez votre opération", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
          Text("Saisissez votre code PIN confidentiel à 4 chiffres", fontSize = 12.sp, color = TextSecondary, modifier = Modifier.padding(top = 2.dp))
        }

        // Transaction Voucher Card
        Card(
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
          Column(modifier = Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(modifier = Modifier.size(38.dp).clip(CircleShape).background(Color(0xFF263238)), contentAlignment = Alignment.Center) {
                  Icon(Icons.Default.Person, contentDescription = null, tint = SurfaceWhite, modifier = Modifier.size(20.dp))
                }
                Column {
                  Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Ndèye Fatim", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Icon(Icons.Default.Verified, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(14.dp))
                  }
                  Text("+221 77 842 19 04", fontSize = 11.sp, color = TextSecondary)
                }
              }

              Column(horizontalAlignment = Alignment.End) {
                Text("OPÉRATION", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = TextSecondary)
                Text("Flash Pay ⚡", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
              }
            }

            Column(
              modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)).background(SurfaceContainerLow).padding(10.dp),
              verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Montant net", fontSize = 11.sp, color = TextSecondary)
                Text("25.000 FCFA", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
              }
              Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Frais de service (1%)", fontSize = 11.sp, color = TextSecondary)
                Text("250 FCFA", fontSize = 11.sp, color = GoldDark)
              }
              Box(modifier = Modifier.fillMaxWidth().height(0.5.dp).background(BorderOutline))
              Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total débité", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text("25.250 FCFA", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold, color = EmeraldPrimary)
              }
            }
          }
        }

        // PIN Slot Dots
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Row(horizontalArrangement = Arrangement.spacedBy(14.dp), verticalAlignment = Alignment.CenterVertically) {
            for (i in 0 until maxDigits) {
              val isFilled = i < enteredPin.length
              val isActive = i == enteredPin.length

              Box(
                modifier = Modifier
                  .size(if (isFilled) 18.dp else 16.dp)
                  .clip(CircleShape)
                  .background(if (isFilled) EmeraldPrimary else if (isActive) EmeraldPrimaryContainer.copy(alpha = 0.5f) else SurfaceContainerLow)
                  .border(1.dp, if (isFilled) EmeraldPrimary else BorderOutline, CircleShape),
                contentAlignment = Alignment.Center
              ) {
                if (isFilled) {
                  Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(SurfaceWhite))
                }
              }
            }
          }

          Text(
            text = if (enteredPin.length == maxDigits) "Validation sécurisée en cours..." else "Dernier chiffre requis pour valider",
            fontSize = 11.sp,
            color = if (enteredPin.length == maxDigits) EmeraldPrimary else TextSecondary
          )
        }

        // Tactile Keypad
        Column(
          modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          val keypadRows = listOf(
            listOf("1", "2", "3"),
            listOf("4", "5", "6"),
            listOf("7", "8", "9"),
            listOf("BIO", "0", "DEL")
          )

          keypadRows.forEach { row ->
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
              row.forEach { key ->
                Box(
                  modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(if (key == "BIO") EmeraldPrimaryContainer else SurfaceWhite)
                    .border(1.dp, BorderOutline, RoundedCornerShape(14.dp))
                    .clickable {
                      when (key) {
                        "BIO" -> {
                          enteredPin = "7428"
                          onPinVerified()
                        }
                        "DEL" -> {
                          if (enteredPin.isNotEmpty()) enteredPin = enteredPin.dropLast(1)
                        }
                        else -> {
                          if (enteredPin.length < maxDigits) {
                            enteredPin += key
                            if (enteredPin.length == maxDigits) {
                              onPinVerified()
                            }
                          }
                        }
                      }
                    },
                  contentAlignment = Alignment.Center
                ) {
                  when (key) {
                    "BIO" -> {
                      Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(Icons.Default.Face, contentDescription = "Face ID", tint = EmeraldDark, modifier = Modifier.size(18.dp))
                        Text("Face ID", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = EmeraldDark)
                      }
                    }
                    "DEL" -> {
                      Icon(Icons.AutoMirrored.Filled.Backspace, contentDescription = "Effacer", tint = TextPrimary, modifier = Modifier.size(20.dp))
                    }
                    else -> {
                      Text(key, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    }
                  }
                }
              }
            }
          }
        }

        Text(
          text = "Session sécurisée par FlashKalpe Vault Protect™",
          fontSize = 10.sp,
          color = TextSecondary
        )
      }
    }
  }
}

/**
 * Official Payment Receipt Screen matching HTML mockup 10 & 11:
 * Celebratory checkmark, Café de Rome Dakar, Cashback +362 FCFA, official BCEAO QR seal.
 */
@Composable
fun ReceiptScreen(
  receipt: SubScreen.Receipt,
  onClose: () -> Unit,
  onShare: () -> Unit,
  onDownloadPdf: () -> Unit,
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
      .padding(horizontal = 16.dp, vertical = 12.dp)
      .testTag("receipt_screen"),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Top Success Checkmark
    Box(
      modifier = Modifier
        .size(76.dp)
        .clip(CircleShape)
        .background(EmeraldLight),
      contentAlignment = Alignment.Center
    ) {
      Box(
        modifier = Modifier
          .size(56.dp)
          .clip(CircleShape)
          .background(EmeraldPrimary),
        contentAlignment = Alignment.Center
      ) {
        Icon(Icons.Default.Check, contentDescription = null, tint = SurfaceWhite, modifier = Modifier.size(36.dp))
      }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Row(
        modifier = Modifier
          .clip(RoundedCornerShape(50.dp))
          .background(EmeraldLight)
          .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(EmeraldPrimary))
        Text("PAIEMENT INSTANTANÉ QR", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
      }
      Text("Paiement Réussi !", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = TextPrimary, modifier = Modifier.padding(top = 4.dp))
      Text("Aujourd'hui à 14:32 • Dakar, Sénégal", fontSize = 11.sp, color = TextSecondary)
    }

    // Total Amount Card
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.fillMaxWidth().padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Text("MONTANT TOTAL RÉGLÉ", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = TextSecondary)
        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
          Text(
            text = FlashKalpeViewModel.formatAmount(receipt.amount),
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Monospace,
            color = TextPrimary
          )
          Text("FCFA", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary, modifier = Modifier.padding(bottom = 4.dp))
        }
        Text("Frais : 0 FCFA (Pris en charge par le marchand)", fontSize = 11.sp, color = EmeraldPrimary, fontWeight = FontWeight.SemiBold)
      }
    }

    // Merchant Profile Card
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth().padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Box(
          modifier = Modifier.size(46.dp).clip(RoundedCornerShape(12.dp)).background(Color(0xFF263238)),
          contentAlignment = Alignment.Center
        ) {
          Text("CR", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = GoldAmber)
        }

        Column {
          Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(receipt.merchantName, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Box(modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(EmeraldLight).padding(horizontal = 6.dp, vertical = 2.dp)) {
              Text("Vérifié", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
            }
          }
          Text(receipt.category, fontSize = 11.sp, color = TextSecondary)
          Text("Plateau, Dakar • ID: FK-DKR-8820", fontSize = 10.sp, color = TextSecondary)
        }
      }
    }

    // Cashback Reward Banner
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = EmeraldLight)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth().padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(EmeraldPrimaryContainer), contentAlignment = Alignment.Center) {
          Icon(Icons.Default.Savings, contentDescription = null, tint = EmeraldDark, modifier = Modifier.size(20.dp))
        }
        Column {
          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("RÉCOMPENSE EXCLUSIVE", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = EmeraldDark)
            Box(modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(EmeraldPrimary).padding(horizontal = 6.dp, vertical = 1.dp)) {
              Text("+2.5%", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = SurfaceWhite)
            }
          }
          Text("Cashback généré : +${receipt.cashback} FCFA", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
          Text("Crédité instantanément sur votre coffre Épargne.", fontSize = 10.sp, color = TextSecondary)
        }
      }
    }

    // Technical Breakdown Ledger
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(modifier = Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
          Text("DÉTAILS DU RÈGLEMENT", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = TextSecondary)
          Text("Immuable • Validé", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
          Text("Référence", fontSize = 11.sp, color = TextSecondary)
          Text(receipt.reference, fontSize = 11.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace, color = TextPrimary)
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
          Text("Moyen de débit", fontSize = 11.sp, color = TextSecondary)
          Text("Carte Flash Gold (•••• 8924)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
          Text("Réseau de compensation", fontSize = 11.sp, color = TextSecondary)
          Text("Interbancaire GIM-UEMOA", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
        }
      }
    }

    // Action CTAs
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
      Button(
        onClick = onShare,
        modifier = Modifier.fillMaxWidth().height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimaryContainer),
        shape = RoundedCornerShape(12.dp)
      ) {
        Icon(Icons.Default.Share, contentDescription = null, tint = EmeraldDark)
        Spacer(modifier = Modifier.width(6.dp))
        Text("Partager le reçu WhatsApp", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = EmeraldDark)
      }

      Button(
        onClick = onDownloadPdf,
        modifier = Modifier.fillMaxWidth().height(46.dp),
        colors = ButtonDefaults.buttonColors(containerColor = SurfaceWhite),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderOutline),
        shape = RoundedCornerShape(12.dp)
      ) {
        Icon(Icons.Default.Download, contentDescription = null, tint = TextPrimary)
        Spacer(modifier = Modifier.width(6.dp))
        Text("Télécharger le reçu PDF officiel", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
      }

      Button(
        onClick = onClose,
        modifier = Modifier.fillMaxWidth().height(42.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
      ) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = TextSecondary)
        Spacer(modifier = Modifier.width(4.dp))
        Text("Retour à l'accueil", fontSize = 12.sp, color = TextSecondary)
      }
    }

    Spacer(modifier = Modifier.height(40.dp))
  }
}
