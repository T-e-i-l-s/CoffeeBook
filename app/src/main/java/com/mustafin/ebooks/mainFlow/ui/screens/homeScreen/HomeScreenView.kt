package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.enums.LoadingStatus
import com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.BookInfoView

// Главный экран приложения
@Composable
fun HomeScreenView() {
    val viewModel: HomeScreenViewModel = hiltViewModel()

    if (viewModel.loadingStatus == LoadingStatus.LOADED) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background))
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { 
                Spacer(modifier = Modifier.statusBarsPadding())
            }

            items(viewModel.books) {
                BookInfoView(book = it)
            }

            item {
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        }
    }
}