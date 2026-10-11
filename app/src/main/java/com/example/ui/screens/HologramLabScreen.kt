package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.MaryadaViewModel
import com.example.ui.hologram.LordRamHologram
import com.example.ui.theme.AntiqueGold
import com.example.ui.theme.CardSurfaceDark
import com.example.ui.theme.CardSurfaceElevated
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
fun HologramLabScreen(
    viewModel: MaryadaViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val labPercentage by viewModel.labPercentage.collectAsState()
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DeepBlack)
            .testTag("hologram_lab_screen")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { viewModel.navigateBack() },
                    modifier = Modifier.testTag("hologram_lab_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = DivyaGoldPrimary
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                Column {
                    Text(
                        text = "HOLOGRAM SIMULATOR",
                        style = MaterialTheme.typography.titleMedium,
                        color = DivyaGoldPrimary,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Interactive Percentage-Controlled Visualizer",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextWarmSilver
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // The Interactive Hologram Canvas
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, GoldBorderMedium, RoundedCornerShape(24.dp)),
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
                        targetPercentage = labPercentage,
                        height = 360.dp,
                        showScoreLabel = true,
                        animationDurationMs = 1200
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Presets row: 0%, 25%, 50%, 75%, 100% (Prompt Section 10 Requirement)
            Text(
                text = "TEST PRESETS",
                style = MaterialTheme.typography.labelMedium,
                color = AntiqueGold,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(0, 25, 50, 75, 100).forEach { preset ->
                    val isSelected = labPercentage == preset
                    Button(
                        onClick = { viewModel.setLabPercentage(preset) },
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp)
                            .testTag("preset_button_$preset"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) DivyaGoldPrimary else CardSurfaceElevated,
                            contentColor = if (isSelected) DeepBlack else TextIvoryWhite
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "$preset%",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Continuous Slider Control
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, GoldBorderSubtle, RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = CardSurfaceDark),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Continuous Energy Slider",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextWarmSilver,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "$labPercentage%",
                            style = MaterialTheme.typography.titleMedium,
                            color = DivyaGoldPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Slider(
                        value = labPercentage.toFloat(),
                        onValueChange = { viewModel.setLabPercentage(it.toInt()) },
                        valueRange = 0f..100f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("hologram_percentage_slider"),
                        colors = SliderDefaults.colors(
                            thumbColor = DivyaGoldPrimary,
                            activeTrackColor = DivyaGoldPrimary,
                            inactiveTrackColor = CardSurfaceElevated
                        )
                    )

                    val description = when {
                        labPercentage == 100 -> "100%: Entire Lord Ram hologram is completely illuminated in radiant golden light."
                        labPercentage >= 75 -> "$labPercentage%: Three-quarters illuminated; upper portion rests in faint ambient outlines."
                        labPercentage >= 50 -> "$labPercentage%: Half illuminated with active wavefront energy pulse at midline."
                        labPercentage >= 25 -> "$labPercentage%: Lower quarter glows with golden energy; figure remains visible in dark outline."
                        labPercentage == 0 -> "0%: Faint, dark outline with subtle ambient glow and no golden fill."
                        else -> "$labPercentage%: Bottom-up energy illumination following the silhouette's proportions."
                    }

                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodySmall,
                        color = GoldenAmber,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Holographic Architecture Details
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(0.5.dp, GoldBorderSubtle, RoundedCornerShape(14.dp)),
                colors = CardDefaults.cardColors(containerColor = CardSurfaceElevated),
                shape = RoundedCornerShape(14.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = AntiqueGold,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Hologram Architecture: Masked vector silhouette of Lord Ram holding Kodanda bow and arrow. Rendered in Jetpack Compose Canvas with bottom-to-top vertical energy clipping, radiant amber gradients, active wavefront flare, and floating golden prana particles.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMutedSand,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
