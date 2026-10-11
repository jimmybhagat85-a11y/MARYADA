package com.example.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Diversity1
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.ui.graphics.vector.ImageVector

enum class DilemmaCategory(
    val id: String,
    val title: String,
    val subtitle: String,
    val iconName: String
) {
    CAREER_WORKPLACE(
        "cat_career",
        "Career and Workplace",
        "Credit theft, ethical dilemmas, ambition vs duty, office politics",
        "BusinessCenter"
    ),
    RELATIONSHIPS_BETRAYAL(
        "cat_relationships",
        "Relationships and Betrayal",
        "Broken trust, forgiveness vs boundaries, commitments",
        "Favorite"
    ),
    FAMILY_RESPONSIBILITIES(
        "cat_family",
        "Family and Responsibilities",
        "Parental expectations, sibling disputes, duty to elders",
        "FamilyRestroom"
    ),
    ANGER_REVENGE(
        "cat_anger",
        "Anger and Revenge",
        "Provocation, self-restraint, righteous retribution vs forgiveness",
        "Shield"
    ),
    TRUTH_HONESTY(
        "cat_truth",
        "Truth and Honesty",
        "Difficult truths, white lies, whistleblowing, keeping promises",
        "Visibility"
    ),
    MONEY_GREED(
        "cat_money",
        "Money and Greed",
        "Unfair gains, financial compromises, generosity vs self-interest",
        "MonetizationOn"
    ),
    LEADERSHIP_JUSTICE(
        "cat_leadership",
        "Leadership and Justice",
        "Impartiality, protecting the vulnerable, team welfare",
        "Gavel"
    ),
    FRIENDSHIP_LOYALTY(
        "cat_friendship",
        "Friendship and Loyalty",
        "Standing by friends in crisis, calling out wrongdoing",
        "Diversity1"
    ),
    FEAR_FAILURE(
        "cat_fear",
        "Fear and Failure",
        "Courage in adversity, overcoming setbacks, imposter syndrome",
        "EmojiEvents"
    ),
    DIFFICULT_MORAL_DECISIONS(
        "cat_moral",
        "Difficult Moral Decisions",
        "No-win dilemmas, competing goods, sacrificing personal peace",
        "Balance"
    ),
    SOCIETY_INJUSTICE(
        "cat_society",
        "Society and Injustice",
        "Standing up to majority tyranny, social duty, helping strangers",
        "Balance"
    ),
    SELF_DISCIPLINE_GROWTH(
        "cat_growth",
        "Self-Discipline and Personal Growth",
        "Overcoming ego, mastery over desires, daily tapasya",
        "SelfImprovement"
    );

    companion object {
        fun fromId(id: String): DilemmaCategory =
            entries.find { it.id == id } ?: CAREER_WORKPLACE
    }
}
