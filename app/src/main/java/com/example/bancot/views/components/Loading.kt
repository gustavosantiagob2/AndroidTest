package com.example.bancot.views.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val ShimmerColorShades = listOf(
    Color.LightGray.copy(0.9f),
    Color.LightGray.copy(0.2f),
    Color.LightGray.copy(0.9f),
)

@Composable
fun LoadingSkeleton(modifier: Modifier = Modifier) {

    val transition = rememberInfiniteTransition(label = "ShimmerTransition")

    val transitionAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "ShimmerTranslate"
    )

    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(x = transitionAnimation - 1000f, y = 0f),
        end = Offset(x = transitionAnimation, y = transitionAnimation),
    )

    Box(
        modifier = Modifier
            .background(brush = brush)
            .then(modifier)
    )
}