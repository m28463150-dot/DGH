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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.EventRepeat
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Stars
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.TontineMember
import com.example.ui.theme.BorderOutline
import com.example.ui.theme.CyanContainer
import com.example.ui.theme.CyanTertiary
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldPrimaryContainer
import com.example.ui.theme.ErrorContainer
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.GoldContainer
import com.example.ui.theme.GoldDark
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.FlashKalpeViewModel

/**
 * Tontine Flash Cercle Screen matching HTML mockup 3:
 * Tontine Entrepreneurs Dakar 2025, 1.000.000 FCFA pot, BCEAO escrow.
 */
@Composable
fun TontineScreen(
  viewModel: FlashKalpeViewModel,
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
      .padding(horizontal = 16.dp, vertical = 12.dp)
      .testTag("tontine_screen"),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. Group Header & Badges
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(EmeraldLight)
            .padding(horizontal = 10.dp, vertical = 4.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(EmeraldPrimary))
          Text(
            text = "TOUR 4 SUR 10 • EN COURS",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = EmeraldPrimary
          )
        }

        Row(
          modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(GoldContainer)
            .padding(horizontal = 10.dp, vertical = 4.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = CyanTertiary, modifier = Modifier.size(13.dp))
          Text("VÉRIFIÉ BCEAO", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = GoldDark)
        }
      }

      Column {
        Text(
          text = "Tontine Entrepreneurs Dakar 2025",
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = TextPrimary
        )
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(4.dp),
          modifier = Modifier.padding(top = 2.dp)
        ) {
          Icon(Icons.Default.Groups, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(16.dp))
          Text(
            text = "10 entrepreneurs • Cycle mensuel fixe (100.000 FCFA/mois)",
            fontSize = 12.sp,
            color = TextSecondary
          )
        }
      }
    }

    // 2. Monthly Pot Hero Card
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(18.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.fillMaxWidth().padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "CAGNOTTE MENSUELLE TOTALE",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 0.8.sp,
              color = TextSecondary
            )
            Row(
              verticalAlignment = Alignment.Bottom,
              horizontalArrangement = Arrangement.spacedBy(4.dp),
              modifier = Modifier.padding(top = 2.dp)
            ) {
              Text(
                text = "1.000.000",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Monospace,
                color = TextPrimary
              )
              Text(
                text = "FCFA",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = GoldDark,
                modifier = Modifier.padding(bottom = 3.dp)
              )
            }
          }

          Box(
            modifier = Modifier.size(46.dp).clip(CircleShape).background(EmeraldLight),
            contentAlignment = Alignment.Center
          ) {
            Icon(Icons.Default.Savings, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(24.dp))
          }
        }

        // Beneficiary & Your Turn Row
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          // Beneficiary Mai
          Column(
            modifier = Modifier
              .weight(1f)
              .clip(RoundedCornerShape(12.dp))
              .background(SurfaceContainerLow)
              .padding(10.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
              Icon(Icons.Default.Stars, contentDescription = null, tint = CyanTertiary, modifier = Modifier.size(13.dp))
              Text("BÉNÉFICIAIRE MAI", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = TextSecondary)
            }
            Text("Amadou Sall", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary, modifier = Modifier.padding(top = 2.dp))
            Text("1er Juin • 1.000.000 F", fontSize = 11.sp, fontWeight = FontWeight.Medium, color = EmeraldPrimary)
          }

          // Your Turn
          Column(
            modifier = Modifier
              .weight(1f)
              .clip(RoundedCornerShape(12.dp))
              .background(EmeraldLight)
              .border(1.dp, EmeraldPrimary.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
              .padding(10.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
              Icon(Icons.Default.EventRepeat, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(13.dp))
              Text("VOTRE TOUR", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
            }
            Text("Tour 6 (Juillet)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary, modifier = Modifier.padding(top = 2.dp))
            Text("1.000.000 FCFA", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
          }
        }
      }
    }

    // 3. Collection Progress (Mai)
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
          Column {
            Text(
              text = "COLLECTE DU MOIS (MAI)",
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              color = TextSecondary
            )
            Row(verticalAlignment = Alignment.Bottom) {
              Text(
                text = "800.000",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
              )
              Text(
                text = " / 1.000.000 FCFA",
                fontSize = 13.sp,
                color = TextSecondary
              )
            }
          }

          Row(
            modifier = Modifier
              .clip(RoundedCornerShape(50.dp))
              .background(SurfaceContainerLow)
              .padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(14.dp))
            Text("8 sur 10 cotisés", fontSize = 11.sp, color = TextPrimary, fontWeight = FontWeight.SemiBold)
          }
        }

        LinearProgressIndicator(
          progress = { 0.8f },
          modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
          color = EmeraldPrimary,
          trackColor = SurfaceContainerLow
        )

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text("80% complété", fontSize = 11.sp, color = TextSecondary)
          Text("Reste 200.000 FCFA (2 membres)", fontSize = 11.sp, color = CyanTertiary, fontWeight = FontWeight.SemiBold)
        }

        // Personal Status Tile
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceContainerLow)
            .padding(12.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Box(
              modifier = Modifier.size(34.dp).clip(CircleShape).background(EmeraldLight),
              contentAlignment = Alignment.Center
            ) {
              Icon(Icons.Default.Verified, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(18.dp))
            }
            Column {
              Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Votre part pour Mai", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Box(
                  modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(EmeraldLight).padding(horizontal = 4.dp, vertical = 1.dp)
                ) {
                  Text("PAYÉE", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
                }
              }
              Text("05 Mai • 100.000 FCFA via Wave", fontSize = 11.sp, color = TextSecondary)
            }
          }

          Button(
            onClick = { viewModel.showFeedback("Téléchargement du reçu horodaté Flash Cercle...") },
            colors = ButtonDefaults.buttonColors(containerColor = SurfaceWhite),
            border = androidx.compose.foundation.BorderStroke(1.dp, BorderOutline),
            shape = RoundedCornerShape(8.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 6.dp)
          ) {
            Icon(Icons.Default.Download, contentDescription = null, tint = TextPrimary, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("Reçu", fontSize = 11.sp, color = TextPrimary, fontWeight = FontWeight.SemiBold)
          }
        }
      }
    }

    // 4. Rotation Schedule (Membres)
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
          Column {
            Text("Calendrier des Rotations", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Text("Ordre tiré au sort certifié BCEAO", fontSize = 11.sp, color = TextSecondary)
          }
          Box(
            modifier = Modifier.clip(RoundedCornerShape(4.dp)).background(SurfaceContainerLow).padding(horizontal = 8.dp, vertical = 2.dp)
          ) {
            Text("10 Tours", fontSize = 10.sp, color = TextSecondary, fontWeight = FontWeight.Bold)
          }
        }

        viewModel.tontineMembers.forEach { member ->
          TontineMemberRow(member = member)
        }
      }
    }

    // 5. Escrow Guarantee Info Card
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth().padding(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
      ) {
        Box(
          modifier = Modifier.size(38.dp).clip(RoundedCornerShape(10.dp)).background(EmeraldLight),
          contentAlignment = Alignment.Center
        ) {
          Icon(Icons.Default.Gavel, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(20.dp))
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text("Garantie Séquestre FlashKalpe", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
          Text(
            text = "Les fonds sont cantonnés sur un compte de fiducie certifié BCEAO. En cas de défaillance, le fonds de réserve solidaire couvre 100% du versement.",
            fontSize = 11.sp,
            color = TextSecondary,
            lineHeight = 16.sp
          )
          Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(top = 4.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
              Icon(Icons.Default.Lock, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(12.dp))
              Text("Zéro Défaut Garanti", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
              Icon(Icons.Default.Verified, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(12.dp))
              Text("Agrément FinTech #SN-2024", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
            }
          }
        }
      }
    }

    // 6. Action Buttons
    Button(
      onClick = { viewModel.showFeedback("Lien d'invitation unique copié dans le presse-papier !") },
      modifier = Modifier.fillMaxWidth().height(48.dp),
      colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimaryContainer),
      shape = RoundedCornerShape(12.dp)
    ) {
      Icon(Icons.Default.PersonAdd, contentDescription = null, tint = EmeraldDark)
      Spacer(modifier = Modifier.width(8.dp))
      Text("Inviter un membre au cercle", color = EmeraldDark, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }

    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      Button(
        onClick = { viewModel.showFeedback("Affichage des archives et quittances du cercle...") },
        modifier = Modifier.weight(1f).height(44.dp),
        colors = ButtonDefaults.buttonColors(containerColor = SurfaceWhite),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderOutline),
        shape = RoundedCornerShape(10.dp)
      ) {
        Icon(Icons.Default.History, contentDescription = null, tint = GoldDark, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text("Historique", color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
      }

      Button(
        onClick = { viewModel.showFeedback("Règlement intérieur : Charte BCEAO 2025") },
        modifier = Modifier.weight(1f).height(44.dp),
        colors = ButtonDefaults.buttonColors(containerColor = SurfaceWhite),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderOutline),
        shape = RoundedCornerShape(10.dp)
      ) {
        Icon(Icons.Default.Description, contentDescription = null, tint = GoldDark, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text("Règlement", color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
      }
    }

    Spacer(modifier = Modifier.height(60.dp))
  }
}

