package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Nfc
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SyncLock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.ui.theme.ErrorContainer
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.GoldAmber
import com.example.ui.theme.GoldContainer
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.ObsidianCard
import com.example.ui.theme.ObsidianDark
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.FlashKalpeViewModel

/**
 * Pixel-perfect FlashKalpe Gold VIP Mastercard component matching the screenshot exactly.
 */
@Composable
fun GoldCardView(
  isFrozen: Boolean,
  isCvvVisible: Boolean,
  cvvCode: String,
  onToggleCvv: () -> Unit,
  modifier: Modifier = Modifier
) {
  val goldGradient = Brush.linearGradient(
    colors = if (isFrozen) {
      listOf(Color(0xFFB0BEC5), Color(0xFFCFD8DC), Color(0xFF90A4AE))
    } else {
      listOf(GoldMuted, GoldLight, GoldPrimary)
    },
    start = Offset(0f, 0f),
    end = Offset(1000f, 600f)
  )

  Card(
    modifier = modifier
      .fillMaxWidth()
      .aspectRatio(1.586f)
      .shadow(elevation = 16.dp, shape = RoundedCornerShape(22.dp), spotColor = Color(0x33000000))
      .testTag("gold_card_container"),
    shape = RoundedCornerShape(22.dp),
    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(goldGradient)
        .padding(20.dp)
    ) {
      // Shimmer overlay
      Canvas(modifier = Modifier.fillMaxSize()) {
        drawCircle(
          brush = Brush.radialGradient(
            colors = listOf(Color.White.copy(alpha = 0.35f), Color.Transparent),
            center = Offset(size.width * 0.85f, size.height * 0.15f),
            radius = size.width * 0.5f
          )
        )
      }

      Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
      ) {
        // Top Bar: Logo + Brand + NFC
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Box(
              modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(SurfaceWhite.copy(alpha = 0.85f)),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Bolt,
                contentDescription = "FlashKalpe Lightning",
                tint = EmeraldPrimary,
                modifier = Modifier.size(22.dp)
              )
            }
            Column {
              Text(
                text = "FLASHKALPE",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 13.sp,
                letterSpacing = 1.2.sp,
                color = TextPrimary
              )
              Text(
                text = "WORLD ELITE GOLD",
                fontWeight = FontWeight.SemiBold,
                fontSize = 9.sp,
                letterSpacing = 1.5.sp,
                color = TextSecondary
              )
            }
          }

          // NFC Badge
          Row(
            modifier = Modifier
              .clip(RoundedCornerShape(50.dp))
              .background(SurfaceWhite.copy(alpha = 0.75f))
              .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Nfc,
              contentDescription = "NFC",
              tint = TextPrimary,
              modifier = Modifier.size(14.dp)
            )
            Text(
              text = "NFC",
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              color = TextPrimary
            )
          }
        }

        // EMV Chip & Shield
        Row(
          modifier = Modifier.padding(vertical = 4.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          // EMV Chip
          Box(
            modifier = Modifier
              .width(42.dp)
              .height(30.dp)
              .clip(RoundedCornerShape(6.dp))
              .background(
                Brush.linearGradient(
                  listOf(Color(0xFFFFE79A), Color(0xFFE1BA54), Color(0xFFC49326))
                )
              )
              .border(0.5.dp, Color.Black.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
              .padding(3.dp)
          ) {
            Column(
              modifier = Modifier.fillMaxSize(),
              verticalArrangement = Arrangement.SpaceEvenly
            ) {
              Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.Black.copy(alpha = 0.25f)))
              Box(modifier = Modifier.fillMaxWidth(0.75f).height(1.dp).background(Color.Black.copy(alpha = 0.25f)))
              Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.Black.copy(alpha = 0.25f)))
            }
          }

          // Hologram shield
          Box(
            modifier = Modifier
              .size(24.dp)
              .clip(CircleShape)
              .background(SurfaceWhite.copy(alpha = 0.4f)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Shield,
              contentDescription = "Security",
              tint = TextSecondary,
              modifier = Modifier.size(13.dp)
            )
          }

          if (isFrozen) {
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(ErrorRed)
                .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
              Text(
                text = "GELÉE",
                color = SurfaceWhite,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
              )
            }
          }
        }

        // Card Number & CVV Toggle
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "••••  ••••  ••••  8924",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            letterSpacing = 2.sp,
            color = TextPrimary
          )

          // CVV toggle button
          Row(
            modifier = Modifier
              .clip(RoundedCornerShape(8.dp))
              .background(SurfaceWhite.copy(alpha = 0.85f))
              .clickable { onToggleCvv() }
              .padding(horizontal = 8.dp, vertical = 4.dp)
              .testTag("toggle_cvv_button"),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Text(
              text = "CVV",
              fontSize = 10.sp,
              fontWeight = FontWeight.SemiBold,
              color = TextSecondary
            )
            Text(
              text = if (isCvvVisible) cvvCode else "•••",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace,
              color = TextPrimary
            )
            Icon(
              imageVector = if (isCvvVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
              contentDescription = "Toggle CVV",
              tint = EmeraldPrimary,
              modifier = Modifier.size(14.dp)
            )
          }
        }

        // Card Holder & Expiry & Mastercard Logo
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.Bottom
        ) {
          Column {
            Text(
              text = "TITULAIRE",
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.sp,
              color = TextSecondary
            )
            Text(
              text = "NATASHA DIALLO",
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 0.8.sp,
              color = TextPrimary
            )
          }

          Column(horizontalAlignment = Alignment.End) {
            Text(
              text = "EXPIRE",
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.sp,
              color = TextSecondary
            )
            Text(
              text = "08/28",
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              fontFamily = FontFamily.Monospace,
              color = TextPrimary
            )
          }

          // Dual Network Mastercard spheres
          Row(
            modifier = Modifier.padding(start = 6.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Color(0xFFEB001B).copy(alpha = 0.95f))
            )
            Box(
              modifier = Modifier
                .offset(x = (-9).dp)
                .size(24.dp)
                .clip(CircleShape)
                .background(Color(0xFFF79E1B).copy(alpha = 0.95f))
            )
          }
        }
      }
    }
  }
}

