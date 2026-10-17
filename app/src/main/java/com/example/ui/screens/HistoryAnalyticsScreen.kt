package com.example.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.automirrored.filled.CallReceived
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Diversity3
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.TransactionItem
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
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.FlashKalpeViewModel

/**
 * History & Analytics Screen matching HTML mockup 7:
 * Financial Overview, Weekly Cashflow Chart, Category breakdown, Filterable transactions.
 */
@Composable
fun HistoryAnalyticsScreen(
  viewModel: FlashKalpeViewModel,
  modifier: Modifier = Modifier
) {
  val transactions by viewModel.transactions.collectAsState()
  var activePeriod by remember { mutableStateOf("Ce mois") }
  var txFilter by remember { mutableStateOf("all") }

  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
      .padding(horizontal = 16.dp, vertical = 12.dp)
      .testTag("history_analytics_screen"),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. Top Section & Temporal Selector
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text("TABLEAU DE BORD", fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp, color = GoldDark)
        Text("Activité & Statistiques", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
      }

      Row(
        modifier = Modifier
          .clip(RoundedCornerShape(50.dp))
          .background(SurfaceContainerLow)
          .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(EmeraldPrimary))
        Text("Temps réel", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
      }
    }

    // Time filter pills
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      listOf("Ce mois", "Mois dernier", "Année 2025").forEach { period ->
        val isActive = activePeriod == period
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(if (isActive) TextPrimary else SurfaceContainerLow)
            .clickable { activePeriod = period }
            .padding(horizontal = 14.dp, vertical = 7.dp)
        ) {
          Text(
            text = period,
            fontSize = 12.sp,
            fontWeight = if (isActive) FontWeight.Bold else FontWeight.Medium,
            color = if (isActive) SurfaceWhite else TextSecondary
          )
        }
      }
    }

    // 2. Financial Overview Bento
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
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("APERÇU MENSUEL NET", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = TextSecondary)
          Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
            Icon(Icons.AutoMirrored.Filled.TrendingUp, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(14.dp))
            Text("+8.4% vs Avril", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
          }
        }

        Column {
          Text("Épargne nette générée", fontSize = 12.sp, color = TextSecondary)
          Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("+125.550", fontSize = 26.sp, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.Monospace, color = TextPrimary)
            Text("FCFA", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextSecondary, modifier = Modifier.padding(bottom = 3.dp))
          }
        }

        // Inflows and Outflows 2-column
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainerLow)
            .padding(12.dp),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
              Box(modifier = Modifier.size(18.dp).clip(CircleShape).background(EmeraldLight), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.ArrowDownward, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(12.dp))
              }
              Text("TOTAL ENTRÉES", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
            }
            Text("+1.450.000", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Text("FCFA (18 dépôts)", fontSize = 10.sp, color = TextSecondary)
          }

          Column(horizontalAlignment = Alignment.End) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
              Box(modifier = Modifier.size(18.dp).clip(CircleShape).background(SurfaceWhite), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.ArrowUpward, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(12.dp))
              }
              Text("TOTAL DÉPENSES", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = TextSecondary)
            }
            Text("-324.450", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Text("FCFA (42 ops)", fontSize = 10.sp, color = TextSecondary)
          }
        }
      }
    }

    // 3. Weekly Cashflow Minimalist Chart
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
          Column {
            Text("Flux hebdomadaires", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Text("Volume des flux en mai 2025", fontSize = 11.sp, color = TextSecondary)
          }

          Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
              Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(EmeraldPrimaryContainer))
              Text("Entrées", fontSize = 11.sp, color = TextSecondary)
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
              Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Color(0xFFCBD5E1)))
              Text("Sorties", fontSize = 11.sp, color = TextSecondary)
            }
          }
        }

        // Custom Canvas Chart
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
        ) {
          Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // 4 weeks bar pairs: (inflow, outflow)
            val weeks = listOf(
              Pair(0.65f, 0.35f),
              Pair(0.75f, 0.45f),
              Pair(0.90f, 0.55f),
              Pair(0.60f, 0.25f)
            )

            val slotWidth = w / 4
            val barWidth = 14.dp.toPx()
            val spacing = 4.dp.toPx()

            weeks.forEachIndexed { i, (inflow, outflow) ->
              val centerX = slotWidth * i + slotWidth / 2
              val outflowX = centerX - barWidth - spacing / 2
              val inflowX = centerX + spacing / 2

              // Outflow bar
              val outHeight = h * outflow
              drawRoundRect(
                color = Color(0xFFCBD5E1),
                topLeft = Offset(outflowX, h - outHeight),
                size = Size(barWidth, outHeight),
                cornerRadius = CornerRadius(6.dp.toPx(), 6.dp.toPx())
              )

              // Inflow bar
              val inHeight = h * inflow
              drawRoundRect(
                color = EmeraldPrimary,
                topLeft = Offset(inflowX, h - inHeight),
                size = Size(barWidth, inHeight),
                cornerRadius = CornerRadius(6.dp.toPx(), 6.dp.toPx())
              )
            }
          }
        }

        // Week labels
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceAround
        ) {
          Text("Sem 1 (01-07)", fontSize = 10.sp, color = TextSecondary)
          Text("Sem 2 (08-14)", fontSize = 10.sp, color = TextSecondary)
          Text("Sem 3 (15-21)", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
          Text("Sem 4 (22-31)", fontSize = 10.sp, color = TextSecondary)
        }
      }
    }

    // 4. Spending Breakdown (Categories)
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
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("Répartition des dépenses", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
          Box(modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(SurfaceContainerLow).padding(horizontal = 8.dp, vertical = 2.dp)) {
            Text("4 Catégories", fontSize = 10.sp, color = TextSecondary, fontWeight = FontWeight.Bold)
          }
        }

        // Multi-segment progress bar
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(SurfaceContainerLow)
        ) {
          Box(modifier = Modifier.weight(0.38f).fillMaxSize().background(TextPrimary))
          Box(modifier = Modifier.weight(0.28f).fillMaxSize().background(EmeraldPrimaryContainer))
          Box(modifier = Modifier.weight(0.22f).fillMaxSize().background(CyanTertiary))
          Box(modifier = Modifier.weight(0.12f).fillMaxSize().background(GoldAmber))
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          CategoryTile("Factures & Maison", "38%", "123.291 F", TextPrimary, Modifier.weight(1f))
          CategoryTile("Alimentation & Resto", "28%", "90.846 F", EmeraldPrimaryContainer, Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          CategoryTile("Tontines & Envois", "22%", "71.379 F", CyanTertiary, Modifier.weight(1f))
          CategoryTile("Loisirs & Shopping", "12%", "38.934 F", GoldAmber, Modifier.weight(1f))
        }
      }
    }

    // 5. Recent Transactions List with Filter Pills
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text("Transactions récentes", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        Text("Mai 2025", fontSize = 12.sp, color = TextSecondary)
      }

      // Filter pills
      Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        val filters = listOf("all" to "Tout", "receive" to "Reçus", "bill" to "Factures", "transfer" to "Envois")
        filters.forEach { (key, label) ->
          val isFilterActive = txFilter == key
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(50.dp))
              .background(if (isFilterActive) TextPrimary else SurfaceWhite)
              .border(1.dp, if (isFilterActive) TextPrimary else BorderOutline, RoundedCornerShape(50.dp))
              .clickable { txFilter = key }
              .padding(horizontal = 12.dp, vertical = 6.dp)
          ) {
            Text(
              text = label,
              fontSize = 11.sp,
              fontWeight = if (isFilterActive) FontWeight.Bold else FontWeight.Medium,
              color = if (isFilterActive) SurfaceWhite else TextSecondary
            )
          }
        }
      }

      // Filtered list
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(modifier = Modifier.fillMaxWidth()) {
          val filteredList = transactions.filter { txFilter == "all" || it.category == txFilter }

          filteredList.forEachIndexed { idx, tx ->
            TransactionRow(item = tx)
            if (idx < filteredList.size - 1) {
              Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).height(0.5.dp).background(BorderOutline))
            }
          }
        }
      }
    }

    // 6. BCEAO Statement Export Card
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
          Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(EmeraldLight), contentAlignment = Alignment.Center) {
              Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(18.dp))
            }
            Column {
              Text("Relevé de Compte Mensuel", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
              Text("Certifié conforme BCEAO / UEMOA", fontSize = 10.sp, color = TextSecondary)
            }
          }

          Box(modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(SurfaceContainerLow).padding(horizontal = 6.dp, vertical = 2.dp)) {
            Text("PDF • 342 KB", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
          }
        }

        Text(
          text = "Générez votre relevé fiscal et comptable complet signé numériquement avec clé horodatée.",
          fontSize = 11.sp,
          color = TextSecondary,
          lineHeight = 15.sp
        )

        Button(
          onClick = { viewModel.showFeedback("Génération du relevé officiel BCEAO (PDF)... Téléchargement réussi !") },
          modifier = Modifier.fillMaxWidth().height(46.dp),
          colors = ButtonDefaults.buttonColors(containerColor = TextPrimary),
          shape = RoundedCornerShape(12.dp)
        ) {
          Icon(Icons.Default.FileDownload, contentDescription = null, tint = EmeraldPrimaryContainer, modifier = Modifier.size(18.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text("Télécharger le relevé certifié (PDF)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SurfaceWhite)
        }
      }
    }

    Spacer(modifier = Modifier.height(60.dp))
  }
}

