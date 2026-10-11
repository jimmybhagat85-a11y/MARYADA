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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DilemmaCategory
import com.example.data.model.QuestionEntity
import com.example.ui.MaryadaViewModel
import com.example.ui.theme.AntiqueGold
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
fun CategoriesScreen(
    viewModel: MaryadaViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val allQuestions by viewModel.allQuestions.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    // Filter questions by category and search query
    val filteredQuestions = allQuestions.filter { q ->
        val matchesCategory = selectedCategory == null || q.categoryId == selectedCategory?.id
        val matchesSearch = searchQuery.isBlank() ||
                q.title.contains(searchQuery, ignoreCase = true) ||
                q.scenario.contains(searchQuery, ignoreCase = true)
        matchesCategory && matchesSearch
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DeepBlack)
            .testTag("categories_screen")
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))

                // Header with Back Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { viewModel.navigateBack() },
                        modifier = Modifier.testTag("categories_back_button")
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
                            text = "CHOOSE A LIFE SITUATION",
                            style = MaterialTheme.typography.titleMedium,
                            color = DivyaGoldPrimary,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Text(
                            text = "104 Scenarios Grounded in Ramayana Wisdom",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextWarmSilver
                        )
                    }
                }
            }

            // Search Bar
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.setSearchQuery(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("search_scenarios_input"),
                    placeholder = { Text("Search dilemmas (e.g. credit, layoff, debt...)", color = TextMutedSand) },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null, tint = AntiqueGold)
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear", tint = TextMutedSand)
                            }
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = CardSurfaceDark,
                        unfocusedContainerColor = CardSurfaceDark,
                        focusedBorderColor = DivyaGoldPrimary,
                        unfocusedBorderColor = GoldBorderSubtle,
                        focusedTextColor = TextIvoryWhite,
                        unfocusedTextColor = TextIvoryWhite
                    ),
                    shape = RoundedCornerShape(14.dp),
                    singleLine = true
                )
            }

            // Describe My Own Problem Action Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.toggleCustomDilemmaDialog(true) }
                        .border(1.dp, GoldBorderMedium, RoundedCornerShape(16.dp))
                        .testTag("describe_own_problem_card"),
                    colors = CardDefaults.cardColors(containerColor = CardSurfaceElevated),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(DivyaGoldPrimary.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
                                    .border(1.dp, DivyaGoldPrimary, RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    tint = DivyaGoldPrimary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column {
                                Text(
                                    text = "Describe My Own Problem",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = DivyaGoldPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Input a custom dilemma for AI Ramayana alignment analysis",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = TextWarmSilver,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = DivyaGoldPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // Category Chips Row
            item {
                Column {
                    Text(
                        text = "BROWSE 12 LIFE CATEGORIES",
                        style = MaterialTheme.typography.labelLarge,
                        color = TextMutedSand,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            FilterChip(
                                selected = selectedCategory == null,
                                onClick = { viewModel.selectCategory(null) },
                                label = { Text("All Categories (${allQuestions.size})") },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = DivyaGoldPrimary,
                                    selectedLabelColor = DeepBlack,
                                    containerColor = CardSurfaceDark,
                                    labelColor = TextWarmSilver
                                ),
                                border = FilterChipDefaults.filterChipBorder(
                                    borderColor = GoldBorderSubtle,
                                    selectedBorderColor = DivyaGoldPrimary,
                                    enabled = true,
                                    selected = selectedCategory == null
                                )
                            )
                        }

                        items(DilemmaCategory.entries) { cat ->
                            val count = allQuestions.count { it.categoryId == cat.id }
                            FilterChip(
                                selected = selectedCategory == cat,
                                onClick = {
                                    if (selectedCategory == cat) viewModel.selectCategory(null)
                                    else viewModel.selectCategory(cat)
                                },
                                label = { Text("${cat.title} ($count)") },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = DivyaGoldPrimary,
                                    selectedLabelColor = DeepBlack,
                                    containerColor = CardSurfaceDark,
                                    labelColor = TextWarmSilver
                                ),
                                border = FilterChipDefaults.filterChipBorder(
                                    borderColor = GoldBorderSubtle,
                                    selectedBorderColor = DivyaGoldPrimary,
                                    enabled = true,
                                    selected = selectedCategory == cat
                                )
                            )
                        }
                    }
                }
            }

            // Scenarios Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (selectedCategory != null) "${selectedCategory?.title} (${filteredQuestions.size})"
                               else "All Available Dilemmas (${filteredQuestions.size})",
                        style = MaterialTheme.typography.titleMedium,
                        color = TextIvoryWhite,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            // List of Scenarios
            items(filteredQuestions) { question ->
                ScenarioCard(
                    question = question,
                    onClick = { viewModel.startQuestion(question) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun ScenarioCard(
    question: QuestionEntity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .border(1.dp, GoldBorderSubtle, RoundedCornerShape(16.dp))
            .testTag("scenario_card_${question.id}"),
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
                // Category Tag
                val category = DilemmaCategory.fromId(question.categoryId)
                Text(
                    text = category.title.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = GoldenAmber,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                // Difficulty badge
                val badgeColor = when (question.difficulty) {
                    "Foundational" -> Color(0xFF81C784)
                    "Nuanced" -> DivyaGoldPrimary
                    else -> Color(0xFFFF8A65)
                }
                Box(
                    modifier = Modifier
                        .background(badgeColor.copy(alpha = 0.15f), RoundedCornerShape(6.dp))
                        .border(0.5.dp, badgeColor, RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = question.difficulty,
                        style = MaterialTheme.typography.labelSmall,
                        color = badgeColor,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = question.title,
                style = MaterialTheme.typography.titleMedium,
                color = TextIvoryWhite,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = question.scenario,
                style = MaterialTheme.typography.bodyMedium,
                color = TextMutedSand,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Reflect on this",
                    style = MaterialTheme.typography.labelLarge,
                    color = DivyaGoldPrimary,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = DivyaGoldPrimary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