/**
 * Dynamic CVV countdown banner with animated circular progress indicator.
 */
@Composable
fun DynamicCvvBanner(
  countdownSeconds: Int,
  modifier: Modifier = Modifier
) {
  val progress = countdownSeconds / 60f
  val animatedProgress by animateFloatAsState(
    targetValue = progress,
    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
    label = "cvv_progress"
  )

  Card(
    modifier = modifier
      .fillMaxWidth()
      .testTag("dynamic_cvv_banner"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Box(
          modifier = Modifier
            .size(38.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(EmeraldLight),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.SyncLock,
            contentDescription = "Sync Lock",
            tint = EmeraldPrimary,
            modifier = Modifier.size(20.dp)
          )
        }

        Column {
          Text(
            text = "Cryptogramme dynamique",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary
          )
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = "Régénéré dans ",
              fontSize = 13.sp,
              color = GoldDark
            )
            Text(
              text = "${countdownSeconds}s",
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              color = EmeraldPrimary
            )
          }
        }
      }

      // Circular progress bar
      Box(
        modifier = Modifier.size(30.dp),
        contentAlignment = Alignment.Center
      ) {
        Canvas(modifier = Modifier.size(28.dp)) {
          // Background track
          drawCircle(
            color = SurfaceContainerLow,
            style = Stroke(width = 3.dp.toPx())
          )
          // Progress arc
          drawArc(
            color = EmeraldPrimary,
            startAngle = -90f,
            sweepAngle = 360f * animatedProgress,
            useCenter = false,
            style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
          )
        }
      }
    }
  }
}

/**
 * 3 Quick Action Buttons: Freeze/Active, Code PIN, Wallet
 */
@Composable
fun CardActionButtons(
  isFrozen: Boolean,
  onToggleFreeze: () -> Unit,
  onOpenPin: () -> Unit,
  onOpenWallet: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    // 1: Freeze Toggle
    Card(
      modifier = Modifier
        .weight(1f)
        .clickable { onToggleFreeze() }
        .testTag("freeze_card_btn"),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 14.dp, horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Box(
          modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(if (isFrozen) ErrorContainer else SurfaceContainerLow),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = if (isFrozen) Icons.Default.Lock else Icons.Default.LockOpen,
            contentDescription = if (isFrozen) "Carte gelée" else "Carte active",
            tint = if (isFrozen) ErrorRed else TextPrimary,
            modifier = Modifier.size(20.dp)
          )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
            text = if (isFrozen) "Gelée" else "Active",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = if (isFrozen) ErrorRed else TextPrimary
          )
          Text(
            text = if (isFrozen) "Débloquer" else "Geler la carte",
            fontSize = 10.sp,
            color = GoldDark
          )
        }
      }
    }

    // 2: PIN
    Card(
      modifier = Modifier
        .weight(1f)
        .clickable { onOpenPin() }
        .testTag("pin_action_btn"),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 14.dp, horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Box(
          modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(GoldContainer),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Password,
            contentDescription = "Code PIN",
            tint = GoldDark,
            modifier = Modifier.size(20.dp)
          )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
            text = "Code PIN",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
          Text(
            text = "Face ID requis",
            fontSize = 10.sp,
            color = GoldDark
          )
        }
      }
    }

    // 3: Wallet
    Card(
      modifier = Modifier
        .weight(1f)
        .clickable { onOpenWallet() }
        .testTag("wallet_action_btn"),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 14.dp, horizontal = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Box(
          modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(CyanContainer),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Wallet,
            contentDescription = "Wallet",
            tint = CyanTertiary,
            modifier = Modifier.size(20.dp)
          )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
            text = "Wallet",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
          Text(
            text = "Connecté",
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = EmeraldPrimary
          )
        }
      }
    }
  }
}

