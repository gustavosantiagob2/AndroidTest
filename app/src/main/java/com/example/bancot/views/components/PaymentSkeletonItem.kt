package com.example.bancot.views.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun PaymentSkeletonItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        LoadingSkeleton(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        LoadingSkeleton(
            modifier = Modifier
                .width(150.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}