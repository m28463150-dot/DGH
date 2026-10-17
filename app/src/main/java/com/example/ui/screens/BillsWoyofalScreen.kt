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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Router
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Toll
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BorderOutline
import com.example.ui.theme.CyanTertiary
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldPrimaryContainer
import com.example.ui.theme.ErrorContainer
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.GoldDark
import com.example.ui.theme.OrangeMoney
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.FlashKalpeViewModel

/**
 * Bills & Woyofal Electricity Screen matching HTML mockup 6:
 * Senelec Woyofal prepaid token instant buy, Sen'Eau, Canal+, Rapido.
 */
@Composable
fun BillsWoyofalScreen(
  viewModel: FlashKalpeViewModel,
  modifier: Modifier = Modifier
) {
  var searchQuery by remember { mutableStateOf("") }
  var selectedCategory by remember { mutableStateOf("Tous") }
  var meterNumber by remember { mutableStateOf("0429 1844 910") }
  var woyofalAmount by remember { mutableLongStateOf(10_000L) }
  var estimatedKwh by remember { mutableStateOf("112,5 kWh") }

  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
      .padding(horizontal = 16.dp, vertical = 12.dp)
      .testTag("bills_woyofal_screen"),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. Search Bar
    OutlinedTextField(
      value = searchQuery,
      onValueChange = { searchQuery = it },
      modifier = Modifier.fillMaxWidth().testTag("bill_search_input"),
      placeholder = { Text("Rechercher un service, compteur, référence...", fontSize = 13.sp, color = TextSecondary) },
      leadingIcon = {
        Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(20.dp))
      },
      trailingIcon = {
        Icon(Icons.Default.QrCodeScanner, contentDescription = "Scan facture", tint = EmeraldPrimary, modifier = Modifier.size(20.dp))
      },
      shape = RoundedCornerShape(14.dp),
      colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = EmeraldPrimary,
        unfocusedBorderColor = BorderOutline,
        focusedContainerColor = SurfaceWhite,
        unfocusedContainerColor = SurfaceWhite
      ),
      singleLine = true
    )

    // 2. Category Pills
    val categories = listOf("Tous", "Électricité", "Eau", "Internet & TV", "Services publics")
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      items(categories) { cat ->
        val isCatActive = selectedCategory == cat
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(if (isCatActive) TextPrimary else SurfaceWhite)
            .border(1.dp, if (isCatActive) TextPrimary else BorderOutline, RoundedCornerShape(50.dp))
            .clickable { selectedCategory = cat }
            .padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
          Text(
            text = cat,
            fontSize = 12.sp,
            fontWeight = if (isCatActive) FontWeight.Bold else FontWeight.Medium,
            color = if (isCatActive) SurfaceWhite else TextSecondary
          )
        }
      }
    }

    // 3. Woyofal Express Spotlight Hero Card
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
        // Card Header
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
              modifier = Modifier.size(42.dp).clip(RoundedCornerShape(10.dp)).background(SurfaceContainerLow),
              contentAlignment = Alignment.Center
            ) {
              Icon(Icons.Default.ElectricBolt, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(24.dp))
            }
            Column {
              Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Senelec Woyofal", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Box(modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(SurfaceContainerLow).padding(horizontal = 5.dp, vertical = 2.dp)) {
                  Text("INSTANTANÉ", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = TextSecondary)
                }
              }
              Text("Recharge de code prépayé 24h/7", fontSize = 11.sp, color = TextSecondary)
            }
          }

          Box(
            modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(EmeraldLight).padding(horizontal = 8.dp, vertical = 4.dp)
          ) {
            Text("0% Frais", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
          }
        }

        // Meter Selector
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainerLow)
            .padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("NUMÉRO DE COMPTEUR", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = TextSecondary)
            Text("Mes compteurs", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
          }

          Text(
            text = meterNumber,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            color = TextPrimary
          )

          Row(
            modifier = Modifier.fillMaxWidth().padding(top = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
              Icon(Icons.Default.LocationOn, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(14.dp))
              Text("Domicile Almadies (Principal)", fontSize = 11.sp, color = TextSecondary)
            }
            Text("Vérifié", fontSize = 10.sp, color = EmeraldPrimary, fontWeight = FontWeight.SemiBold)
          }
        }

        // Quick Preset Amounts
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          Text("CHOISIR LE MONTANT", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = TextSecondary)
          Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            val woyofalPresets = listOf(
              Triple(2_000L, "2.000 F", "22,5 kWh"),
              Triple(5_000L, "5.000 F", "56,2 kWh"),
              Triple(10_000L, "10.000 F", "112,5 kWh"),
              Triple(25_000L, "25.000 F", "281,2 kWh")
            )

            woyofalPresets.forEach { (amt, label, kwh) ->
              val isPresetActive = woyofalAmount == amt
              Box(
                modifier = Modifier
                  .weight(1f)
                  .clip(RoundedCornerShape(10.dp))
                  .background(if (isPresetActive) TextPrimary else SurfaceContainerLow)
                  .clickable {
                    woyofalAmount = amt
                    estimatedKwh = kwh
                  }
                  .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = label,
                  fontSize = 12.sp,
                  fontWeight = if (isPresetActive) FontWeight.Bold else FontWeight.SemiBold,
                  color = if (isPresetActive) SurfaceWhite else TextPrimary
                )
              }
            }
          }
        }

        // Calculation preview tile
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(SurfaceContainerLow)
            .padding(12.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(Icons.Default.Insights, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(18.dp))
            Column {
              Text("Énergie estimée (Tranche 1-2)", fontSize = 11.sp, color = TextSecondary)
              Text("≈ $estimatedKwh", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            }
          }

          Column(horizontalAlignment = Alignment.End) {
            Text("Coût réel", fontSize = 10.sp, color = TextSecondary)
            Text("${FlashKalpeViewModel.formatAmount(woyofalAmount)} FCFA", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
          }
        }

        // CTA
        Button(
          onClick = { viewModel.payWoyofal(woyofalAmount, meterNumber) },
          modifier = Modifier.fillMaxWidth().height(48.dp).testTag("buy_woyofal_btn"),
          colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimaryContainer),
          shape = RoundedCornerShape(12.dp)
        ) {
          Icon(Icons.Default.Bolt, contentDescription = null, tint = EmeraldDark)
          Spacer(modifier = Modifier.width(6.dp))
          Text("Acheter Woyofal (0% frais)", color = EmeraldDark, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
      }
    }

    // 4. Pending Due Bills
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
          Text("Factures en attente", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
          Box(
            modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(ErrorContainer).padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Text("2 dues", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = ErrorRed)
          }
        }
        Text("Historique", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
      }

      // Sen'Eau Bill
      BillCard(
        title = "Sen'Eau Distribution",
        subtitle = "Facture Mai • Réf: SN-849120",
        amount = "14.850 F",
        dueDate = "Échéance 28 Mai",
        icon = Icons.Default.WaterDrop,
        iconTint = CyanTertiary,
        onPayClick = { viewModel.showFeedback("Paiement Sen'Eau 14.850 F validé !") }
      )

      // Canal+ Bill
      BillCard(
        title = "Canal+ Sénégal",
        subtitle = "Formule Evasion • 240 Chaînes HD",
        amount = "15.000 F",
        dueDate = "Renouvellement 15 Juin",
        icon = Icons.Default.LiveTv,
        iconTint = TextPrimary,
        onPayClick = { viewModel.showFeedback("Réabonnement Canal+ 15.000 F activé !") }
      )
    }

    // 5. Integrated Local Providers Grid
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text("Services & Fournisseurs", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        Text("Partenaires officiels", fontSize = 11.sp, color = TextSecondary)
      }

      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        ProviderTile("Rapido Autoroute", "Autoroute de l'Avenir", Icons.Default.Toll, CyanTertiary, Modifier.weight(1f)) {
          viewModel.showFeedback("Recharge Rapido autoroute")
        }
        ProviderTile("Orange Postpayé", "Forfaits & Lignes Pro", Icons.Default.PhoneAndroid, OrangeMoney, Modifier.weight(1f)) {
          viewModel.showFeedback("Règlement facture Orange")
        }
      }

      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        ProviderTile("Free Sénégal", "Fibre & FreeBox", Icons.Default.Router, ErrorRed, Modifier.weight(1f)) {
          viewModel.showFeedback("Recharge Free Sénégal")
        }
        ProviderTile("CampusEN & Écoles", "Frais d'inscription univ.", Icons.Default.School, EmeraldPrimary, Modifier.weight(1f)) {
          viewModel.showFeedback("Paiement scolarité CampusEN")
        }
      }
    }

    // Security footer
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))
        .background(SurfaceWhite)
        .padding(12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {
      Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(16.dp))
      Spacer(modifier = Modifier.width(6.dp))
      Text("Paiements certifiés BCEAO • Quittance officielle avec QR code délivrée", fontSize = 10.sp, color = TextSecondary)
    }

    Spacer(modifier = Modifier.height(60.dp))
  }
}