/**
 * Monthly spending limits card with progress bars matching the screenshot.
 */
@Composable
fun SpendingLimitsCard(
  cardSpent: Long,
  cardLimit: Long,
  atmSpent: Long,
  atmLimit: Long,
  onModifyClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val cardPercent = (cardSpent.toFloat() / cardLimit).coerceIn(0f, 1f)
  val atmPercent = (atmSpent.toFloat() / atmLimit).coerceIn(0f, 1f)

  Card(
    modifier = modifier
      .fillMaxWidth()
      .testTag("spending_limits_card"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      // Header
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Plafonds mensuels",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
          Text(
            text = "Réinitialisation dans 11 jours",
            fontSize = 13.sp,
            color = GoldDark
          )
        }
        Text(
          text = "Modifier",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = EmeraldPrimary,
          modifier = Modifier
            .clickable { onModifyClick() }
            .padding(4.dp)
            .testTag("modify_limits_btn")
        )
      }

      // Card & Web Payments Bar
      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.Bottom
        ) {
          Text(
            text = "Paiements Carte & Web",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary
          )
          Row {
            Text(
              text = FlashKalpeViewModel.formatAmount(cardSpent),
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              color = TextPrimary
            )
            Text(
              text = " / ${FlashKalpeViewModel.formatAmount(cardLimit)} FCFA",
              fontSize = 13.sp,
              color = GoldDark
            )
          }
        }

        LinearProgressIndicator(
          progress = { cardPercent },
          modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .clip(RoundedCornerShape(5.dp)),
          color = EmeraldPrimary,
          trackColor = SurfaceContainerLow,
        )

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = "Utilisé : ${(cardPercent * 100).toInt()}%",
            fontSize = 11.sp,
            color = GoldDark
          )
          Text(
            text = "Reste : ${FlashKalpeViewModel.formatAmount(cardLimit - cardSpent)} FCFA",
            fontSize = 11.sp,
            color = GoldDark
          )
        }
      }

      // ATM Withdrawals Bar
      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.Bottom
        ) {
          Text(
            text = "Retraits Distributeurs (DAB)",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextPrimary
          )
          Row {
            Text(
              text = FlashKalpeViewModel.formatAmount(atmSpent),
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              color = TextPrimary
            )
            Text(
              text = " / ${FlashKalpeViewModel.formatAmount(atmLimit)} FCFA",
              fontSize = 13.sp,
              color = GoldDark
            )
          }
        }

        LinearProgressIndicator(
          progress = { atmPercent },
          modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .clip(RoundedCornerShape(5.dp)),
          color = CyanTertiary.copy(alpha = 0.8f),
          trackColor = SurfaceContainerLow,
        )

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = "Utilisé : ${(atmPercent * 100).toInt()}%",
            fontSize = 11.sp,
            color = GoldDark
          )
          Text(
            text = "Reste : ${FlashKalpeViewModel.formatAmount(atmLimit - atmSpent)} FCFA",
            fontSize = 11.sp,
            color = GoldDark
          )
        }
      }
    }
  }
}

/**
 * Authorization Channels Card with 4 switches (E-commerce, International, ATM, NFC)
 */
