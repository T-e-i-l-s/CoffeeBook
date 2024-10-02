package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT
import com.mustafin.ebooks.core.domain.enums.LoadingStatus

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReaderScreenView(bookId: Int) {
    val viewModel: ReaderScreenViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    if (viewModel.loadingStatus == LoadingStatus.LOADED) {
        ContextualFlowRow(
            modifier = Modifier
                .safeDrawingPadding()
                .fillMaxWidth(1f)
                .padding(16.dp)
                .wrapContentHeight(align = Alignment.Top)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            itemCount = 1000
        ) { index ->
            Text(
                text = viewModel.book.content[index],
                color = colorResource(id = R.color.text),
                fontSize = 18.sp,
                fontFamily = APP_DEFAULT_FONT
            )
        }
    }
}