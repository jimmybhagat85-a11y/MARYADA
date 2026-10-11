package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.DilemmaCategory
import com.example.ui.MaryadaViewModel
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDilemmaDialog(
    viewModel: MaryadaViewModel,
    onDismiss: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var scenario by remember { mutableStateOf("") }
    var context by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(DilemmaCategory.CAREER_WORKPLACE) }
    var categoryDropdownExpanded by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    AlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.testTag("custom_dilemma_dialog"),
        containerColor = CardSurfaceDark,
        shape = RoundedCornerShape(20.dp),
        title = {
            Text(
                text = "Describe My Own Problem",
                style = MaterialTheme.typography.titleLarge,
                color = DivyaGoldPrimary,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Present a real ethical dilemma from your life. The AI will evaluate your intended actions against Ramayana wisdom principles.",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMutedSand
                )

                // Category selector dropdown
                ExposedDropdownMenuBox(
                    expanded = categoryDropdownExpanded,
                    onExpandedChange = { categoryDropdownExpanded = !categoryDropdownExpanded }
                ) {
                    OutlinedTextField(
                        value = selectedCategory.title,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Category", color = GoldenAmber) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryDropdownExpanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = CardSurfaceElevated,
                            unfocusedContainerColor = CardSurfaceElevated,
                            focusedBorderColor = DivyaGoldPrimary,
                            unfocusedBorderColor = GoldBorderSubtle,
                            focusedTextColor = TextIvoryWhite,
                            unfocusedTextColor = TextIvoryWhite
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )

                    ExposedDropdownMenu(
                        expanded = categoryDropdownExpanded,
                        onDismissRequest = { categoryDropdownExpanded = false },
                        containerColor = CardSurfaceElevated
                    ) {
                        DilemmaCategory.entries.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(cat.title, color = TextIvoryWhite) },
                                onClick = {
                                    selectedCategory = cat
                                    categoryDropdownExpanded = false
                                }
                            )
                        }
                    }
                }

                // Title
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Dilemma Title", color = GoldenAmber) },
                    placeholder = { Text("Brief summary (e.g. Broken Partnership)", color = TextMutedSand) },
                    modifier = Modifier.fillMaxWidth().testTag("custom_dilemma_title_input"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = CardSurfaceElevated,
                        unfocusedContainerColor = CardSurfaceElevated,
                        focusedBorderColor = DivyaGoldPrimary,
                        unfocusedBorderColor = GoldBorderSubtle,
                        focusedTextColor = TextIvoryWhite,
                        unfocusedTextColor = TextIvoryWhite
                    ),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )

                // Detailed Situation
                OutlinedTextField(
                    value = scenario,
                    onValueChange = { scenario = it },
                    label = { Text("What is the Situation?", color = GoldenAmber) },
                    placeholder = { Text("Describe the conflict, choices, and stakes...", color = TextMutedSand) },
                    modifier = Modifier.fillMaxWidth().testTag("custom_dilemma_scenario_input"),
                    minLines = 4,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = CardSurfaceElevated,
                        unfocusedContainerColor = CardSurfaceElevated,
                        focusedBorderColor = DivyaGoldPrimary,
                        unfocusedBorderColor = GoldBorderSubtle,
                        focusedTextColor = TextIvoryWhite,
                        unfocusedTextColor = TextIvoryWhite
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                // Additional context
                OutlinedTextField(
                    value = context,
                    onValueChange = { context = it },
                    label = { Text("Key Constraints or Stakes (Optional)", color = GoldenAmber) },
                    placeholder = { Text("Who might be harmed? What are personal vs duty conflicts?", color = TextMutedSand) },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = CardSurfaceElevated,
                        unfocusedContainerColor = CardSurfaceElevated,
                        focusedBorderColor = DivyaGoldPrimary,
                        unfocusedBorderColor = GoldBorderSubtle,
                        focusedTextColor = TextIvoryWhite,
                        unfocusedTextColor = TextIvoryWhite
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (scenario.isNotBlank()) {
                        viewModel.createAndStartCustomDilemma(
                            title = title,
                            scenario = scenario,
                            context = context,
                            category = selectedCategory
                        )
                    }
                },
                enabled = scenario.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DivyaGoldPrimary,
                    contentColor = DeepBlack
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Start Reflection", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = TextWarmSilver)
            }
        }
    )
}