@Composable
private fun CategoryTile(
  title: String,
  percent: String,
  amount: String,
  color: Color,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .clip(RoundedCornerShape(10.dp))
      .background(SurfaceContainerLow)
      .padding(10.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(color))
    Column {
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(title, fontSize = 10.sp, fontWeight = FontWeight.Medium, color = TextSecondary, maxLines = 1)
        Text(percent, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
      }
      Text(amount, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
    }
  }
}

@Composable
private fun TransactionRow(item: TransactionItem) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(14.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
      val icon = when (item.category) {
        "receive" -> Icons.AutoMirrored.Filled.CallReceived
        "bill" -> if (item.title.contains("Woyofal")) Icons.Default.Bolt else Icons.Default.Restaurant
        else -> Icons.Default.Diversity3
      }
      val iconBg = if (item.isCredit) EmeraldLight else SurfaceContainerLow

      Box(
        modifier = Modifier.size(38.dp).clip(CircleShape).background(iconBg),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = if (item.isCredit) EmeraldPrimary else TextPrimary,
          modifier = Modifier.size(18.dp)
        )
      }

      Column {
        Text(item.title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        Text("${item.subtitle} • ${item.date}", fontSize = 10.sp, color = TextSecondary)
      }
    }

    Column(horizontalAlignment = Alignment.End) {
      Text(
        text = "${if (item.isCredit) "+" else "-"}${FlashKalpeViewModel.formatAmount(item.amount)} F",
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace,
        color = if (item.isCredit) EmeraldPrimary else TextPrimary
      )
      Text(item.status, fontSize = 9.sp, fontWeight = FontWeight.SemiBold, color = if (item.isCredit) EmeraldPrimary else TextSecondary)
    }
  }
}
