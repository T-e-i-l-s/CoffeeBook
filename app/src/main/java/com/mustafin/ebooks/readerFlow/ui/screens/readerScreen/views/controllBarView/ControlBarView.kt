package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.controllBarView

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT
import java.util.Locale

// View блока с информацией снизу читалки
@Composable
fun ControlBarView(bookName: String, progress: Float, openMenu: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { openMenu() }
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "${String.format(Locale.US, "%.1f", progress * 100)}%",
                color = colorResource(id = R.color.gray),
                fontSize = 12.sp,
                fontFamily = APP_DEFAULT_FONT
            )
            Text(
                text = bookName,
                color = colorResource(id = R.color.gray),
                fontSize = 12.sp,
                fontFamily = APP_DEFAULT_FONT
            )
        }

        Text(
            text = stringResource(id = R.string.menu),
            color = colorResource(id = R.color.gray),
            fontSize = 15.sp,
            fontFamily = APP_DEFAULT_FONT,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}
