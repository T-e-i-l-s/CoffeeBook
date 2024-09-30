package com.mustafin.ebooks.core.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomProgressIndicator(
    color: Color,
    size: Dp
) {
    CircularProgressIndicator(
        modifier = Modifier.size(size),
        color = color,
        trackColor = Color.Transparent,
        strokeWidth = 2.dp
    )
}