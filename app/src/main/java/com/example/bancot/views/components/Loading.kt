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
fun LoadingSkelton(modifier: Modifier = Modifier) { // ⚠️ O retorno deve ser Unit, não Modifier
    val transition = rememberInfiniteTransition(label = "ShimmerTransition")

    val transitionAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f, // ⭐️ CORREÇÃO 1: Aumente o targetValue para cobrir a largura da tela (1000f é seguro)
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "ShimmerTranslate"
    )

    // 3. Define o pincel (Brush) para o gradiente
    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(x = transitionAnimation - 1000f, y = 0f), // ⭐️ CORREÇÃO 2: Ajuste o início com base no novo target
        end = Offset(x = transitionAnimation, y = transitionAnimation),
    )

    // 4. Aplica o background dentro de um Box para renderizar visualmente.
    Box(
        modifier = Modifier
            .background(brush = brush)
            .then(modifier) // Aplica as dimensões (width, height) passadas de PaymentSkeletonItem
    )
}