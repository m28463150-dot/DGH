package com.example.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ContactSupport
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CellTower
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Waves
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BorderOutline
import com.example.ui.theme.CyanContainer
import com.example.ui.theme.CyanTertiary
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldPrimaryContainer
import com.example.ui.theme.GoldAmber
import com.example.ui.theme.GoldContainer
import com.example.ui.theme.GoldDark
import com.example.ui.theme.OrangeMoney
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.WaveBlue
import com.example.viewmodel.FlashKalpeViewModel

/**
 * Merchant QR Scanner Screen matching HTML mockup 4:
 * Scan & Pay merchant instant checkout (Café de Rome Dakar, Cashback +2.5%).
 */
@Composable
fun QrScannerScreen(
  viewModel: FlashKalpeViewModel,
  modifier: Modifier = Modifier
) {
  var isTorchOn by remember { mutableStateOf(false) }
  var paymentAmount by remember { mutableLongStateOf(14_500L) }
  val balance by viewModel.balance.collectAsState()

  val infiniteTransition = rememberInfiniteTransition(label = "scan_laser")
  val laserPosition by infiniteTransition.animateFloat(
    initialValue = 0.1f,
    targetValue = 0.9f,
    animationSpec = infiniteRepeatable(
      animation = tween(1800, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "laser_y"
  )

  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
      .padding(horizontal = 16.dp, vertical = 12.dp)
      .testTag("qr_scanner_screen"),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Top Bar Actions
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Box(
          modifier = Modifier.size(40.dp).clip(CircleShape).background(SurfaceContainerLow),
          contentAlignment = Alignment.Center
        ) {
          Icon(Icons.Default.QrCodeScanner, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(22.dp))
        }
        Column {
          Text("Scanner & Payer", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
          Text("PAIEMENT MARCHAND INSTANTANÉ", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = GoldDark)
        }
      }

      Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        IconButton(
          onClick = { isTorchOn = !isTorchOn },
          modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
            .background(if (isTorchOn) EmeraldPrimaryContainer else SurfaceWhite)
        ) {
          Icon(
            imageVector = if (isTorchOn) Icons.Default.FlashOff else Icons.Default.FlashOn,
            contentDescription = "Torch",
            tint = if (isTorchOn) EmeraldDark else TextPrimary,
            modifier = Modifier.size(18.dp)
          )
        }

        IconButton(
          onClick = { viewModel.showFeedback("Positionnez le QR code au centre du cadre pour détection automatique.") },
          modifier = Modifier.size(38.dp).clip(CircleShape).background(SurfaceWhite)
        ) {
          Icon(Icons.AutoMirrored.Filled.ContactSupport, contentDescription = "Aide", tint = TextSecondary, modifier = Modifier.size(18.dp))
        }
      }
    }

    // Viewfinder Canvas Camera Box
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(1f),
      shape = RoundedCornerShape(20.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
    ) {
      Box(modifier = Modifier.fillMaxSize()) {
        // Simulated Camera Feed & Target QR
        Canvas(modifier = Modifier.fillMaxSize()) {
          val strokeWidth = 4.dp.toPx()
          val cornerSize = 28.dp.toPx()
          val padding = 40.dp.toPx()

          val left = padding
          val top = padding
          val right = size.width - padding
          val bottom = size.height - padding

          // 4 Corner brackets
          // Top-Left
          drawLine(EmeraldPrimary, Offset(left, top), Offset(left + cornerSize, top), strokeWidth)
          drawLine(EmeraldPrimary, Offset(left, top), Offset(left, top + cornerSize), strokeWidth)

          // Top-Right
          drawLine(EmeraldPrimary, Offset(right, top), Offset(right - cornerSize, top), strokeWidth)
          drawLine(EmeraldPrimary, Offset(right, top), Offset(right, top + cornerSize), strokeWidth)

          // Bottom-Left
          drawLine(EmeraldPrimary, Offset(left, bottom), Offset(left + cornerSize, bottom), strokeWidth)
          drawLine(EmeraldPrimary, Offset(left, bottom), Offset(left, bottom - cornerSize), strokeWidth)

          // Bottom-Right
          drawLine(EmeraldPrimary, Offset(right, bottom), Offset(right - cornerSize, bottom), strokeWidth)
          drawLine(EmeraldPrimary, Offset(right, bottom), Offset(right, bottom - cornerSize), strokeWidth)

          // Laser scanning line
          val laserY = top + (bottom - top) * laserPosition
          drawLine(
            color = EmeraldPrimaryContainer,
            start = Offset(left + 10.dp.toPx(), laserY),
            end = Offset(right - 10.dp.toPx(), laserY),
            strokeWidth = 3.dp.toPx()
          )
        }

        // Center simulated QR graphic
        Box(
          modifier = Modifier
            .size(160.dp)
            .align(Alignment.Center)
            .clip(RoundedCornerShape(14.dp))
            .background(SurfaceWhite)
            .padding(14.dp),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.QrCodeScanner,
            contentDescription = null,
            tint = TextPrimary.copy(alpha = 0.8f),
            modifier = Modifier.fillMaxSize()
          )
          Box(
            modifier = Modifier.size(32.dp).clip(CircleShape).background(EmeraldPrimary),
            contentAlignment = Alignment.Center
          ) {
            Icon(Icons.Default.Bolt, contentDescription = null, tint = SurfaceWhite, modifier = Modifier.size(20.dp))
          }
        }

        // Bottom instruction pill
        Row(
          modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 12.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(SurfaceWhite.copy(alpha = 0.95f))
            .padding(horizontal = 12.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(EmeraldPrimary))
          Text("Pointez vers le QR Code marchand", fontSize = 11.sp, color = TextPrimary, fontWeight = FontWeight.Medium)
        }
      }
    }

    // Manual input & gallery import
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      Button(
        onClick = { viewModel.showFeedback("Entrez le code marchand à 6 chiffres : FK-8820") },
        modifier = Modifier.weight(1f).height(44.dp),
        colors = ButtonDefaults.buttonColors(containerColor = SurfaceWhite),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderOutline),
        shape = RoundedCornerShape(10.dp)
      ) {
        Icon(Icons.Default.Pin, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text("Code marchand", fontSize = 12.sp, color = TextPrimary, fontWeight = FontWeight.SemiBold)
      }

      Button(
        onClick = { viewModel.showFeedback("Sélectionnez une image de QR Code depuis votre galerie.") },
        modifier = Modifier.weight(1f).height(44.dp),
        colors = ButtonDefaults.buttonColors(containerColor = SurfaceWhite),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderOutline),
        shape = RoundedCornerShape(10.dp)
      ) {
        Icon(Icons.Default.PhotoLibrary, contentDescription = null, tint = CyanTertiary, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text("Importer photo", fontSize = 12.sp, color = TextPrimary, fontWeight = FontWeight.SemiBold)
      }
    }

    // Identified Merchant Card
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.Top
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            Box(
              modifier = Modifier
                .size(46.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF263238)),
              contentAlignment = Alignment.Center
            ) {
              Text("CR", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = GoldAmber)
            }

            Column {
              Text("Café de Rome Dakar", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
              Text("Restaurant & Lounge Privilège", fontSize = 12.sp, color = TextSecondary)
            }
          }

          Box(
            modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(GoldContainer).padding(horizontal = 8.dp, vertical = 3.dp)
          ) {
            Text("Vérifié FlashKalpe", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = GoldDark)
          }
        }

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(SurfaceContainerLow)
            .padding(horizontal = 10.dp, vertical = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Icon(Icons.Default.LocationOn, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(14.dp))
            Text("Plateau, Dakar", fontSize = 11.sp, color = TextSecondary)
          }
          Text("ID: FK-DKR-8820", fontSize = 11.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace, color = TextPrimary)
        }

        // Cashback Banner
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(EmeraldLight)
            .padding(10.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
              modifier = Modifier.size(28.dp).clip(CircleShape).background(EmeraldPrimaryContainer),
              contentAlignment = Alignment.Center
            ) {
              Icon(Icons.Default.Savings, contentDescription = null, tint = EmeraldDark, modifier = Modifier.size(16.dp))
            }
            Column {
              Text("Cashback Kalpe", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
              Text("+2.5% reversé en épargne", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
            }
          }
          Icon(Icons.Default.Bolt, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(18.dp))
        }
      }
    }

    // Debit source selected
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("MOYEN DE DÉBIT SÉLECTIONNÉ", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = TextSecondary)
          Text("Modifier", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
        }

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(SurfaceContainerLow)
            .padding(12.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(
              modifier = Modifier.size(36.dp).clip(RoundedCornerShape(6.dp)).background(TextPrimary),
              contentAlignment = Alignment.Center
            ) {
              Icon(Icons.Default.CreditCard, contentDescription = null, tint = GoldAmber, modifier = Modifier.size(20.dp))
            }
            Column {
              Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Carte Flash Gold", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Box(modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(CyanContainer).padding(horizontal = 4.dp, vertical = 1.dp)) {
                  Text("VIP", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = CyanTertiary)
                }
              }
              Text("Solde: ${FlashKalpeViewModel.formatAmount(balance)} FCFA", fontSize = 11.sp, color = TextSecondary)
            }
          }
          Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(20.dp))
        }
      }
    }

    // Validate & Pay Button
    Button(
      onClick = { viewModel.payMerchantQr(paymentAmount, "Café de Rome Dakar") },
      modifier = Modifier
        .fillMaxWidth()
        .height(52.dp)
        .testTag("validate_pay_btn"),
      colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimaryContainer),
      shape = RoundedCornerShape(14.dp)
    ) {
      Text(
        text = "Payer ${FlashKalpeViewModel.formatAmount(paymentAmount)} FCFA",
        color = EmeraldDark,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold
      )
      Spacer(modifier = Modifier.width(8.dp))
      Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = EmeraldDark, modifier = Modifier.size(18.dp))
    }

    // Interoperability disclaimer
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(SurfaceContainerLow)
        .padding(10.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {
      Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(16.dp))
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "Compatible Wave, Orange Money & réseau GIM-UEMOA",
        fontSize = 11.sp,
        color = TextSecondary
      )
    }

    Spacer(modifier = Modifier.height(60.dp))
  }
}
