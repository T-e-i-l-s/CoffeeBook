package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT
import com.mustafin.ebooks.core.domain.enums.LoadingStatus
import com.mustafin.ebooks.core.ui.components.CustomButton
import com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.BookInfoView
import com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.addBookSheet.AddBookBottomSheetView
import com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.addBookSheet.AddBookViewModel

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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.my_books),
                    color = colorResource(id = R.color.text),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    fontFamily = APP_DEFAULT_FONT,
                )

                Icon(
                    painter = painterResource(id = R.drawable.arrow_right_icon),
                    contentDescription = null,
                    tint = colorResource(id = R.color.gray),
                    modifier = Modifier.size(25.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (viewModel.loadingStatus == LoadingStatus.LOADED) {
                val pagerState = rememberPagerState(pageCount = { viewModel.books.size })
                HorizontalPager(
                    modifier = Modifier.fillMaxWidth(),
                    pageSize = PageSize.Fixed(330.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp),
                    pageSpacing = 12.dp,
                    state = pagerState
                ) {
                    BookInfoView(book = viewModel.books[it], openReader = openReader)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            CustomButton(
                label = stringResource(id = R.string.add_book),
                background = colorResource(id = R.color.text),
                textColor = colorResource(id = R.color.background),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) { viewModel.openAddBookSheet() }
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
                viewModel.loadData()
                addBookViewModel.resetState()
            },
            containerColor = colorResource(id = R.color.background),
            windowInsets = WindowInsets(0, 0, 0, 0)
        ) {
            AddBookBottomSheetView()
        }
    }
}