@Composable
fun AuthorizationChannelsCard(
  ecommerceEnabled: Boolean,
  internationalEnabled: Boolean,
  atmEnabled: Boolean,
  nfcEnabled: Boolean,
  onToggleEcommerce: () -> Unit,
  onToggleInternational: () -> Unit,
  onToggleAtm: () -> Unit,
  onToggleNfc: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .testTag("authorization_channels_card"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      Text(
        text = "Canaux d'autorisation",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = TextPrimary
      )

      ChannelRow(
        icon = Icons.Default.ShoppingCart,
        title = "Paiements E-commerce",
        subtitle = "Achats en ligne & abonnements 3D-Secure",
        checked = ecommerceEnabled,
        onCheckedChange = { onToggleEcommerce() }
      )

      ChannelRow(
        icon = Icons.Default.Public,
        title = "International & Devises",
        subtitle = "Taux de change interbancaire direct (EUR, USD)",
        checked = internationalEnabled,
        onCheckedChange = { onToggleInternational() }
      )

      ChannelRow(
        icon = Icons.Default.CreditCard,
        title = "Retraits aux Distributeurs",
        subtitle = "Réseau GIM-UEMOA et Mastercard mondial",
        checked = atmEnabled,
        onCheckedChange = { onToggleAtm() }
      )

      ChannelRow(
        icon = Icons.Default.Nfc,
        title = "Paiement Sans Contact (NFC)",
        subtitle = "Jusqu'à 30.000 FCFA sans saisie de code",
        checked = nfcEnabled,
        onCheckedChange = { onToggleNfc() }
      )
    }
  }
}

@Composable
private fun ChannelRow(
  icon: ImageVector,
  title: String,
  subtitle: String,
  checked: Boolean,
  onCheckedChange: (Boolean) -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 4.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(
      modifier = Modifier.weight(1f).padding(end = 12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      Box(
        modifier = Modifier
          .size(34.dp)
          .clip(RoundedCornerShape(8.dp))
          .background(SurfaceContainerLow),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = title,
          tint = TextPrimary,
          modifier = Modifier.size(18.dp)
        )
      }

      Column {
        Text(
          text = title,
          fontSize = 13.sp,
          fontWeight = FontWeight.SemiBold,
          color = TextPrimary
        )
        Text(
          text = subtitle,
          fontSize = 11.sp,
          color = GoldDark,
          maxLines = 1
        )
      }
    }

    Switch(
      checked = checked,
      onCheckedChange = onCheckedChange,
      colors = SwitchDefaults.colors(
        checkedThumbColor = SurfaceWhite,
        checkedTrackColor = EmeraldPrimary,
        uncheckedThumbColor = SurfaceWhite,
        uncheckedTrackColor = SurfaceContainerLow,
        uncheckedBorderColor = BorderOutline
      )
    )
  }
}

/**
 * Prestige Black Metal Upgrade Tier Banner.
 */
@Composable
fun PrestigeMetalCard(
  onDiscoverClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .shadow(8.dp, RoundedCornerShape(18.dp))
      .testTag("prestige_metal_card"),
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = ObsidianSurface)
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .background(
          Brush.horizontalGradient(
            listOf(ObsidianDark, ObsidianSurface, ObsidianCard)
          )
        )
        .padding(16.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(
          modifier = Modifier.weight(1f).padding(end = 12.dp),
          verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(
              imageVector = Icons.Default.WorkspacePremium,
              contentDescription = "Prestige",
              tint = CyanTertiary,
              modifier = Modifier.size(16.dp)
            )
            Text(
              text = "PRESTIGE BLACK METAL",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.sp,
              color = CyanTertiary
            )
          }

          Text(
            text = "Carte Métal Pure 18g",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = SurfaceWhite
          )

          Text(
            text = "Acier brossé, conciergerie 24/7 & salons d'aéroport illimités.",
            fontSize = 12.sp,
            color = Color(0xFF94A3B8),
            lineHeight = 16.sp
          )
        }

        Button(
          onClick = onDiscoverClick,
          shape = RoundedCornerShape(12.dp),
          colors = ButtonDefaults.buttonColors(containerColor = CyanTertiary),
          contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 10.dp)
        ) {
          Text(
            text = "Découvrir",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = SurfaceWhite
          )
        }
      }
    }
  }
}

/**
 * Emergency Opposition Button
 */
@Composable
fun EmergencyOppositionButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .clickable { onClick() }
      .testTag("emergency_opposition_btn"),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = ErrorContainer.copy(alpha = 0.8f))
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 14.dp, horizontal = 16.dp),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        imageVector = Icons.Default.ReportProblem,
        contentDescription = "Attention",
        tint = ErrorRed,
        modifier = Modifier.size(20.dp)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = "Faire opposition (Perte ou Vol)",
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = ErrorRed
      )
    }
  }
}
