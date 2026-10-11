package com.example.ui.hologram

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AntiqueGold
import com.example.ui.theme.DivyaGoldPrimary
import com.example.ui.theme.GoldenAmber
import com.example.ui.theme.LuminousGoldLight
import com.example.ui.theme.WarmGlowOrange
import kotlin.math.sin

/**
 * Lord Ram Hologram modeled after the backlit silhouette sculpture reference:
 * - Jet black heroic warrior silhouette with topknot hair and athletic stance
 * - Flowing cape / uttariya billowing dynamically to the left
 * - Tall Kodanda bow with recurve flared tips in left hand, taut bowstring
 * - Radiant amber-gold backlighting forming a blazing divine halo/corona around the silhouette
 * - Percentage-controlled fill: 0% dim outline -> 100% fully blazing divine golden backlight
 */
@Composable
fun LordRamHologram(
    targetPercentage: Int, // 0..100
    modifier: Modifier = Modifier,
    height: Dp = 380.dp,
    showScoreLabel: Boolean = true,
    animationDurationMs: Int = 2000
) {
    val clampedPercentage = targetPercentage.coerceIn(0, 100)
    val targetFraction = clampedPercentage / 100f

    val animatedFill = remember { Animatable(0f) }
    LaunchedEffect(clampedPercentage) {
        animatedFill.animateTo(
            targetValue = targetFraction,
            animationSpec = tween(
                durationMillis = animationDurationMs,
                easing = FastOutSlowInEasing
            )
        )
    }

    val infiniteTransition = rememberInfiniteTransition(label = "hologram_backlight")
    val pulseGlow by infiniteTransition.animateFloat(
        initialValue = 0.90f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(2400, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_glow"
    )
    val particlePhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "particle_phase"
    )

    Column(
        modifier = modifier.testTag("lord_ram_hologram_container"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(height)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("lord_ram_hologram_canvas")
            ) {
                drawBacklitLordRam(
                    fillFraction = animatedFill.value,
                    pulse = pulseGlow,
                    particlePhase = particlePhase
                )
            }
        }

        if (showScoreLabel) {
            Spacer(modifier = Modifier.height(8.dp))
            val currentDisplayedPercent = (animatedFill.value * 100).toInt()
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = DivyaGoldPrimary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "$currentDisplayedPercent% MARYADA ALIGNMENT",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = DivyaGoldPrimary,
                    letterSpacing = 1.5.sp
                )
            }
        }
    }
}

