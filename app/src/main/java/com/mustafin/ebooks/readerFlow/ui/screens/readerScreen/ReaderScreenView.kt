package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT
import com.mustafin.ebooks.core.domain.enums.LoadingStatus
import com.mustafin.ebooks.core.ui.components.CustomProgressIndicator
import com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.bookContentView.BookContentView
import com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.controllBar.ControlBarView

// View экрана читалки
@Composable
fun ReaderScreenView(bookId: Int) {
    val viewModel: ReaderScreenViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
            .background(colorResource(id = R.color.background)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.loadingStatus == LoadingStatus.LOADED) {
            BookContentView(viewModel.book)
            ControlBarView()
        } else if (viewModel.loadingStatus == LoadingStatus.LOADING) {
            CustomProgressIndicator(color = colorResource(id = R.color.text), size = 24.dp)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.book_processing),
                color = colorResource(id = R.color.text),
                fontSize = 21.sp,
                fontFamily = APP_DEFAULT_FONT
            )
        }
    }
}