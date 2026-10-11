package com.example.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MoralArchetypes
import com.example.ui.AppScreen
import com.example.ui.MaryadaViewModel
import com.example.ui.hologram.LordRamHologram
import com.example.ui.theme.AntiqueGold
import com.example.ui.theme.CardSurfaceDark
import com.example.ui.theme.CardSurfaceElevated
import com.example.ui.theme.CosmicDivider
import com.example.ui.theme.DeepBlack
import com.example.ui.theme.DivyaGoldPrimary
import com.example.ui.theme.GoldBorderMedium
import com.example.ui.theme.GoldBorderSubtle
import com.example.ui.theme.GoldenAmber
import com.example.ui.theme.LuminousGoldLight
import com.example.ui.theme.TextIvoryWhite
import com.example.ui.theme.TextMutedSand
import com.example.ui.theme.TextWarmSilver
import com.example.ui.theme.WarmGlowOrange

@Composable
fun WelcomeScreen(
    viewModel: MaryadaViewModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    val infiniteTransition = rememberInfiniteTransition(label = "landing_glow")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_alpha"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DeepBlack)
            .testTag("welcome_landing_screen")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // VIRAL SOCIAL PROOF BADGE (Trending in India)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color(0x1FFFFD700))
                    .border(0.8.dp, GoldBorderMedium.copy(alpha = pulseAlpha), RoundedCornerShape(30.dp))
                    .padding(horizontal = 14.dp, vertical = 6.dp)
                    .testTag("viral_social_proof_badge")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(DivyaGoldPrimary)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "84,000+ SEEKERS TESTED • TRENDING #MARYADATEST",
                        style = MaterialTheme.typography.labelSmall,
                        color = LuminousGoldLight,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // REFINED MINIMALIST BRANDING
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = null,
                    tint = DivyaGoldPrimary,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "M A R Y A D A",
                    style = MaterialTheme.typography.headlineLarge,
                    color = DivyaGoldPrimary,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 6.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = null,
                    tint = DivyaGoldPrimary,
                    modifier = Modifier.size(22.dp)
                )
            }

            Text(
                text = "What Would Lord Ram Say?",
                style = MaterialTheme.typography.titleMedium,
                color = GoldenAmber,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.2.sp,
                modifier = Modifier.padding(top = 2.dp)
            )

            Text(
                text = "Ancient Wisdom. Modern Life. • प्राचीन मर्यादा, आधुनिक जीवन",
                style = MaterialTheme.typography.bodySmall,
                color = TextWarmSilver,
                letterSpacing = 0.5.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // MINIMALIST HERO HOLOGRAM CARD
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, GoldBorderSubtle, RoundedCornerShape(22.dp))
                    .testTag("hero_hologram_card"),
                colors = CardDefaults.cardColors(containerColor = CardSurfaceDark),
                shape = RoundedCornerShape(22.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LordRamHologram(
                        targetPercentage = 85,
                        height = 260.dp,
                        showScoreLabel = false,
                        animationDurationMs = 2000
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "धर्मो रक्षति रक्षितः",
                        style = MaterialTheme.typography.titleMedium,
                        color = DivyaGoldPrimary,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )

                    Text(
                        text = "Dharma protects those who uphold Dharma.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextWarmSilver,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // WHAT THE NEXT 20 QUESTIONS WILL DESCRIBE (Landing Page Core Section)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(0.8.dp, GoldBorderSubtle, RoundedCornerShape(18.dp))
                    .testTag("what_next_20_will_describe_card"),
                colors = CardDefaults.cardColors(containerColor = CardSurfaceDark),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = DivyaGoldPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "WHAT THE 20 QUESTIONS DESCRIBE",
                            style = MaterialTheme.typography.titleSmall,
                            color = DivyaGoldPrimary,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }

                    Text(
                        text = "अगले 20 प्रश्नों का टेस्ट कैसे काम करेगा:",
                        style = MaterialTheme.typography.bodySmall,
                        color = GoldenAmber,
                        modifier = Modifier.padding(top = 2.dp, bottom = 14.dp)
                    )

                    // 3 Ultra-Clean Minimalist Pillars
                    MinimalistPillarRow(
                        step = "01",
                        icon = Icons.Default.Psychology,
                        title = "20 Real Kalyug Dilemmas (वास्तविक चुनौतियाँ)",
                        desc = "Office credit, family duties, money temptations, anger & tough ethics — framed in fluid Hindi + English mix."
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    MinimalistPillarRow(
                        step = "02",
                        icon = Icons.Default.VisibilityOff,
                        title = "No Mid-Test Distractions (बिना भटकाव के)",
                        desc = "No scores after each question. Focus purely on your conscience (अंतर्रात्मा) without anxiety."
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    MinimalistPillarRow(
                        step = "03",
                        icon = Icons.Default.WorkspacePremium,
                        title = "Divine Hologram & Moral Archetype (अंतिम परिणाम)",
                        desc = "Lord Ram's golden hologram illuminates with your overall 0–100% score, archetype badge, and viral share card."
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // VIRAL ARCHETYPES SHOWCASE TEASER
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(0.6.dp, GoldBorderSubtle, RoundedCornerShape(16.dp))
                    .testTag("archetypes_teaser_card"),
                colors = CardDefaults.cardColors(containerColor = CardSurfaceElevated),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp)
                ) {
                    Text(
                        text = "DISCOVER YOUR MORAL ARCHETYPE (आपकी मर्यादा श्रेणी)",
                        style = MaterialTheme.typography.labelSmall,
                        color = AntiqueGold,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ArchetypeMiniBadge("👑", "Dharmic Warrior", "90%+")
                        ArchetypeMiniBadge("🏹", "Satya Sadhak", "75%+")
                        ArchetypeMiniBadge("⚖️", "Nyaya Mitra", "60%+")
                        ArchetypeMiniBadge("🕊️", "Samyam Yogi", "45%+")
                    }
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            // PRIMARY CALL TO ACTION: BEGIN TEST (Ultra-Viral Glowing Gold Button)
            Button(
                onClick = { viewModel.startNewTwentyQuestionsSession() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .testTag("begin_test_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DivyaGoldPrimary,
                    contentColor = DeepBlack
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text(
                    text = "Begin 20-Question Test • टेस्ट शुरू करें",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // MINIMALIST SECONDARY ACTIONS
            OutlinedButton(
                onClick = { viewModel.navigateTo(AppScreen.WISDOM_EXPLORER) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("explore_wisdom_button"),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = Brush.horizontalGradient(listOf(AntiqueGold, DivyaGoldPrimary))
                ),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Explore,
                    contentDescription = null,
                    tint = DivyaGoldPrimary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Explore 100+ Scenarios & Ramayana Sources",
                    style = MaterialTheme.typography.bodySmall,
                    color = DivyaGoldPrimary,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    onClick = { viewModel.navigateTo(AppScreen.HOLOGRAM_LAB) },
                    modifier = Modifier.testTag("hologram_simulator_nav_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Science,
                        contentDescription = null,
                        tint = GoldenAmber,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Hologram Simulator",
                        color = GoldenAmber,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                TextButton(
                    onClick = { viewModel.navigateTo(AppScreen.SAVED_REFLECTIONS) },
                    modifier = Modifier.testTag("saved_reflections_nav_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Bookmark,
                        contentDescription = null,
                        tint = TextWarmSilver,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Saved Reflections",
                        color = TextWarmSilver,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun MinimalistPillarRow(
    step: String,
    icon: ImageVector,
    title: String,
    desc: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(CardSurfaceElevated)
                .border(0.6.dp, GoldBorderSubtle, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = DivyaGoldPrimary,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = TextIvoryWhite,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = desc,
                style = MaterialTheme.typography.bodySmall,
                color = TextWarmSilver,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
private fun ArchetypeMiniBadge(
    emoji: String,
    name: String,
    score: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 2.dp)
    ) {
        Text(text = emoji, fontSize = 18.sp)
        Text(
            text = name,
            style = MaterialTheme.typography.labelSmall,
            color = TextWarmSilver,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        Text(
            text = score,
            style = MaterialTheme.typography.labelSmall,
            color = GoldenAmber,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
