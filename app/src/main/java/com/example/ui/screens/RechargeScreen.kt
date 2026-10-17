package com.example.ui.screens

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.LocalConvenienceStore
import androidx.compose.material.icons.filled.PhonelinkRing
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.SwapHorizontalCircle
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BorderOutline
import com.example.ui.theme.CyanTertiary
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldPrimaryContainer
import com.example.ui.theme.GoldContainer
import com.example.ui.theme.GoldDark
import com.example.ui.theme.OrangeMoney
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.FlashKalpeViewModel

/**
 * Recharge Screen matching HTML mockup 5:
 * Top up Kalpe wallet balance via Wave, Orange Money, Card, Wire or Cash.
 */
@Composable
fun RechargeScreen(
  viewModel: FlashKalpeViewModel,
  modifier: Modifier = Modifier
) {
  val balance by viewModel.balance.collectAsState()
  var selectedAmount by remember { mutableLongStateOf(50_000L) }
  var selectedMethod by remember { mutableStateOf("Wave Sénégal") }

  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
      .padding(horizontal = 16.dp, vertical = 12.dp)
      .testTag("recharge_screen"),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. Balance & VIP Privilege Card
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.fillMaxWidth().padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            modifier = Modifier
              .clip(RoundedCornerShape(50.dp))
              .background(GoldContainer)
              .padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Icon(Icons.Default.WorkspacePremium, contentDescription = null, tint = GoldDark, modifier = Modifier.size(14.dp))
            Text("OBSIDIAN ÉLITE", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = GoldDark)
          }

          Row(
            modifier = Modifier
              .clip(RoundedCornerShape(50.dp))
              .background(EmeraldLight)
              .padding(horizontal = 8.dp, vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(13.dp))
            Text("Protégé", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
          }
        }

        Text("Solde Kalpé disponible", fontSize = 12.sp, color = TextSecondary)

        Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
          Text(
            text = FlashKalpeViewModel.formatAmount(balance),
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Monospace,
            color = TextPrimary
          )
          Text(
            text = "FCFA",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = GoldDark,
            modifier = Modifier.padding(bottom = 4.dp)
          )
        }

        Row(
          modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("Recharge maximale : 5.000.000 FCFA", fontSize = 11.sp, color = TextSecondary)
          Icon(Icons.Default.Bolt, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(16.dp))
        }
      }
    }

    // 2. Amount Input Box & Presets
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("Montant à approvisionner", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
          Text("Plafond instantané", fontSize = 11.sp, color = TextSecondary)
        }

        // Amount Display & Input Field
        OutlinedTextField(
          value = FlashKalpeViewModel.formatAmount(selectedAmount),
          onValueChange = { newVal ->
            val digits = newVal.filter { it.isDigit() }
            if (digits.isNotEmpty()) {
              val parsed = digits.toLongOrNull()?.coerceIn(1000L, 5_000_000L) ?: 50_000L
              selectedAmount = parsed
            }
          },
          modifier = Modifier.fillMaxWidth().testTag("recharge_amount_input"),
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
          shape = RoundedCornerShape(12.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = EmeraldPrimary,
            unfocusedBorderColor = BorderOutline,
            focusedContainerColor = SurfaceContainerLow,
            unfocusedContainerColor = SurfaceContainerLow
          ),
          textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Monospace,
            color = TextPrimary
          ),
          trailingIcon = {
            Text("FCFA", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextSecondary, modifier = Modifier.padding(end = 12.dp))
          }
        )

        // Quick Amount Presets
        val presets = listOf(5_000L, 10_000L, 25_000L, 50_000L)
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          presets.forEach { preset ->
            val isPresetActive = selectedAmount == preset
            Box(
              modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(if (isPresetActive) EmeraldPrimary else SurfaceContainerLow)
                .clickable { selectedAmount = preset }
                .padding(vertical = 10.dp),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = "${preset / 1000}k F",
                fontSize = 12.sp,
                fontWeight = if (isPresetActive) FontWeight.Bold else FontWeight.SemiBold,
                color = if (isPresetActive) SurfaceWhite else TextPrimary
              )
            }
          }
        }
      }
    }

    // 3. Payment Methods Group
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text("Moyen de débit", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        Box(
          modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(SurfaceContainerLow).padding(horizontal = 8.dp, vertical = 2.dp)
        ) {
          Text("5 disponibles", fontSize = 10.sp, color = TextSecondary, fontWeight = FontWeight.Bold)
        }
      }

      // Method 1: Wave Sénégal (Recommended)
      MethodCard(
        title = "Wave Sénégal",
        subtitle = "Validation en 1-clic direct • Gratuit",
        badge = "RECOMMANDÉ",
        icon = Icons.Default.SwapHorizontalCircle,
        iconTint = EmeraldPrimary,
        isSelected = selectedMethod == "Wave Sénégal",
        onClick = { selectedMethod = "Wave Sénégal" },
        contextContent = {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(8.dp))
              .background(SurfaceContainerLow)
              .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
              Icon(Icons.Default.PhonelinkRing, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(16.dp))
              Text("+221 77 ••• 89 24", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
              Text("(Natasha Diallo)", fontSize = 11.sp, color = TextSecondary)
            }
            Text("Une notification de validation sera envoyée sur votre app Wave.", fontSize = 10.sp, color = TextSecondary)
          }
        }
      )

      // Method 2: Orange Money
      MethodCard(
        title = "Orange Money Sénégal",
        subtitle = "Code de confirmation #144# • 0% frais",
        badge = "0%",
        icon = Icons.Default.AccountBalanceWallet,
        iconTint = OrangeMoney,
        isSelected = selectedMethod == "Orange Money Sénégal",
        onClick = { selectedMethod = "Orange Money Sénégal" }
      )

      // Method 3: Carte Visa / Mastercard
      MethodCard(
        title = "Carte Visa / Mastercard",
        subtitle = "GIM-UEMOA & Réseaux Internationaux (3D-Secure)",
        badge = "3D-SECURE",
        icon = Icons.Default.CreditCard,
        iconTint = TextPrimary,
        isSelected = selectedMethod == "Carte Visa / Mastercard",
        onClick = { selectedMethod = "Carte Visa / Mastercard" }
      )

      // Method 4: Virement Express UEMOA
      MethodCard(
        title = "Virement Express UEMOA",
        subtitle = "IBAN nominatif dédié FlashKalpé",
        badge = null,
        icon = Icons.Default.AccountBalance,
        iconTint = TextPrimary,
        isSelected = selectedMethod == "Virement Express UEMOA",
        onClick = { selectedMethod = "Virement Express UEMOA" }
      )

      // Method 5: Cash
      MethodCard(
        title = "Dépôt Espèces Partenaire",
        subtitle = "Guichets agréés Dakar, Thiès, Mbour",
        badge = null,
        icon = Icons.Default.LocalConvenienceStore,
        iconTint = TextPrimary,
        isSelected = selectedMethod == "Dépôt Espèces Partenaire",
        onClick = { selectedMethod = "Dépôt Espèces Partenaire" }
      )
    }

    // 4. Summary Card
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
          Text("Frais de transfert", fontSize = 12.sp, color = TextSecondary)
          Text("0 FCFA (Offert)", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
          Text("Montant crédité", fontSize = 12.sp, color = TextSecondary)
          Text("${FlashKalpeViewModel.formatAmount(selectedAmount)} FCFA", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        }
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(BorderOutline))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
          Text("Nouveau solde estimé", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
          Text(
            text = "${FlashKalpeViewModel.formatAmount(balance + selectedAmount)} FCFA",
            fontSize = 14.sp,
            fontWeight = FontWeight.ExtraBold,
            color = EmeraldPrimary
          )
        }
      }
    }

    // 5. Action Button
    Button(
      onClick = { viewModel.performRecharge(selectedAmount, selectedMethod) },
      modifier = Modifier
        .fillMaxWidth()
        .height(52.dp)
        .testTag("submit_recharge_btn"),
      colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimaryContainer),
      shape = RoundedCornerShape(14.dp)
    ) {
      Icon(Icons.Default.Bolt, contentDescription = null, tint = EmeraldDark)
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "Recharger ${FlashKalpeViewModel.formatAmount(selectedAmount)} FCFA sans frais",
        color = EmeraldDark,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
      )
    }

    // Disclaimer
    Row(
      modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {
      Icon(Icons.Default.Security, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(16.dp))
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "Établissement agréé EME Banque Centrale (BCEAO). Fonds séquestrés à 100%.",
        fontSize = 10.sp,
        color = TextSecondary,
        textAlign = androidx.compose.ui.text.style.TextAlign.Center
      )
    }

    Spacer(modifier = Modifier.height(60.dp))
  }
}

