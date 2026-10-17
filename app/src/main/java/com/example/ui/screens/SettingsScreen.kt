package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
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
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.SubScreen
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
import com.example.ui.theme.ObsidianDark
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.viewmodel.FlashKalpeViewModel

/**
 * Settings & Security Screen matching HTML mockup 8:
 * KYC Tier 3, Virtual UEMOA IBAN, Biometrics, VIP Concierge WhatsApp link.
 */
@Composable
fun SettingsScreen(
  viewModel: FlashKalpeViewModel,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var biometricEnabled by remember { mutableStateOf(true) }

  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
      .padding(horizontal = 16.dp, vertical = 12.dp)
      .testTag("settings_screen"),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. Profile & VIP Status Card
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
          horizontalArrangement = Arrangement.spacedBy(12.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier.size(54.dp).clip(CircleShape).background(Color(0xFF263238)),
            contentAlignment = Alignment.Center
          ) {
            Icon(Icons.Default.Person, contentDescription = null, tint = SurfaceWhite, modifier = Modifier.size(32.dp))
            Box(
              modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(16.dp)
                .clip(CircleShape)
                .background(EmeraldPrimaryContainer)
                .border(2.dp, SurfaceWhite, CircleShape),
              contentAlignment = Alignment.Center
            ) {
              Icon(Icons.Default.Verified, contentDescription = null, tint = EmeraldDark, modifier = Modifier.size(10.dp))
            }
          }

          Column {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
              Text("Natasha Diallo", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
              Box(modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(GoldContainer).padding(horizontal = 6.dp, vertical = 2.dp)) {
                Text("VIP OBSIDIAN", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = GoldDark)
              }
            }
            Text("+221 77 645 89 24", fontSize = 12.sp, color = TextSecondary)
          }
        }

        // KYC Tier 3 Badge
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(SurfaceContainerLow)
            .padding(10.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(18.dp))
            Column {
              Text("Tier KYC BCEAO • Niveau 3", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
              Text("Identité vérifiée & plafonds étendus", fontSize = 10.sp, color = TextSecondary)
            }
          }
          Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(16.dp))
        }

        // Virtual IBAN UEMOA Box
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(SurfaceContainerLow)
            .padding(12.dp),
          verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text("IBAN Virtuel UEMOA (Sénégal)", fontSize = 11.sp, color = TextSecondary)
            Row(
              modifier = Modifier
                .clickable {
                  val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                  val clip = ClipData.newPlainText("IBAN", "SN08 SN01 2013 4900 8821 00")
                  clipboard.setPrimaryClip(clip)
                  viewModel.showFeedback("IBAN copié dans le presse-papier !")
                }
                .padding(2.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Icon(Icons.Default.ContentCopy, contentDescription = "Copier", tint = EmeraldPrimary, modifier = Modifier.size(13.dp))
              Text("Copier", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
            }
          }
          Text(
            text = "SN08 SN01 2013 4900 8821 00",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            color = TextPrimary
          )
        }
      }
    }

    // 2. Sécurité & Authentification
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text("Sécurité & Accès", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        Box(modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(EmeraldLight).padding(horizontal = 8.dp, vertical = 2.dp)) {
          Text("Niveau Élevé", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
        }
      }

      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
          // PIN
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { viewModel.openSubScreen(SubScreen.PinKeypad) }
              .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
              Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(SurfaceContainerLow), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Pin, contentDescription = null, tint = TextPrimary, modifier = Modifier.size(18.dp))
              }
              Column {
                Text("Code secret à 4 chiffres", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                Text("Sécurisé • Modifié il y a 2 semaines", fontSize = 11.sp, color = EmeraldPrimary)
              }
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(18.dp))
          }

          // Biometrics
          Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
              Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(SurfaceContainerLow), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Face, contentDescription = null, tint = TextPrimary, modifier = Modifier.size(18.dp))
              }
              Column {
                Text("Face ID & Empreinte", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                Text("Connexion instantanée sécurisée", fontSize = 11.sp, color = TextSecondary)
              }
            }
            Switch(
              checked = biometricEnabled,
              onCheckedChange = { biometricEnabled = it },
              colors = SwitchDefaults.colors(
                checkedThumbColor = SurfaceWhite,
                checkedTrackColor = EmeraldPrimary
              )
            )
          }

          // 2FA
          Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
              Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(SurfaceContainerLow), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Security, contentDescription = null, tint = TextPrimary, modifier = Modifier.size(18.dp))
              }
              Column {
                Text("Double facteur (2FA)", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                Text("Vérification SMS & WhatsApp", fontSize = 11.sp, color = TextSecondary)
              }
            }
            Box(modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(EmeraldLight).padding(horizontal = 8.dp, vertical = 3.dp)) {
              Text("Actif", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
            }
          }

          // Connected Devices
          Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
              Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(SurfaceContainerLow), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Devices, contentDescription = null, tint = TextPrimary, modifier = Modifier.size(18.dp))
              }
              Column {
                Text("Appareils connectés", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                Text("Google Pixel Dakar • En ligne", fontSize = 11.sp, color = TextSecondary)
              }
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(18.dp))
          }
        }
      }
    }

    // 3. Plafonds & Limites
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
          Column {
            Text("Plafond quotidien utilisé", fontSize = 11.sp, color = TextSecondary)
            Text("450.000 / 2.000.000 FCFA", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
          }
          Text("22.5%", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
        }

        LinearProgressIndicator(
          progress = { 0.225f },
          modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
          color = EmeraldPrimary,
          trackColor = SurfaceContainerLow
        )

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("Besoin d'un transfert exceptionnel ?", fontSize = 11.sp, color = TextSecondary)
          Button(
            onClick = { viewModel.showFeedback("Demande d'augmentation de plafond transmise au conseiller VIP.") },
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = TextPrimary),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 6.dp)
          ) {
            Icon(Icons.Default.Speed, contentDescription = null, tint = EmeraldPrimaryContainer, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("Augmenter", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SurfaceWhite)
          }
        }
      }
    }

    // 4. Conciergerie VIP 24/7 (WhatsApp)
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = ObsidianDark)
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
            Box(
              modifier = Modifier.size(34.dp).clip(CircleShape).background(EmeraldPrimaryContainer),
              contentAlignment = Alignment.Center
            ) {
              Icon(Icons.Default.SupportAgent, contentDescription = null, tint = EmeraldDark, modifier = Modifier.size(18.dp))
            }
            Column {
              Text("Conciergerie VIP 24/7", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SurfaceWhite)
              Text("Conseiller privé dédié FlashKalpe", fontSize = 10.sp, color = SurfaceWhite.copy(alpha = 0.7f))
            }
          }

          Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(EmeraldPrimaryContainer))
        }

        Text(
          text = "Assistance prioritaire instantanée pour vos transferts internationaux et opérations de change UEMOA.",
          fontSize = 11.sp,
          color = SurfaceWhite.copy(alpha = 0.8f),
          lineHeight = 15.sp
        )

        Button(
          onClick = { viewModel.showFeedback("Ouverture du canal WhatsApp Conciergerie VIP (+221 77 000 00 00)") },
          modifier = Modifier.fillMaxWidth().height(44.dp),
          colors = ButtonDefaults.buttonColors(containerColor = EmeraldPrimaryContainer),
          shape = RoundedCornerShape(10.dp)
        ) {
          Icon(Icons.AutoMirrored.Filled.Chat, contentDescription = null, tint = EmeraldDark, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text("Ouvrir le chat WhatsApp VIP", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = EmeraldDark)
        }
      }
    }

    // 5. Legal & Logout
    Column(
      modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Icon(Icons.Default.AccountBalance, contentDescription = null, tint = EmeraldPrimary, modifier = Modifier.size(14.dp))
        Text("Établissement agréé BCEAO sous le N° SN-044/2023", fontSize = 10.sp, color = TextSecondary)
      }

      Button(
        onClick = { viewModel.showFeedback("Session sécurisée fermée.") },
        modifier = Modifier.fillMaxWidth().height(44.dp),
        colors = ButtonDefaults.buttonColors(containerColor = ErrorContainer),
        shape = RoundedCornerShape(10.dp)
      ) {
        Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null, tint = ErrorRed, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text("Déconnexion du compte", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ErrorRed)
      }

      Text(
        text = "FlashKalpe v3.4.2 (2025) • Chiffrement Quantique AES-256",
        fontSize = 10.sp,
        color = TextSecondary.copy(alpha = 0.6f)
      )
    }

    Spacer(modifier = Modifier.height(60.dp))
  }
}
