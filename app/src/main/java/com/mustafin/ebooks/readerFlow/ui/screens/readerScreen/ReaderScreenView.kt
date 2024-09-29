package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT
import com.mustafin.ebooks.core.domain.enums.LoadingStatus

@Composable
fun ReaderScreenView(bookId: Int) {
    val viewModel: ReaderScreenViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    if (viewModel.loadingStatus == LoadingStatus.LOADED) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background))
        ) {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = viewModel.book.content,
                    fontSize = viewModel.readerSettings?.fontSize ?: 18.sp,
                    fontFamily = APP_DEFAULT_FONT,
                    color = colorResource(id = R.color.text),
                    softWrap = false,
                )
            }
        }
    }
}