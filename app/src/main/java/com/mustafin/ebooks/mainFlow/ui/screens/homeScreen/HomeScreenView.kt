package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.mustafin.ebooks.mainFlow.ui.screens.addBookSheet.AddBookBottomSheetView
import com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.BookInfoView

// Главный экран приложения
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenView() {
    val viewModel: HomeScreenViewModel = hiltViewModel()

    if (viewModel.loadingStatus == LoadingStatus.LOADED) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.background))
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Spacer(modifier = Modifier.statusBarsPadding())

                Row(
                    modifier = Modifier.fillMaxWidth(),
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
                        painter = painterResource(id = R.drawable.plus_icon),
                        contentDescription = null,
                        tint = colorResource(id = R.color.text),
                        modifier = Modifier
                            .size(28.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) { viewModel.openAddBookSheet() }
                    )
                }
            }

            items(viewModel.books) {
                BookInfoView(book = it)
            }

            item {
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        }
    }
    
    if (viewModel.isAddBookSheetOpened) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.closeAddBookSheet()},
            containerColor = colorResource(id = R.color.background),
            windowInsets = WindowInsets(0, 0, 0, 0)
        ) {
            AddBookBottomSheetView { viewModel.closeAddBookSheet() }
        }
    }
}