@Composable
private fun TontineMemberRow(member: TontineMember) {
  val isCurrent = member.status == "current"
  val isYou = member.isYou

  val rowBackground = when {
    isYou -> EmeraldLight.copy(alpha = 0.5f)
    isCurrent -> GoldContainer.copy(alpha = 0.4f)
    else -> Color.Transparent
  }

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(10.dp))
      .background(rowBackground)
      .padding(horizontal = 8.dp, vertical = 8.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      Text(
        text = member.turn.toString(),
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = if (isCurrent) CyanTertiary else if (isYou) EmeraldPrimary else TextSecondary,
        modifier = Modifier.width(16.dp)
      )

      Box(
        modifier = Modifier.size(36.dp).clip(CircleShape).background(Color(0xFF263238)),
        contentAlignment = Alignment.Center
      ) {
        Icon(Icons.Default.Person, contentDescription = null, tint = SurfaceWhite, modifier = Modifier.size(20.dp))
      }

      Column {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
          Text(
            text = member.name,
            fontSize = 13.sp,
            fontWeight = if (isCurrent || isYou) FontWeight.Bold else FontWeight.SemiBold,
            color = if (isYou) EmeraldPrimary else TextPrimary
          )
          if (isYou) {
            Box(modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(EmeraldPrimary).padding(horizontal = 6.dp, vertical = 1.dp)) {
              Text("VOUS", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = SurfaceWhite)
            }
          }
        }
        Text(
          text = when (member.status) {
            "done" -> "A reçu 1.000.000 FCFA"
            "current" -> "Versement prévu 1er Juin"
            "you" -> "Cagnotte en Juillet (Tour 6)"
            else -> "Tour en attente (${member.month})"
          },
          fontSize = 11.sp,
          color = if (isCurrent) CyanTertiary else TextSecondary
        )
      }
    }

    when (member.status) {
      "done" -> {
        Row(
          modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(EmeraldLight).padding(horizontal = 8.dp, vertical = 3.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
          Icon(Icons.Default.DoneAll, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(13.dp))
          Text("Validé", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
        }
      }
      "current" -> {
        Box(
          modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(GoldContainer).padding(horizontal = 8.dp, vertical = 3.dp)
        ) {
          Text("En cours", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = GoldDark)
        }
      }
      "you" -> {
        Box(
          modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(EmeraldLight).padding(horizontal = 8.dp, vertical = 3.dp)
        ) {
          Text("À jour ✓", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
        }
      }
      else -> {
        Text("Suivant", fontSize = 11.sp, color = TextSecondary)
      }
    }
  }
}
