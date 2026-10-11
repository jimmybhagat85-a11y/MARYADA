package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.EthicalDimension
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

@Composable
fun ResultScreen(
    viewModel: MaryadaViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val context = LocalContext.current
    val result by viewModel.analysisResult.collectAsState()
    val isSaved by viewModel.isCurrentResultSaved.collectAsState()

    var showWisdomSourceExpanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    if (result == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DeepBlack),
            contentAlignment = Alignment.Center
        ) {
            Text("No analysis available", color = TextIvoryWhite)
        }
        return
    }

    val res = result!!
    val archetype = MoralArchetypes.forScore(res.overallScore)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DeepBlack)
            .testTag("result_screen")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TOP BAR: Navigation, Brand, and Native Share
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { viewModel.navigateBack() },
                    modifier = Modifier.testTag("result_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = DivyaGoldPrimary
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = null,
                        tint = DivyaGoldPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "M A R Y A D A",
                        style = MaterialTheme.typography.titleSmall,
                        color = DivyaGoldPrimary,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 3.sp
                    )
                }

                IconButton(
                    onClick = {
                        val shareIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, archetype.generateShareMessage(res.overallScore))
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Share Maryada Score"))
                    },
                    modifier = Modifier.testTag("share_result_icon_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = DivyaGoldPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // REFINED LORD RAM HOLOGRAM CARD
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, GoldBorderMedium, RoundedCornerShape(24.dp))
                    .testTag("result_hologram_card"),
                colors = CardDefaults.cardColors(containerColor = CardSurfaceDark),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LordRamHologram(
                        targetPercentage = res.overallScore,
                        height = 320.dp,
                        showScoreLabel = false,
                        animationDurationMs = 2400
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "YOUR MARYADA ALIGNMENT",
                        style = MaterialTheme.typography.labelSmall,
                        color = AntiqueGold,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "${res.overallScore}%",
                        style = MaterialTheme.typography.displayLarge,
                        color = DivyaGoldPrimary,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    // Minimalist Archetype Badge Pill
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(CardSurfaceElevated)
                            .border(1.dp, archetype.auraColor.copy(alpha = 0.6f), RoundedCornerShape(20.dp))
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = archetype.badgeSymbol, fontSize = 16.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "${archetype.titleEn.uppercase()} • ${archetype.titleHi}",
                                style = MaterialTheme.typography.titleSmall,
                                color = archetype.auraColor,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = archetype.auraName,
                        style = MaterialTheme.typography.labelSmall,
                        color = GoldenAmber,
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = archetype.shortDescription,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextWarmSilver,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp,
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // VIRAL SHAREABLE STORY CARD PREVIEW (INSTAGRAM / WHATSAPP STYLE)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.5.dp, archetype.auraColor.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                    .testTag("viral_story_card_preview"),
                colors = CardDefaults.cardColors(containerColor = CardSurfaceElevated),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "🏹", fontSize = 16.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "VIRAL STORY CARD",
                                style = MaterialTheme.typography.labelSmall,
                                color = archetype.auraColor,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0x22FFD700))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "SHARE READY",
                                style = MaterialTheme.typography.labelSmall,
                                color = LuminousGoldLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 9.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Inner Aesthetic Story Box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFF090A0E))
                            .border(0.8.dp, GoldBorderSubtle, RoundedCornerShape(14.dp))
                            .padding(16.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "M A R Y A D A",
                                style = MaterialTheme.typography.labelMedium,
                                color = DivyaGoldPrimary,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 4.sp
                            )
                            Text(
                                text = "20-Question Moral Dilemma Test",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextMutedSand,
                                fontSize = 10.sp
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "${res.overallScore}%",
                                    style = MaterialTheme.typography.displayMedium,
                                    color = DivyaGoldPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "${archetype.badgeSymbol} ${archetype.titleEn}",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = archetype.auraColor,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = archetype.titleHi,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = GoldenAmber
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "\"${archetype.viralQuote}\"",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextIvoryWhite,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 18.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Trending in India #MaryadaTest",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextMutedSand,
                                fontSize = 9.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // VIRAL SHARE ACTION BUTTONS
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // WhatsApp / Status Direct Share
                        Button(
                            onClick = {
                                val shareIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, archetype.generateShareMessage(res.overallScore))
                                    type = "text/plain"
                                }
                                context.startActivity(Intent.createChooser(shareIntent, "Share on WhatsApp / Socials"))
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .testTag("whatsapp_share_button"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DivyaGoldPrimary,
                                contentColor = DeepBlack
                            ),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Share Result",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        // Challenge Friend Button
                        OutlinedButton(
                            onClick = {
                                val challengeIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, archetype.generateChallengeMessage(res.overallScore))
                                    type = "text/plain"
                                }
                                context.startActivity(Intent.createChooser(challengeIntent, "Challenge a Friend"))
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .testTag("challenge_friend_button"),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = Brush.horizontalGradient(listOf(GoldenAmber, DivyaGoldPrimary))
                            ),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.FlashOn,
                                contentDescription = null,
                                tint = DivyaGoldPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Challenge Friend",
                                color = DivyaGoldPrimary,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Copy Text Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("Maryada Result", archetype.generateShareMessage(res.overallScore))
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(context, "Story text copied to clipboard!", Toast.LENGTH_SHORT).show()
                            }
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = null,
                            tint = TextMutedSand,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Copy formatted story caption",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextMutedSand
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // 5 CORE RAMAYANA VIRTUES BREAKDOWN
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(0.8.dp, GoldBorderSubtle, RoundedCornerShape(18.dp))
                    .testTag("virtues_breakdown_card"),
                colors = CardDefaults.cardColors(containerColor = CardSurfaceDark),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Text(
                        text = "5 ETERNAL VIRTUES BREAKDOWN (पंच मर्यादाएं)",
                        style = MaterialTheme.typography.labelSmall,
                        color = AntiqueGold,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    val dimensions = listOf(
                        Triple(EthicalDimension.SATYA, "Satya (सत्य)", "Truth & Honesty"),
                        Triple(EthicalDimension.DHARMA, "Dharma (धर्म)", "Righteous Duty"),
                        Triple(EthicalDimension.SAMYAMA, "Samyama (संयम)", "Emotional Restraint"),
                        Triple(EthicalDimension.NYAYA, "Nyaya (न्याय)", "Fairness & Justice"),
                        Triple(EthicalDimension.KARUNA, "Karuna (करुणा)", "Compassion")
                    )

                    dimensions.forEach { (dim, title, desc) ->
                        val dimScore = res.dimensionScores[dim] ?: res.overallScore

                        Column(modifier = Modifier.padding(vertical = 6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = title,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = TextIvoryWhite,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "• $desc",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = TextMutedSand
                                    )
                                }

                                Text(
                                    text = "$dimScore%",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = DivyaGoldPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            LinearProgressIndicator(
                                progress = { dimScore / 100f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(4.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                color = if (dimScore >= 75) DivyaGoldPrimary else GoldenAmber,
                                trackColor = CosmicDivider
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // KEY DHARMIC HIGHLIGHTS
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(0.8.dp, GoldBorderSubtle, RoundedCornerShape(18.dp))
                    .testTag("dharmic_highlights_card"),
                colors = CardDefaults.cardColors(containerColor = CardSurfaceDark),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Text(
                        text = "WHERE YOU ALIGNED (मर्यादा का पालन)",
                        style = MaterialTheme.typography.labelSmall,
                        color = DivyaGoldPrimary,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    res.alignedPoints.take(3).forEach { point ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = DivyaGoldPrimary,
                                modifier = Modifier
                                    .size(16.dp)
                                    .padding(top = 2.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = point,
                                style = MaterialTheme.typography.bodySmall,
                                color = TextWarmSilver,
                                lineHeight = 18.sp
                            )
                        }
                    }

                    if (res.reflectOnPoints.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = "POINTS FOR DEEPER CONTEMPLATION (चिंतन के बिंदु)",
                            style = MaterialTheme.typography.labelSmall,
                            color = GoldenAmber,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        res.reflectOnPoints.take(2).forEach { point ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Lightbulb,
                                    contentDescription = null,
                                    tint = GoldenAmber,
                                    modifier = Modifier
                                    .size(16.dp)
                                    .padding(top = 2.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = point,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextWarmSilver,
                                    lineHeight = 18.sp
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // BOTTOM ACTIONS: SAVE REFLECTION & RETAKE TEST
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { viewModel.saveCurrentReflection() },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .testTag("save_reflection_button"),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = Brush.horizontalGradient(listOf(AntiqueGold, DivyaGoldPrimary))
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(
                        imageVector = if (isSaved) Icons.Default.BookmarkAdded else Icons.Default.Bookmark,
                        contentDescription = null,
                        tint = DivyaGoldPrimary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isSaved) "Saved" else "Save Result",
                        color = DivyaGoldPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = { viewModel.startNewTwentyQuestionsSession() },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .testTag("retake_test_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DivyaGoldPrimary,
                        contentColor = DeepBlack
                    ),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Retake Test",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
