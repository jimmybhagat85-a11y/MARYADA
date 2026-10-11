package com.example.ui.screens

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DilemmaCategory
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
fun QuestionScreen(
    viewModel: MaryadaViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val question by viewModel.currentQuestion.collectAsState()
    val userAnswer by viewModel.userAnswer.collectAsState()
    val userReasoning by viewModel.userReasoning.collectAsState()
    val isAnalyzing by viewModel.isAnalyzing.collectAsState()

    val scrollState = rememberScrollState()

    if (question == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DeepBlack),
            contentAlignment = Alignment.Center
        ) {
            Text("No question selected", color = TextIvoryWhite)
        }
        return
    }

    val currentQ = question!!
    val category = DilemmaCategory.fromId(currentQ.categoryId)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DeepBlack)
            .testTag("question_screen")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            // Header with Back Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { viewModel.navigateBack() },
                    modifier = Modifier.testTag("question_back_button")
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
                        text = category.title.uppercase(),
                        style = MaterialTheme.typography.labelSmall,
                        color = GoldenAmber,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = currentQ.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = TextIvoryWhite,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Scenario Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, GoldBorderMedium, RoundedCornerShape(16.dp)),
                colors = CardDefaults.cardColors(containerColor = CardSurfaceDark),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Text(
                        text = "THE SITUATION",
                        style = MaterialTheme.typography.labelSmall,
                        color = AntiqueGold,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = currentQ.scenario,
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextIvoryWhite,
                        lineHeight = 24.sp
                    )

                    if (currentQ.context.isNotBlank()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(CardSurfaceElevated, RoundedCornerShape(10.dp))
                                .padding(12.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = GoldenAmber,
                                modifier = Modifier
                                    .size(18.dp)
                                    .padding(top = 2.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = currentQ.context,
                                style = MaterialTheme.typography.bodySmall,
                                color = TextWarmSilver
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Suggested Response Directions
            if (currentQ.suggestedDirections.isNotBlank()) {
                val directions = currentQ.suggestedDirections.split("|")
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = DivyaGoldPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "SUGGESTED PERSPECTIVES (OPTIONAL)",
                            style = MaterialTheme.typography.labelSmall,
                            color = DivyaGoldPrimary,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    directions.forEach { dir ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .background(CardSurfaceDark, RoundedCornerShape(10.dp))
                                .border(0.5.dp, GoldBorderSubtle, RoundedCornerShape(10.dp))
                                .clickable { viewModel.applySuggestedDirection(dir) }
                                .padding(horizontal = 12.dp, vertical = 10.dp)
                        ) {
                            Text(
                                text = "• $dir",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextWarmSilver
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            // User Free-Text Answer Input
            Text(
                text = "WHAT WOULD YOU DO?",
                style = MaterialTheme.typography.titleMedium,
                color = DivyaGoldPrimary,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Text(
                text = "Describe your intended action in your own words. There is no single scripted answer.",
                style = MaterialTheme.typography.bodySmall,
                color = TextMutedSand,
                modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
            )

            OutlinedTextField(
                value = userAnswer,
                onValueChange = { viewModel.setUserAnswer(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("user_answer_input"),
                placeholder = {
                    Text(
                        "Explain what choice or action you would take...",
                        color = TextMutedSand
                    )
                },
                minLines = 4,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = CardSurfaceDark,
                    unfocusedContainerColor = CardSurfaceDark,
                    focusedBorderColor = DivyaGoldPrimary,
                    unfocusedBorderColor = GoldBorderSubtle,
                    focusedTextColor = TextIvoryWhite,
                    unfocusedTextColor = TextIvoryWhite
                ),
                shape = RoundedCornerShape(14.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // User Reasoning / Motive Input
            Text(
                text = "YOUR REASONING & MOTIVE",
                style = MaterialTheme.typography.titleMedium,
                color = GoldenAmber,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Text(
                text = "Why would you choose this path? What values or consequences did you prioritize?",
                style = MaterialTheme.typography.bodySmall,
                color = TextMutedSand,
                modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
            )

            OutlinedTextField(
                value = userReasoning,
                onValueChange = { viewModel.setUserReasoning(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("user_reasoning_input"),
                placeholder = {
                    Text(
                        "Explain the principles, duties, or trade-offs behind your decision...",
                        color = TextMutedSand
                    )
                },
                minLines = 3,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = CardSurfaceDark,
                    unfocusedContainerColor = CardSurfaceDark,
                    focusedBorderColor = GoldenAmber,
                    unfocusedBorderColor = GoldBorderSubtle,
                    focusedTextColor = TextIvoryWhite,
                    unfocusedTextColor = TextIvoryWhite
                ),
                shape = RoundedCornerShape(14.dp)
            )

            if (currentQ.followUpQuestions.isNotBlank()) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(CardSurfaceDark, RoundedCornerShape(12.dp))
                        .border(0.5.dp, GoldBorderSubtle, RoundedCornerShape(12.dp))
                        .padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.HelpOutline,
                        contentDescription = null,
                        tint = AntiqueGold,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Follow-up Consideration: ${currentQ.followUpQuestions}",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextWarmSilver
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Submit Button
            Button(
                onClick = { viewModel.submitForAnalysis() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("submit_analysis_button"),
                enabled = userAnswer.isNotBlank() && !isAnalyzing,
                colors = ButtonDefaults.buttonColors(
                    containerColor = DivyaGoldPrimary,
                    contentColor = DeepBlack,
                    disabledContainerColor = CardSurfaceElevated,
                    disabledContentColor = TextMutedSand
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                if (isAnalyzing) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = DeepBlack,
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Analyzing Dharmic Principles...",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Analyse My Answer",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}
