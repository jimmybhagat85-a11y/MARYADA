package com.example.data.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.DimCompassionTeal
import com.example.ui.theme.DimCourageAmber
import com.example.ui.theme.DimDharmaGold
import com.example.ui.theme.DimJusticePurple
import com.example.ui.theme.DimLoyaltyGreen
import com.example.ui.theme.DimRestraintIndigo
import com.example.ui.theme.DimTruthBlue
import com.example.ui.theme.DimWisdomYellow

enum class EthicalDimension(
    val key: String,
    val sanskritName: String,
    val displayName: String,
    val description: String,
    val color: Color
) {
    SATYA(
        "satya",
        "Satya",
        "Truth & Integrity",
        "Uncompromising honesty, authenticity, keeping one's word under pressure",
        DimTruthBlue
    ),
    DHARMA(
        "dharma",
        "Dharma & Kartavya",
        "Duty & Righteous Action",
        "Fulfilling responsibilities to family, role, and cosmic order above selfish desire",
        DimDharmaGold
    ),
    KARUNA(
        "karuna",
        "Karuna & Maryada",
        "Compassion & Respect",
        "Empathy for the vulnerable, respecting boundaries, gentle speech, dignity for all",
        DimCompassionTeal
    ),
    NYAYA(
        "nyaya",
        "Nyaya & Samadarshana",
        "Justice & Fairness",
        "Impartial judgment, equity, protecting the innocent regardless of affiliation",
        DimJusticePurple
    ),
    SAMYAMA(
        "samyama",
        "Samyama & Jitendriya",
        "Self-Control & Restraint",
        "Mastery over anger, pride, and reactive impulses; calm dignified fortitude",
        DimRestraintIndigo
    ),
    DHAIRYA(
        "dhairya",
        "Dhairya & Parakrama",
        "Courage & Righteous Fortitude",
        "Bravery in facing hardship, taking decisive moral action without cowardice",
        DimCourageAmber
    ),
    NISHTHA(
        "nishtha",
        "Nishtha & Mitrata",
        "Loyalty & Ethical Discernment",
        "Faithfulness to righteous companions while refusing to blindly enable injustice",
        DimLoyaltyGreen
    ),
    VIVEKA(
        "viveka",
        "Viveka & Chintana",
        "Wisdom & Consequential Foresight",
        "Long-term discernment, seeing beyond immediate gains, considering downstream ripple effects",
        DimWisdomYellow
    );

    companion object {
        fun fromKey(key: String): EthicalDimension =
            entries.find { it.key.equals(key, ignoreCase = true) } ?: DHARMA
    }
}
