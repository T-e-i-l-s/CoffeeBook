package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT
import com.mustafin.ebooks.core.domain.enums.LoadingStatus
import com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.bookContentView.BookContentView
import com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.controllBarView.ControlBarView
import com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.menuView.MenuView
import com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.wordMeaningView.WordMeaningView

// View экрана читалки
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderScreenView(bookId: Int) {
    val viewModel: ReaderScreenViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(colorResource(id = R.color.background)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.loadingStatus == LoadingStatus.LOADED) {
            var readingProgress by remember { mutableFloatStateOf(0f) }

            BookContentView(
                viewModel.book,
                Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                setReadingProgress = { readingProgress = it },
                onSelectWord = { viewModel.showWordMeaning(it) }
            )

            ControlBarView(viewModel.book.name, readingProgress) { viewModel.showMenu = true }

            if (viewModel.showMenu) {
                ModalBottomSheet(
                    onDismissRequest = {
                        viewModel.showMenu = false
                    },
                    containerColor = colorResource(id = R.color.background),
                    windowInsets = WindowInsets(0, 0, 0, 0)
                ) {
                    MenuView(book = viewModel.book, progress = readingProgress)
                }
            }

            if (viewModel.showWordMeaning) {
                ModalBottomSheet(
                    onDismissRequest = {
                        viewModel.resetSelection()
                    },
                    containerColor = colorResource(id = R.color.background),
                    windowInsets = WindowInsets(0, 0, 0, 0)
                ) {
                    WordMeaningView(viewModel.selectedWord!!)
                }
            }
        } else if (viewModel.loadingStatus == LoadingStatus.LOADING) {
            Text(
                text = stringResource(id = R.string.book_processing),
                color = colorResource(id = R.color.text),
                fontSize = 21.sp,
                fontFamily = APP_DEFAULT_FONT
            )
        }
    }
}