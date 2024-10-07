package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.bookContentView

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mustafin.ebooks.readerFlow.domain.models.BookModel

// View блока с текстом книги
@Composable
fun BookContentView(book: BookModel) {
    val viewModel: BookContentViewModel =
        viewModel(factory = BookContentViewModelFactory(book.content))

    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = rememberPagerState { viewModel.pages.size }
    ) {
        ContentFlowRow(currentPageContent = viewModel.pages[it]) { index ->
            if (it == viewModel.pages.size-1) {
                viewModel.setLastWordIndex(index)
            }
        }
    }
}