package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MainTab
import com.example.ui.theme.BorderOutline
import com.example.ui.theme.CyanContainer
import com.example.ui.theme.CyanTertiary
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldPrimaryContainer
import com.example.ui.theme.GoldContainer
import com.example.ui.theme.GoldDark
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

/**
 * Top App Bar matching the Flash Pay Transfert header from screenshot and HTML.
 */
@Composable
fun FlashHeader(
  title: String = "Flash Pay Transfert",
  canGoBack: Boolean = false,
  onBackClick: () -> Unit = {},
  onHelpClick: () -> Unit = {},
  onProfileClick: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .shadow(elevation = 2.dp),
    color = SurfaceWhite
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 10.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        if (canGoBack) {
          IconButton(
            onClick = onBackClick,
            modifier = Modifier.size(36.dp).testTag("header_back_btn")
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Retour",
              tint = TextPrimary
            )
          }
        }

        // Logo
        Box(
          modifier = Modifier
            .size(36.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(EmeraldDark),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Bolt,
            contentDescription = "FlashKalpe",
            tint = EmeraldPrimaryContainer,
            modifier = Modifier.size(24.dp)
          )
        }

        Text(
          text = title,
          fontSize = 17.sp,
          fontWeight = FontWeight.Bold,
          color = TextPrimary
        )
      }

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        IconButton(
          onClick = onHelpClick,
          modifier = Modifier.size(36.dp).testTag("header_help_btn")
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.HelpOutline,
            contentDescription = "Aide",
            tint = TextSecondary,
            modifier = Modifier.size(22.dp)
          )
        }

        // Profile Avatar with green status ring
        Box(
          modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .border(2.dp, EmeraldPrimaryContainer.copy(alpha = 0.6f), CircleShape)
            .background(Color(0xFF263238))
            .clickable { onProfileClick() }
            .testTag("header_profile_avatar"),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Profil Natasha Diallo",
            tint = SurfaceWhite,
            modifier = Modifier.size(20.dp)
          )
        }
      }
    }
  }
}

/**
 * Status Chips: FLASHKALPE GOLD VIP & Biométrie Active
 */
@Composable
fun StatusChipsRow(
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    // Left chip: FlashKalpe Gold VIP with pulsing dot
    Row(
      modifier = Modifier
        .clip(RoundedCornerShape(50.dp))
        .background(GoldContainer.copy(alpha = 0.7f))
        .padding(horizontal = 12.dp, vertical = 6.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Box(
        modifier = Modifier
          .size(8.dp)
          .clip(CircleShape)
          .background(EmeraldPrimary)
      )
      Text(
        text = "FLASHKALPE GOLD VIP",
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.8.sp,
        color = TextPrimary
      )
    }

    // Right chip: Biométrie Active
    Row(
      modifier = Modifier
        .clip(RoundedCornerShape(50.dp))
        .background(SurfaceWhite)
        .border(1.dp, BorderOutline, RoundedCornerShape(50.dp))
        .padding(horizontal = 10.dp, vertical = 5.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      Icon(
        imageVector = Icons.Default.VerifiedUser,
        contentDescription = "Vérifié",
        tint = CyanTertiary,
        modifier = Modifier.size(15.dp)
      )
      Text(
        text = "Biométrie Active",
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        color = CyanTertiary
      )
    }
  }
}

/**
 * Floating Pill Bottom Navigation Bar
 */
@Composable
fun BottomNavBar(
  currentTab: MainTab,
  onTabSelected: (MainTab) -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .shadow(elevation = 16.dp, shape = RoundedCornerShape(32.dp), spotColor = Color(0x22000000))
      .testTag("bottom_nav_bar"),
    shape = RoundedCornerShape(32.dp),
    colors = CardDefaults.cardColors(containerColor = SurfaceWhite)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 6.dp),
      horizontalArrangement = Arrangement.SpaceAround,
      verticalAlignment = Alignment.CenterVertically
    ) {
      NavTabItem(
        tab = MainTab.ACCUEIL,
        icon = Icons.Default.AccountBalanceWallet,
        label = "Accueil",
        isSelected = currentTab == MainTab.ACCUEIL,
        onClick = { onTabSelected(MainTab.ACCUEIL) }
      )

      NavTabItem(
        tab = MainTab.CARTE_GOLD,
        icon = Icons.Default.CreditCard,
        label = "Carte Gold",
        isSelected = currentTab == MainTab.CARTE_GOLD,
        onClick = { onTabSelected(MainTab.CARTE_GOLD) }
      )

      NavTabItem(
        tab = MainTab.COFFRES,
        icon = Icons.Default.Savings,
        label = "Tontine",
        isSelected = currentTab == MainTab.COFFRES,
        onClick = { onTabSelected(MainTab.COFFRES) }
      )

      NavTabItem(
        tab = MainTab.FACTURES,
        icon = Icons.Default.ElectricBolt,
        label = "Woyofal",
        isSelected = currentTab == MainTab.FACTURES,
        onClick = { onTabSelected(MainTab.FACTURES) }
      )

      NavTabItem(
        tab = MainTab.HISTORIQUE,
        icon = Icons.Default.BarChart,
        label = "Activité",
        isSelected = currentTab == MainTab.HISTORIQUE,
        onClick = { onTabSelected(MainTab.HISTORIQUE) }
      )

      NavTabItem(
        tab = MainTab.REGLAGES,
        icon = Icons.Default.Tune,
        label = "Réglages",
        isSelected = currentTab == MainTab.REGLAGES,
        onClick = { onTabSelected(MainTab.REGLAGES) }
      )
    }
  }
}

@Composable
private fun NavTabItem(
  tab: MainTab,
  icon: ImageVector,
  label: String,
  isSelected: Boolean,
  onClick: () -> Unit
) {
  val contentColor = if (isSelected) EmeraldPrimary else TextSecondary
  val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium

  Column(
    modifier = Modifier
      .clip(RoundedCornerShape(16.dp))
      .clickable { onClick() }
      .padding(horizontal = 6.dp, vertical = 4.dp)
      .testTag("nav_tab_${tab.name.lowercase()}"),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(2.dp)
  ) {
    Icon(
      imageVector = icon,
      contentDescription = label,
      tint = contentColor,
      modifier = Modifier.size(22.dp)
    )
    Text(
      text = label,
      fontSize = 10.sp,
      fontWeight = fontWeight,
      color = contentColor
    )
  }
}
