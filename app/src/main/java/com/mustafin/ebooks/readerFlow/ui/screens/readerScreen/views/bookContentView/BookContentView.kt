package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.bookContentView

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mustafin.ebooks.readerFlow.domain.models.BookModel

// View блока с текстом книги
@Composable
fun BookContentView(
    book: BookModel,
    modifier: Modifier,
    setReadingProgress: (Float) -> Unit
) {
    val viewModel: BookContentViewModel =
        viewModel(factory = BookContentViewModelFactory(book.content))

    val pagerState = rememberPagerState { viewModel.pages.size }

    LaunchedEffect(pagerState.currentPage) {
        setReadingProgress(
            viewModel.firstWordsOfAllPages[pagerState.currentPage].toFloat() / book.content.size
        )
    }

    HorizontalPager(
        modifier = modifier,
        state = pagerState
    ) {
        ContentFlowRow(currentPageContent = viewModel.pages[it]) { index ->
            if (it == viewModel.pages.size - 1) {
                viewModel.setLastWordIndex(index)
            }
        }
    }
}