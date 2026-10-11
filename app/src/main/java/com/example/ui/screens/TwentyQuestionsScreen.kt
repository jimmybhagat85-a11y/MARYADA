package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DilemmaCategory
import com.example.ui.MaryadaViewModel
import com.example.ui.theme.CardSurfaceDark
import com.example.ui.theme.CardSurfaceElevated
import com.example.ui.theme.CosmicDivider
import com.example.ui.theme.DeepBlack
import com.example.ui.theme.DivyaGoldPrimary
import com.example.ui.theme.GoldBorderMedium
import com.example.ui.theme.GoldBorderSubtle
import com.example.ui.theme.GoldenAmber
import com.example.ui.theme.TextIvoryWhite
import com.example.ui.theme.TextMutedSand
import com.example.ui.theme.TextWarmSilver

@Composable
fun TwentyQuestionsScreen(
    viewModel: MaryadaViewModel,
    modifier: Modifier = Modifier
) {
    // Intercept back navigation to return cleanly to Welcome Landing Page
    BackHandler {
        viewModel.navigateBack()
    }

    val twentyQuestions by viewModel.twentyQuestions.collectAsState()
    val currentIndex by viewModel.currentQuestionIndex.collectAsState()
    val currentAnswer by viewModel.currentAnswerText.collectAsState()
    val currentReasoning by viewModel.currentReasoningText.collectAsState()
    val selectedChoiceIndex by viewModel.selectedChoiceIndex.collectAsState()
    val isAnalyzing by viewModel.isAnalyzing.collectAsState()

    val scrollState = rememberScrollState()
    var showCustomInput by remember { mutableStateOf(false) }

    // Spiritual Transition / Analyzing Screen
    if (isAnalyzing) {
        val infiniteTransition = rememberInfiniteTransition(label = "analyzing_pulse")
        val pulseAlpha by infiniteTransition.animateFloat(
            initialValue = 0.6f,
            targetValue = 1.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(1400, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "pulse_alpha"
        )

        Box(
            modifier = modifier
                .fillMaxSize()
                .background(DeepBlack),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(32.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color(0x1AFFE082))
                        .border(1.5.dp, DivyaGoldPrimary.copy(alpha = pulseAlpha), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = DivyaGoldPrimary,
                        modifier = Modifier.size(56.dp),
                        strokeWidth = 3.dp
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                Text(
                    text = "20 प्रश्नों का विश्लेषण...",
                    style = MaterialTheme.typography.headlineMedium,
                    color = DivyaGoldPrimary,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Evaluating your moral decisions across Satya, Dharma, Samyama, Nyaya, and Karuna through Lord Ram's timeless rubrics.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextWarmSilver,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "धर्मो रक्षति रक्षितः • Dharma protects those who uphold Dharma",
                    style = MaterialTheme.typography.bodySmall,
                    color = GoldenAmber,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        return
    }

    if (twentyQuestions.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(DeepBlack),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = DivyaGoldPrimary)
        }
        return
    }

    val currentQ = twentyQuestions[currentIndex]
    val category = DilemmaCategory.fromId(currentQ.categoryId)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DeepBlack)
            .testTag("twenty_questions_screen")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // TOP MINIMALIST HEADER
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { viewModel.navigateBack() },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back to Landing Page",
                        tint = DivyaGoldPrimary,
                        modifier = Modifier.size(20.dp)
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

                // Sleek Question Counter Pill
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(CardSurfaceElevated)
                        .border(0.6.dp, GoldBorderSubtle, RoundedCornerShape(14.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${currentIndex + 1} / 20",
                        style = MaterialTheme.typography.labelSmall,
                        color = GoldenAmber,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // MINIMALIST PROGRESS BAR
            LinearProgressIndicator(
                progress = { (currentIndex + 1) / 20f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = DivyaGoldPrimary,
                trackColor = CosmicDivider
            )

            Spacer(modifier = Modifier.height(18.dp))

            // DILEMMA SCENARIO CARD
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(0.8.dp, GoldBorderSubtle, RoundedCornerShape(20.dp))
                    .testTag("dilemma_scenario_card"),
                colors = CardDefaults.cardColors(containerColor = CardSurfaceDark),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    // Category & Difficulty Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(CardSurfaceElevated)
                                .border(0.5.dp, GoldBorderSubtle, RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = category.title.uppercase(),
                                style = MaterialTheme.typography.labelSmall,
                                color = GoldenAmber,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.5.sp
                            )
                        }

                        Text(
                            text = currentQ.difficulty,
                            style = MaterialTheme.typography.labelSmall,
                            color = TextMutedSand
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = currentQ.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = TextIvoryWhite,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 26.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = currentQ.scenario,
                        style = MaterialTheme.typography.bodyLarge,
                        color = TextWarmSilver,
                        lineHeight = 24.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // QUESTION PROMPT
            Text(
                text = "Aap kya karenge? (What would you do?)",
                style = MaterialTheme.typography.titleSmall,
                color = DivyaGoldPrimary,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            )
            Text(
                text = "Ek vikalp chunein (Choose one option):",
                style = MaterialTheme.typography.bodySmall,
                color = TextMutedSand,
                modifier = Modifier.padding(top = 2.dp, bottom = 10.dp)
            )

            // 4 SUGGESTED CHOICE CARDS
            val options = currentQ.suggestedDirections.split("|")
            options.forEachIndexed { optIndex, optionText ->
                val isSelected = selectedChoiceIndex == optIndex || currentAnswer == optionText

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable { viewModel.selectChoice(optIndex, optionText) }
                        .border(
                            width = if (isSelected) 1.5.dp else 0.6.dp,
                            color = if (isSelected) DivyaGoldPrimary else GoldBorderSubtle,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .testTag("option_card_$optIndex"),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) Color(0x24FFD700) else CardSurfaceDark
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                            contentDescription = null,
                            tint = if (isSelected) DivyaGoldPrimary else TextMutedSand,
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = optionText,
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (isSelected) TextIvoryWhite else TextWarmSilver,
                            lineHeight = 20.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // OPTIONAL: EXPANDABLE REASONING PROMPT
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showCustomInput = !showCustomInput }
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.EditNote,
                    contentDescription = null,
                    tint = GoldenAmber,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (showCustomInput) "Apna karan chupayein (Hide reasoning field)"
                           else "+ Apna karan ya vichar likhein (Optional reasoning / thoughts)",
                    style = MaterialTheme.typography.bodySmall,
                    color = GoldenAmber,
                    fontWeight = FontWeight.SemiBold
                )
            }

            AnimatedVisibility(visible = showCustomInput) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    OutlinedTextField(
                        value = currentAnswer,
                        onValueChange = { viewModel.setCurrentAnswerText(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("twenty_questions_custom_answer"),
                        label = { Text("Aapka Nirnay (Custom Action)", color = GoldenAmber) },
                        placeholder = { Text("Apne shabdon mein likhein...", color = TextMutedSand) },
                        minLines = 2,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = CardSurfaceDark,
                            unfocusedContainerColor = CardSurfaceDark,
                            focusedBorderColor = DivyaGoldPrimary,
                            unfocusedBorderColor = GoldBorderSubtle,
                            focusedTextColor = TextIvoryWhite,
                            unfocusedTextColor = TextIvoryWhite
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = currentReasoning,
                        onValueChange = { viewModel.setCurrentReasoningText(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("twenty_questions_reasoning"),
                        label = { Text("Karan / Reason (Why this choice?)", color = GoldenAmber) },
                        placeholder = { Text("Aapne yeh vikalp kyon chuna?", color = TextMutedSand) },
                        minLines = 2,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = CardSurfaceDark,
                            unfocusedContainerColor = CardSurfaceDark,
                            focusedBorderColor = GoldenAmber,
                            unfocusedBorderColor = GoldBorderSubtle,
                            focusedTextColor = TextIvoryWhite,
                            unfocusedTextColor = TextIvoryWhite
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // NAVIGATION BUTTONS: PREVIOUS & NEXT / REVEAL HOLOGRAM
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (currentIndex > 0) {
                    OutlinedButton(
                        onClick = { viewModel.previousQuestion() },
                        modifier = Modifier
                            .weight(1f)
                            .height(54.dp)
                            .testTag("twenty_questions_prev_button"),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = androidx.compose.ui.graphics.SolidColor(GoldBorderSubtle)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Previous",
                            tint = TextWarmSilver,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("पिछला (Prev)", color = TextWarmSilver, fontWeight = FontWeight.SemiBold)
                    }
                }

                val isLastQuestion = currentIndex == 19
                val canProceed = currentAnswer.isNotBlank()

                Button(
                    onClick = { viewModel.nextQuestion() },
                    modifier = Modifier
                        .weight(if (currentIndex > 0) 2f else 1f)
                        .height(54.dp)
                        .testTag("twenty_questions_next_button"),
                    enabled = canProceed,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DivyaGoldPrimary,
                        contentColor = DeepBlack,
                        disabledContainerColor = CardSurfaceElevated,
                        disabledContentColor = TextMutedSand
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = if (isLastQuestion) "Reveal Hologram • संपूर्ण विश्लेषण"
                               else "अगला प्रश्न (Next Question)",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = if (isLastQuestion) Icons.Default.AutoAwesome else Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
