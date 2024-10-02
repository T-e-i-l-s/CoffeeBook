package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.addBookSheet.AddBookBottomSheetView
import com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.addBookSheet.AddBookViewModel
import com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.banner.BannerView
import com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.booksView.BooksView

// Главный экран приложения
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenView(openReader: (bookId: Int) -> Unit) {
    val viewModel: HomeScreenViewModel = hiltViewModel()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background)),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Spacer(modifier = Modifier.statusBarsPadding())

            BooksView(
                loadingStatus = viewModel.loadingStatus,
                books = viewModel.books,
                openAddBookSheet = { viewModel.openAddBookSheet() },
                openReader = openReader
            )

            Spacer(modifier = Modifier.height(12.dp))

            BannerView(Modifier.padding(horizontal = 12.dp))
        }

        item {
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }

    if (viewModel.isAddBookSheetOpened) {
        // Всплывающее снизу модальное окно для импорта книги

        // ViewModel этого всплывающего окна
        val addBookViewModel: AddBookViewModel = hiltViewModel()

        ModalBottomSheet(
            onDismissRequest = {
                viewModel.closeAddBookSheet()
                addBookViewModel.resetState()
            },
            containerColor = colorResource(id = R.color.background),
            windowInsets = WindowInsets(0, 0, 0, 0)
        ) {
            AddBookBottomSheetView { viewModel.loadData() }
        }
    }
}