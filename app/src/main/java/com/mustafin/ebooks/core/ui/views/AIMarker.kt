package com.mustafin.ebooks.core.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT

@Composable
fun AIMarker() {
    Text(
        text = stringResource(id = R.string.by_ai),
        style = TextStyle(
            brush = Brush.horizontalGradient(
                listOf(
                    colorResource(id = R.color.ai_marker_color1),
                    colorResource(id = R.color.ai_marker_color2)
                )
            )
        ),
        fontSize = 15.sp,
        fontFamily = APP_DEFAULT_FONT,
        fontWeight = FontWeight.Thin,
        modifier = Modifier.fillMaxWidth()
    )
}