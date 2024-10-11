package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.bookContentView

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mustafin.ebooks.readerFlow.domain.models.BookModel

// View блока с текстом книги
@Composable
fun BookContentView(book: BookModel) {
    val viewModel: BookContentViewModel =
        viewModel(factory = BookContentViewModelFactory(book.content))

    val pagerState = rememberPagerState { viewModel.pages.size }

    LaunchedEffect(viewModel.currentPageIndex) {
        println("New Page ${viewModel.currentPageIndex}")
        pagerState.animateScrollToPage(2)
    }

    LaunchedEffect(Unit) {
        println("Restore To ${viewModel.firstWordIndex}")
        viewModel.openPageWithWord(viewModel.firstWordIndex)
    }

    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        state = pagerState
    ) {
        ContentFlowRow(currentPageContent = viewModel.pages[it]) { index ->
            if (it == viewModel.pages.size - 1) {
                if (!viewModel.isRestoring) {
                    viewModel.setLastWordIndex(index)
                } else {
                    viewModel.restoreLastWordIndex(index)
                }
            }
        }
    }
}