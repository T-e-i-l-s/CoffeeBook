package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT
import com.mustafin.ebooks.mainFlow.domain.models.ShortBookModel

// Ячейка с информацией о книге на главном экране
@Composable
fun BookInfoView(book: ShortBookModel, openReader: (bookId: Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(id = R.color.secondary_background))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { openReader(book.id) }
            .padding(12.dp),
    ) {
        Image(
            bitmap = book.preview.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(colorResource(id = R.color.gray)),
            contentScale = ContentScale.Crop
        )

        Spacer(
            modifier = Modifier
                .width(12.dp)
                .fillMaxHeight()
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = book.name,
                color = colorResource(id = R.color.text),
                fontWeight = FontWeight.Medium,
                fontSize = 21.sp,
                fontFamily = APP_DEFAULT_FONT,
            )
        }
    }
}