@Composable
private fun MethodCard(
  title: String,
  subtitle: String,
  badge: String?,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  iconTint: Color,
  isSelected: Boolean,
  onClick: () -> Unit,
  contextContent: (@Composable () -> Unit)? = null
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onClick() }
      .border(
        width = if (isSelected) 1.5.dp else 1.dp,
        color = if (isSelected) EmeraldPrimary else BorderOutline,
        shape = RoundedCornerShape(16.dp)
      ),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
    elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 2.dp else 1.dp)
  ) {
    Column(
      modifier = Modifier.fillMaxWidth().padding(14.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          modifier = Modifier.weight(1f).padding(end = 8.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Box(
            modifier = Modifier.size(40.dp).clip(CircleShape).background(SurfaceContainerLow),
            contentAlignment = Alignment.Center
          ) {
            Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(20.dp))
          }

          Column {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
              Text(title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
              if (badge != null) {
                Box(
                  modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(EmeraldLight).padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                  Text(badge, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
                }
              }
            }
            Text(subtitle, fontSize = 11.sp, color = TextSecondary)
          }
        }

        Box(
          modifier = Modifier
            .size(22.dp)
            .clip(CircleShape)
            .background(if (isSelected) EmeraldPrimary else SurfaceContainerLow),
          contentAlignment = Alignment.Center
        ) {
          if (isSelected) {
            Icon(Icons.Default.Check, contentDescription = null, tint = SurfaceWhite, modifier = Modifier.size(14.dp))
          }
        }
      }

      if (isSelected && contextContent != null) {
        contextContent()
      }
    }
  }
}
