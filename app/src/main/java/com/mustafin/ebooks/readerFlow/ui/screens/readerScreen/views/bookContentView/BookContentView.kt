package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.bookContentView

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mustafin.ebooks.readerFlow.domain.models.BookModel

// View блока с текстом книги
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BookContentView(book: BookModel) {
    val viewModel: BookContentViewModel =
        viewModel(factory = BookContentViewModelFactory(book.content))

    HorizontalPager(state = rememberPagerState { viewModel.pages.size }) {
        ContentFlowRow(currentPageContent = viewModel.pages[it]) { index ->
            viewModel.setLastWordIndex(index)
        }
    }
}