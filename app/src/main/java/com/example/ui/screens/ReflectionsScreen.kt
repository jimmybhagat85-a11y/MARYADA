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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.SavedReflectionEntity
import com.example.ui.MaryadaViewModel
import com.example.ui.theme.AntiqueGold
import com.example.ui.theme.CardSurfaceDark
import com.example.ui.theme.CardSurfaceElevated
import com.example.ui.theme.DeepBlack
import com.example.ui.theme.DivyaGoldPrimary
import com.example.ui.theme.GoldBorderMedium
import com.example.ui.theme.GoldBorderSubtle
import com.example.ui.theme.GoldenAmber
import com.example.ui.theme.TextIvoryWhite
import com.example.ui.theme.TextMutedSand
import com.example.ui.theme.TextWarmSilver

@Composable
fun ReflectionsScreen(
    viewModel: MaryadaViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val reflections by viewModel.savedReflections.collectAsState()
    var showClearConfirmDialog by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DeepBlack)
            .testTag("reflections_screen")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = { viewModel.navigateBack() },
                        modifier = Modifier.testTag("reflections_back_button")
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
                            text = "MY REFLECTIONS",
                            style = MaterialTheme.typography.titleMedium,
                            color = DivyaGoldPrimary,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "${reflections.size} Saved Reflections",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextWarmSilver
                        )
                    }
                }

                if (reflections.isNotEmpty()) {
                    IconButton(onClick = { showClearConfirmDialog = true }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Clear All",
                            tint = Color(0xFFFF8A65)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Privacy & Progress Stats Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(0.5.dp, GoldBorderSubtle, RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = CardSurfaceDark),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = AntiqueGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "PRIVACY ASSURANCE",
                            style = MaterialTheme.typography.labelSmall,
                            color = AntiqueGold,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Your reflections and dilemma responses are saved exclusively locally on this device. You can delete individual reflections or purge all history at any time.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMutedSand,
                        lineHeight = 18.sp
                    )

                    if (reflections.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        val avgScore = reflections.map { it.overallScore }.average().toInt()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(CardSurfaceElevated, RoundedCornerShape(10.dp))
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Average Alignment",
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextWarmSilver
                            )
                            Text(
                                text = "$avgScore%",
                                style = MaterialTheme.typography.titleMedium,
                                color = DivyaGoldPrimary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (reflections.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 60.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = AntiqueGold,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "No Saved Reflections Yet",
                            style = MaterialTheme.typography.titleMedium,
                            color = TextWarmSilver
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Complete an analysis and tap 'Save Reflection' to store your insights here.",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextMutedSand
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(reflections) { ref ->
                        SavedReflectionCard(
                            reflection = ref,
                            onDelete = { viewModel.deleteReflection(ref) }
                        )
                    }
                    item { Spacer(modifier = Modifier.height(30.dp)) }
                }
            }
        }

        // Clear All Confirmation Dialog
        if (showClearConfirmDialog) {
            AlertDialog(
                onDismissRequest = { showClearConfirmDialog = false },
                containerColor = CardSurfaceDark,
                title = {
                    Text(
                        text = "Clear All Reflections?",
                        color = DivyaGoldPrimary,
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Text(
                        text = "This will permanently remove all saved reflections from your local database. This action cannot be undone.",
                        color = TextWarmSilver
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.clearAllReflections()
                            showClearConfirmDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC62828))
                    ) {
                        Text("Delete All", color = Color.White)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showClearConfirmDialog = false }) {
                        Text("Cancel", color = TextWarmSilver)
                    }
                }
            )
        }
    }
}

@Composable
fun SavedReflectionCard(
    reflection: SavedReflectionEntity,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
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
                    text = reflection.questionTitle,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextIvoryWhite,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                // Score pill
                Box(
                    modifier = Modifier
                        .background(DivyaGoldPrimary.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                        .border(1.dp, DivyaGoldPrimary, RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${reflection.overallScore}%",
                        style = MaterialTheme.typography.labelLarge,
                        color = DivyaGoldPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Your Stated Action: ${reflection.userAnswer}",
                style = MaterialTheme.typography.bodySmall,
                color = TextWarmSilver
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Ramayana Episode: ${reflection.episodeTitle} (${reflection.episodeReference})",
                style = MaterialTheme.typography.bodySmall,
                color = GoldenAmber
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Considered Path: ${reflection.consideredPath}",
                style = MaterialTheme.typography.bodySmall,
                color = TextMutedSand
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color(0xFFFF8A65),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