private fun DrawScope.drawBacklitLordRam(
    fillFraction: Float,
    pulse: Float,
    particlePhase: Float
) {
    val canvasW = size.width
    val canvasH = size.height

    // Coordinate mapping for normalized [0..1000, 0..1400]
    val figureH = canvasH * 0.90f
    val figureW = figureH * 0.72f
    val startX = (canvasW - figureW) / 2f
    val startY = canvasH * 0.05f

    val scaleX = figureW / 1000f
    val scaleY = figureH / 1400f

    fun px(x: Float) = startX + x * scaleX
    fun py(y: Float) = startY + y * scaleY

    // 1. Build the Heroic Silhouette matching the user's reference image:
    // - Topknot (Juda), powerful torso, right arm resting at side
    // - Dramatic billowing cape flying to the left
    // - Left arm raised holding Kodanda bow with flared recurve tips
    val ramBodyPath = Path().apply {
        // Start at feet
        moveTo(px(530f), py(1340f))
        // Left foot (heroic stance)
        lineTo(px(470f), py(1345f))
        quadraticTo(px(460f), py(1310f), px(490f), py(1250f))
        // Left calf and pleated dhoti flare
        quadraticTo(px(480f), py(1140f), px(520f), py(1060f))
        quadraticTo(px(470f), py(970f), px(510f), py(850f))

        // Billowing cape lower edge (flaring wide to the left)
        quadraticTo(px(430f), py(850f), px(350f), py(890f))
        quadraticTo(px(310f), py(880f), px(320f), py(850f))
        quadraticTo(px(250f), py(800f), px(170f), py(770f))
        quadraticTo(px(120f), py(770f), px(110f), py(745f)) // Cape tail tip
        quadraticTo(px(150f), py(730f), px(230f), py(720f))
        quadraticTo(px(320f), py(680f), px(400f), py(600f))

        // Left arm raised holding bow
        lineTo(px(380f), py(560f))
        quadraticTo(px(370f), py(510f), px(390f), py(460f)) // Left forearm grip
        lineTo(px(420f), py(430f))
        quadraticTo(px(460f), py(400f), px(490f), py(360f)) // Left shoulder

        // Neck and flowing hair strand blowing left
        quadraticTo(px(510f), py(320f), px(500f), py(290f))
        quadraticTo(px(460f), py(310f), px(440f), py(315f))
        quadraticTo(px(470f), py(285f), px(515f), py(275f))

        // Head and Topknot (Juda)
        quadraticTo(px(530f), py(220f), px(560f), py(170f))
        // Topknot round bun
        quadraticTo(px(585f), py(140f), px(605f), py(165f))
        quadraticTo(px(615f), py(190f), px(610f), py(220f))
        // Right face and chin
        quadraticTo(px(635f), py(260f), px(625f), py(300f))

        // Powerful right shoulder
        quadraticTo(px(670f), py(330f), px(710f), py(380f))
        // Right bicep and tricep
        quadraticTo(px(740f), py(460f), px(735f), py(540f))
        // Right forearm and hand resting at side
        quadraticTo(px(720f), py(620f), px(710f), py(700f))
        lineTo(px(700f), py(740f)) // Fingers
        quadraticTo(px(685f), py(730f), px(690f), py(680f))
        quadraticTo(px(670f), py(610f), px(660f), py(540f))

        // Right torso and waist
        quadraticTo(px(645f), py(640f), px(635f), py(750f))
        // Right leg and pleated dhoti
        quadraticTo(px(660f), py(850f), px(675f), py(970f))
        quadraticTo(px(690f), py(1090f), px(660f), py(1200f))
        // Right foot
        lineTo(px(670f), py(1300f))
        lineTo(px(690f), py(1345f))
        lineTo(px(640f), py(1340f))

        // Inseam between legs
        quadraticTo(px(600f), py(1250f), px(580f), py(1050f))
        quadraticTo(px(565f), py(1250f), px(530f), py(1340f))
        close()
    }

    // Kodanda Bow Path with the flared recurve tips (left side of Rama)
    val bowPath = Path().apply {
        // Top flared tip pointing up-left
        moveTo(px(460f), py(170f))
        quadraticTo(px(470f), py(210f), px(445f), py(270f))
        // Bow upper limb curving majestically
        cubicTo(
            px(380f), py(360f),
            px(360f), py(480f),
            px(375f), py(580f) // Grip point
        )
        // Bow lower limb curving downward
        cubicTo(
            px(340f), py(720f),
            px(340f), py(960f),
            px(410f), py(1120f)
        )
        // Bottom flared tip pointing down-left
        quadraticTo(px(440f), py(1180f), px(435f), py(1220f))
        lineTo(px(415f), py(1210f))
    }

    // Bowstring Path
    val bowstringPath = Path().apply {
        moveTo(px(460f), py(170f))
        lineTo(px(435f), py(1220f))
    }

    // 2. LAYER A: BACKLIGHT CORONA & HALO GLOW
    // When illuminated by percentage, a blazing warm amber halo shines from behind the silhouette
    val bottomY = startY + figureH * 1.05f
    val topY = startY - figureH * 0.05f
    val currentFillCutoffY = bottomY - (bottomY - topY) * fillFraction

    // Ambient back aura (subtle when 0%, blazing when filled)
    val auraAlpha = 0.15f + (0.75f * fillFraction * pulse)
    val haloCenter = Offset(canvasW * 0.52f, startY + figureH * 0.45f)

    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(
                GoldenAmber.copy(alpha = 0.45f * auraAlpha),
                WarmGlowOrange.copy(alpha = 0.25f * auraAlpha),
                DivyaGoldPrimary.copy(alpha = 0.10f * auraAlpha),
                Color.Transparent
            ),
            center = haloCenter,
            radius = figureW * 0.75f
        ),
        center = haloCenter,
        radius = figureW * 0.75f
    )

    // Clip the illuminated golden backlight halo from bottom to top
    if (fillFraction > 0.001f) {
        clipRect(
            left = 0f,
            top = currentFillCutoffY,
            right = canvasW,
            bottom = canvasH
        ) {
            // Blazing backlighting rim around body path (thick luminous amber stroke behind)
            drawPath(
                path = ramBodyPath,
                color = WarmGlowOrange.copy(alpha = 0.90f * pulse),
                style = Stroke(width = 24.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
            )
            drawPath(
                path = ramBodyPath,
                color = GoldenAmber.copy(alpha = 0.95f),
                style = Stroke(width = 14.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
            )
            drawPath(
                path = ramBodyPath,
                color = LuminousGoldLight.copy(alpha = 1.0f),
                style = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
            )

            // Blazing bow backlight stroke
            drawPath(
                path = bowPath,
                color = WarmGlowOrange.copy(alpha = 0.85f * pulse),
                style = Stroke(width = 16.dp.toPx(), cap = StrokeCap.Round)
            )
            drawPath(
                path = bowPath,
                color = GoldenAmber.copy(alpha = 0.95f),
                style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
            )
            drawPath(
                path = bowPath,
                color = LuminousGoldLight.copy(alpha = 1.0f),
                style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
            )

            // Radiant bowstring
            drawPath(
                path = bowstringPath,
                color = LuminousGoldLight.copy(alpha = 0.95f),
                style = Stroke(width = 1.6.dp.toPx(), cap = StrokeCap.Round)
            )
        }

        // Active energy wavefront blade at the current illumination cutoff
        if (fillFraction in 0.02f..0.98f) {
            val beamY = currentFillCutoffY
            val beamStartX = startX - figureW * 0.1f
            val beamEndX = startX + figureW * 1.1f

            drawLine(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Transparent,
                        WarmGlowOrange.copy(alpha = 0.6f),
                        LuminousGoldLight,
                        DivyaGoldPrimary,
                        LuminousGoldLight,
                        WarmGlowOrange.copy(alpha = 0.6f),
                        Color.Transparent
                    ),
                    startX = beamStartX,
                    endX = beamEndX
                ),
                start = Offset(beamStartX, beamY),
                end = Offset(beamEndX, beamY),
                strokeWidth = 3.5.dp.toPx(),
                cap = StrokeCap.Round
            )

            drawCircle(
                color = LuminousGoldLight.copy(alpha = 0.9f * pulse),
                center = Offset(canvasW / 2f, beamY),
                radius = 18.dp.toPx()
            )
        }
    }

    // 3. LAYER B: SOLID JET BLACK FOREGROUND SILHOUETTE (Exact reference style)
    // The foreground figure is deep matte black, making the backlight blaze around it
    drawPath(
        path = ramBodyPath,
        color = Color(0xFF09090D)
    )

    // Solid black Kodanda bow body
    drawPath(
        path = bowPath,
        color = Color(0xFF09090D),
        style = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Round)
    )

    // Subtle edge rim outline for dark silhouette visibility when fill is low
    drawPath(
        path = ramBodyPath,
        color = AntiqueGold.copy(alpha = 0.25f + 0.25f * fillFraction),
        style = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round, join = StrokeJoin.Round)
    )
    drawPath(
        path = bowPath,
        color = AntiqueGold.copy(alpha = 0.25f + 0.25f * fillFraction),
        style = Stroke(width = 1.2.dp.toPx(), cap = StrokeCap.Round)
    )

    // 4. DIVINE FLOATING PRANA PARTICLES
    val particleCount = 18
    for (i in 0 until particleCount) {
        val seed = i * 53.7f
        val particleX = startX + (sin(seed + particlePhase * 0.05f) * 0.5f + 0.5f) * figureW
        val rawY = ((seed * 23.1f + particlePhase * 2.4f) % figureH)
        val particleY = startY + figureH - rawY

        val isIlluminated = if (fillFraction > 0.01f) {
            particleY >= currentFillCutoffY
        } else false

        val pAlpha = if (isIlluminated) 0.85f * pulse else 0.15f
        val pRadius = if (isIlluminated) 2.2f else 1.2f

        drawCircle(
            color = if (isIlluminated) LuminousGoldLight.copy(alpha = pAlpha)
                    else AntiqueGold.copy(alpha = pAlpha),
            center = Offset(particleX, particleY),
            radius = pRadius
        )
    }
}
