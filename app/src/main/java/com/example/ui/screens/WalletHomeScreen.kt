package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.TrendingDown
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Contactless
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.NorthEast
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.SouthWest
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.MainTab
import com.example.model.SubScreen
import com.example.ui.theme.BorderOutline
import com.example.ui.theme.CyanTertiary
import com.example.ui.theme.EmeraldDark
import com.example.ui.theme.EmeraldLight
import com.example.ui.theme.EmeraldPrimary
import com.example.ui.theme.EmeraldPrimaryContainer
import com.example.ui.theme.GoldAmber
import com.example.ui.theme.GoldContainer
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.OrangeMoney
import com.example.ui.theme.SurfaceContainerLow
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.WaveBlue
import com.example.viewmodel.FlashKalpeViewModel

/**
 * Wallet Home Screen with Multi-Card Deck, Quick Bento Actions,
 * Linked accounts (Wave, Orange Money), Spending analytics and Limits.
 */
@Composable
fun WalletHomeScreen(
  viewModel: FlashKalpeViewModel,
  modifier: Modifier = Modifier
) {
  val balance by viewModel.balance.collectAsState()
  val isFrozen by viewModel.isCardFrozen.collectAsState()
  val activeCardIndex by viewModel.activeCardIndex.collectAsState()
  val cardLimit by viewModel.monthlyCardLimit.collectAsState()
  val cardSpent by viewModel.monthlyCardSpent.collectAsState()

  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
      .padding(horizontal = 16.dp, vertical = 12.dp)
      .testTag("wallet_home_screen"),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. Top Greeting & Live Pulse Indicator
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
          modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .border(1.5.dp, BorderOutline, CircleShape)
            .background(Color(0xFF263238)),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Natasha Diallo",
            tint = SurfaceWhite,
            modifier = Modifier.size(24.dp)
          )
          Box(
            modifier = Modifier
              .align(Alignment.BottomEnd)
              .size(12.dp)
              .clip(CircleShape)
              .background(EmeraldPrimary)
              .border(2.dp, SurfaceWhite, CircleShape)
          )
        }

        Column {
          Text(text = "Bonjour,", fontSize = 12.sp, color = TextSecondary)
          Text(
            text = "Natasha Diallo",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
        }
      }

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Row(
          modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(SurfaceWhite)
            .border(1.dp, BorderOutline, RoundedCornerShape(50.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Box(
            modifier = Modifier
              .size(6.dp)
              .clip(CircleShape)
              .background(EmeraldPrimary)
          )
          Text(
            text = "PREMIUM VIP",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp,
            color = TextPrimary
          )
        }

        IconButton(
          onClick = { viewModel.openSubScreen(SubScreen.QrScanner) },
          modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
            .background(SurfaceWhite)
            .border(1.dp, BorderOutline, CircleShape)
            .testTag("qr_scanner_header_btn")
        ) {
          Icon(
            imageVector = Icons.Default.QrCodeScanner,
            contentDescription = "Scanner QR",
            tint = TextPrimary,
            modifier = Modifier.size(20.dp)
          )
        }
      }
    }

    // 2. Multi-Card Deck Section
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Text(
            text = "Mes Cartes & Soldes",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(50.dp))
              .background(SurfaceContainerLow)
              .padding(horizontal = 8.dp, vertical = 2.dp)
          ) {
            Text(
              text = "3 Actives",
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              color = TextPrimary
            )
          }
        }

        // Card dots
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
          repeat(viewModel.cards.size) { idx ->
            Box(
              modifier = Modifier
                .width(if (idx == activeCardIndex) 16.dp else 6.dp)
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(if (idx == activeCardIndex) GoldDark else BorderOutline)
            )
          }
        }
      }

      // Horizontal Card Carousel
      LazyRow(
        contentPadding = PaddingValues(end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        itemsIndexed(viewModel.cards) { index, card ->
          val isSelected = index == activeCardIndex

          val cardGradient = when (index) {
            0 -> Brush.linearGradient(listOf(Color(0xFFFABC4D), Color(0xFFBD8718), Color(0xFF432C00)))
            1 -> Brush.linearGradient(listOf(Color(0xFF00D664), Color(0xFF005322), Color(0xFF00210A)))
            else -> Brush.linearGradient(listOf(Color(0xFF50D7EE), Color(0xFF004E59), Color(0xFF0B1C30)))
          }

          Card(
            modifier = Modifier
              .width(310.dp)
              .aspectRatio(1.58f)
              .clickable { viewModel.setActiveCardIndex(index) }
              .shadow(elevation = if (isSelected) 8.dp else 2.dp, shape = RoundedCornerShape(18.dp)),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
          ) {
            Box(
              modifier = Modifier
                .fillMaxSize()
                .background(cardGradient)
                .padding(18.dp)
            ) {
              Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
              ) {
                // Top row
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                  ) {
                    Box(
                      modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color.White.copy(alpha = 0.2f))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                      Text(
                        text = card.typeBadge,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = SurfaceWhite
                      )
                    }
                  }
                  Icon(
                    imageVector = Icons.Default.Contactless,
                    contentDescription = "Contactless",
                    tint = SurfaceWhite.copy(alpha = 0.85f),
                    modifier = Modifier.size(18.dp)
                  )
                }

                // Balance
                Column {
                  Text(
                    text = if (index == 0) "Solde Disponible" else card.subtitle,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = SurfaceWhite.copy(alpha = 0.8f)
                  )
                  Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                  ) {
                    Text(
                      text = FlashKalpeViewModel.formatAmount(if (index == 0) balance else card.balance),
                      fontSize = 26.sp,
                      fontWeight = FontWeight.ExtraBold,
                      color = SurfaceWhite,
                      fontFamily = FontFamily.Monospace
                    )
                    Text(
                      text = "FCFA",
                      fontSize = 14.sp,
                      fontWeight = FontWeight.Bold,
                      color = GoldAmber,
                      modifier = Modifier.padding(bottom = 3.dp)
                    )
                  }
                }

                // Bottom row
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.Bottom
                ) {
                  Column {
                    Text(
                      text = card.holderName,
                      fontSize = 12.sp,
                      fontWeight = FontWeight.Bold,
                      color = SurfaceWhite
                    )
                    Text(
                      text = "${card.cardNumberMasked} • ${card.expiryDate}",
                      fontSize = 10.sp,
                      color = SurfaceWhite.copy(alpha = 0.7f)
                    )
                  }

                  if (index == 0) {
                    // Mastercard dual spheres
                    Row(verticalAlignment = Alignment.CenterVertically) {
                      Box(modifier = Modifier.size(20.dp).clip(CircleShape).background(Color(0xFFEB001B)))
                      Box(modifier = Modifier.offset(x = (-8).dp).size(20.dp).clip(CircleShape).background(Color(0xFFFABC4D)))
                    }
                  } else {
                    Box(
                      modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.Black.copy(alpha = 0.3f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                      Text(
                        text = if (index == 1) "VISA" else "TONTINE",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = SurfaceWhite
                      )
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    // 3. Quick Action Buttons (Verrouiller, Ma carte QR, PIN)
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      // Action 1: Geler
      Card(
        modifier = Modifier
          .weight(1f)
          .clickable { viewModel.toggleFreezeCard() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(
          modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 6.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Box(
            modifier = Modifier.size(38.dp).clip(CircleShape).background(SurfaceContainerLow),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = if (isFrozen) Icons.Default.Lock else Icons.Default.LockOpen,
              contentDescription = "Geler",
              tint = if (isFrozen) com.example.ui.theme.ErrorRed else TextPrimary,
              modifier = Modifier.size(18.dp)
            )
          }
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
              text = if (isFrozen) "Débloquer" else "Verrouiller",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = TextPrimary
            )
            Text(
              text = if (isFrozen) "Gelée" else "Carte active",
              fontSize = 9.sp,
              color = TextSecondary
            )
          }
        }
      }

      // Action 2: Ma carte QR
      Card(
        modifier = Modifier
          .weight(1f)
          .clickable { viewModel.openSubScreen(SubScreen.QrScanner) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(
          modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 6.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Box(
            modifier = Modifier.size(38.dp).clip(CircleShape).background(GoldContainer),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.QrCode2,
              contentDescription = "Ma carte QR",
              tint = GoldDark,
              modifier = Modifier.size(18.dp)
            )
          }
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
              text = "Ma carte QR",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = TextPrimary
            )
            Text(
              text = "Paiement direct",
              fontSize = 9.sp,
              color = TextSecondary
            )
          }
        }
      }

      // Action 3: Code PIN
      Card(
        modifier = Modifier
          .weight(1f)
          .clickable { viewModel.openSubScreen(SubScreen.PinKeypad) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(
          modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 6.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Box(
            modifier = Modifier.size(38.dp).clip(CircleShape).background(SurfaceContainerLow),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Visibility,
              contentDescription = "Code PIN",
              tint = TextPrimary,
              modifier = Modifier.size(18.dp)
            )
          }
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
              text = "PIN & CVV",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = TextPrimary
            )
            Text(
              text = "Afficher",
              fontSize = 9.sp,
              fontWeight = FontWeight.SemiBold,
              color = EmeraldPrimary
            )
          }
        }
      }
    }

    // 4. Bento Section: High Priority "Envoyer" + Virer + Demander
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      // Left Hero Action: Emerald "Envoyer"
      Card(
        modifier = Modifier
          .weight(1f)
          .clickable { viewModel.openSubScreen(SubScreen.Recharge) }
          .testTag("bento_envoyer_btn"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = EmeraldPrimary)
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          verticalArrangement = Arrangement.SpaceBetween
        ) {
          Box(
            modifier = Modifier
              .size(38.dp)
              .clip(CircleShape)
              .background(SurfaceWhite.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.NorthEast,
              contentDescription = "Envoyer",
              tint = SurfaceWhite,
              modifier = Modifier.size(20.dp)
            )
          }

          Spacer(modifier = Modifier.height(28.dp))

          Column {
            Text(
              text = "Envoyer",
              fontSize = 18.sp,
              fontWeight = FontWeight.ExtraBold,
              color = SurfaceWhite
            )
            Text(
              text = "de l'argent sans frais",
              fontSize = 12.sp,
              color = SurfaceWhite.copy(alpha = 0.85f)
            )
          }
        }
      }

      // Right Column: Virer fonds & Demander
      Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        // Virer fonds
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { viewModel.openSubScreen(SubScreen.Recharge) },
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
          Column(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "PASSERELLE FLASH",
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = TextSecondary
              )
              Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                  modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(OrangeMoney),
                  contentAlignment = Alignment.Center
                ) {
                  Text("O", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = SurfaceWhite)
                }
                Box(
                  modifier = Modifier
                    .offset(x = (-4).dp)
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(WaveBlue),
                  contentAlignment = Alignment.Center
                ) {
                  Text("W", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = SurfaceWhite)
                }
              }
            }

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "Virer fonds",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
              )
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Virer",
                tint = EmeraldPrimary,
                modifier = Modifier.size(16.dp)
              )
            }
          }
        }

        // Demander de l'argent
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { viewModel.showFeedback("Lien de paiement FlashKalpe généré et copié !") },
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
          Column(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "DEMANDE RAPIDE",
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = TextSecondary
              )
              Icon(
                imageVector = Icons.Default.SouthWest,
                contentDescription = "Demander",
                tint = GoldDark,
                modifier = Modifier.size(16.dp)
              )
            }

            Column {
              Text(
                text = "Demander",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
              )
              Text(
                text = "Lien ou QR Code",
                fontSize = 10.sp,
                color = TextSecondary
              )
            }
          }
        }
      }
    }

    // 5. Card Limits & Security Slider
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(16.dp),
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
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Box(
              modifier = Modifier.size(32.dp).clip(CircleShape).background(EmeraldLight),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Tune,
                contentDescription = "Plafond",
                tint = EmeraldPrimary,
                modifier = Modifier.size(18.dp)
              )
            }
            Column {
              Text(
                text = "Plafond Mensuel",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
              )
              Text(
                text = "Ajustez selon vos besoins",
                fontSize = 11.sp,
                color = TextSecondary
              )
            }
          }

          Text(
            text = "${FlashKalpeViewModel.formatAmount(cardLimit)} FCFA",
            fontSize = 13.sp,
            fontWeight = FontWeight.ExtraBold,
            color = EmeraldPrimary
          )
        }

        LinearProgressIndicator(
          progress = { (cardSpent.toFloat() / cardLimit).coerceIn(0f, 1f) },
          modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(4.dp)),
          color = EmeraldPrimary,
          trackColor = SurfaceContainerLow
        )

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = "Consommé : ${FlashKalpeViewModel.formatAmount(cardSpent)} F",
            fontSize = 11.sp,
            color = TextSecondary
          )
          Text(
            text = "Reste : ${FlashKalpeViewModel.formatAmount(cardLimit - cardSpent)} F",
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color = GoldDark
          )
        }
      }
    }

    // 6. Linked Gateways Row (Wave, OM, UBA)
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(16.dp),
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
              text = "Comptes & Passerelles Liés",
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold,
              color = TextPrimary
            )
            Text(
              text = "Rechargez sans délai via vos portefeuilles locaux",
              fontSize = 11.sp,
              color = TextSecondary
            )
          }
          Text(
            text = "Gérer",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = EmeraldPrimary,
            modifier = Modifier.clickable { viewModel.openSubScreen(SubScreen.Recharge) }
          )
        }

        LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
          // Wave
          item {
            Row(
              modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(SurfaceContainerLow)
                .border(1.dp, BorderOutline, RoundedCornerShape(50.dp))
                .clickable { viewModel.openSubScreen(SubScreen.Recharge) }
                .padding(horizontal = 12.dp, vertical = 8.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(24.dp)
                  .clip(CircleShape)
                  .background(WaveBlue),
                contentAlignment = Alignment.Center
              ) {
                Text("W", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = SurfaceWhite)
              }
              Column {
                Text("Wave Sénégal", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text("Lié • 77 ••• 24", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = EmeraldPrimary)
              }
            }
          }

          // Orange Money
          item {
            Row(
              modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(SurfaceContainerLow)
                .border(1.dp, BorderOutline, RoundedCornerShape(50.dp))
                .clickable { viewModel.openSubScreen(SubScreen.Recharge) }
                .padding(horizontal = 12.dp, vertical = 8.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(24.dp)
                  .clip(CircleShape)
                  .background(OrangeMoney),
                contentAlignment = Alignment.Center
              ) {
                Text("OM", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = SurfaceWhite)
              }
              Column {
                Text("Orange Money", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text("Lié • 78 ••• 19", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = EmeraldPrimary)
              }
            }
          }

          // Banque UBA
          item {
            Row(
              modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(SurfaceContainerLow)
                .border(1.dp, BorderOutline, RoundedCornerShape(50.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(24.dp)
                  .clip(CircleShape)
                  .background(Color(0xFFD0021B)),
                contentAlignment = Alignment.Center
              ) {
                Text("UBA", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = SurfaceWhite)
              }
              Column {
                Text("Banque UBA", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text("RIB ••• 9021", fontSize = 10.sp, color = TextSecondary)
              }
            }
          }

          // Add button
          item {
            Row(
              modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(EmeraldLight)
                .clickable { viewModel.openSubScreen(SubScreen.Recharge) }
                .padding(horizontal = 12.dp, vertical = 10.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Lier",
                tint = EmeraldPrimary,
                modifier = Modifier.size(16.dp)
              )
              Text("Lier", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
            }
          }
        }
      }
    }

    // 7. Monthly Spending Analytics Breakdown
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(16.dp),
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
              text = "ANALYSE DU MOIS DE MAI",
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 0.8.sp,
              color = TextSecondary
            )
            Text(
              text = "324.450 FCFA",
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
              color = TextPrimary
            )
          }

          Row(
            modifier = Modifier
              .clip(RoundedCornerShape(50.dp))
              .background(EmeraldLight)
              .padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.TrendingDown,
              contentDescription = "Baisse",
              tint = EmeraldPrimary,
              modifier = Modifier.size(14.dp)
            )
            Text("-8% vs Avril", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = EmeraldPrimary)
          }
        }

        // Segmented bar
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(SurfaceContainerLow)
        ) {
          Box(modifier = Modifier.weight(0.42f).fillMaxSize().background(EmeraldPrimary))
          Box(modifier = Modifier.weight(0.28f).fillMaxSize().background(GoldDark))
          Box(modifier = Modifier.weight(0.18f).fillMaxSize().background(CyanTertiary))
          Box(modifier = Modifier.weight(0.12f).fillMaxSize().background(TextMuted))
        }

        // Legend 2x2
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          CategoryBox("Shopping & Services", "136.269 F", EmeraldPrimary, Modifier.weight(1f))
          CategoryBox("Senelec, Sen'Eau", "90.846 F", GoldDark, Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          CategoryBox("Restaurants & Loisirs", "58.401 F", CyanTertiary, Modifier.weight(1f))
          CategoryBox("Transferts pairs", "38.934 F", TextMuted, Modifier.weight(1f))
        }
      }
    }

    // 8. Order New Card CTA
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(16.dp),
      colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f).padding(end = 12.dp)) {
          Text(
            text = "ÉDITION MÉTAL GOLD",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = GoldDark,
            letterSpacing = 0.8.sp
          )
          Text(
            text = "Besoin d'une nouvelle carte ?",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
          )
          Text(
            text = "Commandez votre carte Mastercard Métal gravée au laser, livrée à Dakar en 24h.",
            fontSize = 11.sp,
            color = TextSecondary,
            lineHeight = 15.sp,
            modifier = Modifier.padding(top = 2.dp)
          )

          Button(
            onClick = { viewModel.showFeedback("Commande de carte initiée. Livraison sous 24h à Dakar.") },
            modifier = Modifier.padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GoldDark),
            shape = RoundedCornerShape(50.dp),
            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
          ) {
            Text("Commander", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SurfaceWhite)
            Spacer(modifier = Modifier.width(4.dp))
            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(14.dp))
          }
        }

        Box(
          modifier = Modifier
            .size(70.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Brush.linearGradient(listOf(GoldAmber, GoldDark))),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Bolt,
            contentDescription = null,
            tint = SurfaceWhite,
            modifier = Modifier.size(36.dp)
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(60.dp))
  }
}

@Composable
private fun CategoryBox(
  title: String,
  amount: String,
  color: Color,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .clip(RoundedCornerShape(8.dp))
      .background(SurfaceContainerLow)
      .padding(8.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Box(
      modifier = Modifier
        .size(8.dp)
        .clip(CircleShape)
        .background(color)
    )
    Column {
      Text(text = title, fontSize = 10.sp, color = TextSecondary, maxLines = 1)
      Text(text = amount, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
    }
  }
}