@Composable
private fun BillCard(
  title: String,
  subtitle: String,
  amount: String,
  dueDate: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  iconTint: Color,
  onPayClick: () -> Unit
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Column(
      modifier = Modifier.fillMaxWidth().padding(14.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
      ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
          Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(SurfaceContainerLow), contentAlignment = Alignment.Center) {
            Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(22.dp))
          }
          Column {
            Text(title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Text(subtitle, fontSize = 11.sp, color = TextSecondary)
          }
        }

        Column(horizontalAlignment = Alignment.End) {
          Text(amount, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
          Text(dueDate, fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = ErrorRed)
        }
      }

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(SurfaceContainerLow)
          .padding(horizontal = 10.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text("Pénalité de retard évitée avant J-3", fontSize = 11.sp, color = TextSecondary)
        Button(
          onClick = onPayClick,
          shape = RoundedCornerShape(8.dp),
          colors = ButtonDefaults.buttonColors(containerColor = TextPrimary),
          contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 4.dp)
        ) {
          Text("Payer maintenant", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SurfaceWhite)
        }
      }
    }
  }
}

@Composable
private fun ProviderTile(
  title: String,
  subtitle: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  iconTint: Color,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  Card(
    modifier = modifier.clickable { onClick() },
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Column(
      modifier = Modifier.fillMaxWidth().padding(12.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Box(modifier = Modifier.size(34.dp).clip(RoundedCornerShape(8.dp)).background(SurfaceContainerLow), contentAlignment = Alignment.Center) {
          Icon(icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(18.dp))
        }
        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(16.dp))
      }
      Column {
        Text(title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        Text(subtitle, fontSize = 10.sp, color = TextSecondary)
      }
    }
  }
}
