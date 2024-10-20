package com.mustafin.ebooks.mainFlow.ui.screens.allBooksScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.mainFlow.ui.views.BookInfoView

// View экрана с полным списком книг
@Composable
fun AllBooksScreenView(popBackNavigationStack: () -> Unit, openReader: (bookId: Int) -> Unit) {
    val viewModel: AllBooksScreenViewModel = hiltViewModel()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Spacer(modifier = Modifier.statusBarsPadding())

            Icon(
                painter = painterResource(id = R.drawable.arrow_left_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(27.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { popBackNavigationStack() },
                tint = colorResource(id = R.color.text)
            )
        }

        items(viewModel.books) {
            BookInfoView(book = it, openReader = openReader)
        }

        item {
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}