package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.bookContentView

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mustafin.ebooks.readerFlow.domain.models.BookModel
import com.mustafin.ebooks.readerFlow.domain.models.ReaderProgressModel

// View блока с текстом книги
@Composable
fun BookContentView(
    book: BookModel,
    readerProgress: ReaderProgressModel,
    modifier: Modifier,
    setReadingProgress: (Float, ReaderProgressModel) -> Unit,
    onSelectWord: (String) -> Unit,
) {
    val viewModel: BookContentViewModel = viewModel(
        factory = BookContentViewModelFactory(book)
    )

    val pagerState = rememberPagerState { viewModel.pages.size }

    LaunchedEffect(Unit) {
        if (!viewModel.restored && readerProgress.rendered.size > 1) {
            viewModel.restoreProgress(readerProgress)
            pagerState.scrollToPage(viewModel.pages.size+1)
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        var wordsSum = 0
        for (i in 0..<pagerState.currentPage) {
            wordsSum += viewModel.pages[i].size
        }
        wordsSum -= pagerState.currentPage
        setReadingProgress(
            wordsSum.toFloat() / book.content.size,
            ReaderProgressModel(
                viewModel.pages.subList(0, pagerState.currentPage+1),
                wordsSum
            )
        )
    }

    HorizontalPager(
        modifier = modifier,
        state = pagerState
    ) {
        ContentFlowRow(
            currentPageContent = viewModel.pages[it],
            setLastWordIndex = { index ->
                if (it == viewModel.pages.size - 1) {
                    viewModel.setLastWordIndex(index)
                }
            },
            onSelectWord = onSelectWord
        )
    }
}