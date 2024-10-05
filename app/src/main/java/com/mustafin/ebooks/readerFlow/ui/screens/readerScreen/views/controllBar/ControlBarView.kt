package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.controllBar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT

@Composable
fun ControlBarView() {
    Box(
        Modifier
            .fillMaxSize()
            .height(36.dp)
            .padding(horizontal = 12.dp, vertical = 5.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "Евгений Онегин",
                color = colorResource(id = R.color.gray),
                fontSize = 13.sp,
                fontFamily = APP_DEFAULT_FONT
            )
            Text(
                text = "23 / 357",
                color = colorResource(id = R.color.gray),
                fontSize = 13.sp,
                fontFamily = APP_DEFAULT_FONT
            )
        }

        Text(
            text = "Меню",
            color = colorResource(id = R.color.gray),
            fontSize = 16.sp,
            fontFamily = APP_DEFAULT_FONT,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}
