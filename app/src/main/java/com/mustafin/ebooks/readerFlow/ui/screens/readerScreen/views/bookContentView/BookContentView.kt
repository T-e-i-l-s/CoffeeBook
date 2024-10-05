package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.bookContentView

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ContextualFlowRowOverflow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT
import com.mustafin.ebooks.readerFlow.domain.models.BookModel

// View блока с текстом книги
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BookContentView(book: BookModel) {
    val viewModel: BookContentViewModel =
        viewModel(factory = BookContentViewModelFactory(book.content))

    ContextualFlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentHeight(align = Alignment.Top)
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    if (delta < 0) {
                        viewModel.openNextPage()
                    } else {
                        viewModel.openPreviousPage()
                    }
                }
            ),
        overflow = ContextualFlowRowOverflow.Clip,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        itemCount = 500 // Максимальное количество слов на страницы(увы, пока константа)
    ) { index ->
        var isShown by remember { mutableStateOf(true) }

        if (isShown && index < viewModel.currentPageContent.size) {
            if (viewModel.currentPageContent[index] == "\n") {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                )
            } else {
                Text(
                    text = viewModel.currentPageContent[index],
                    color = colorResource(id = R.color.text),
                    fontSize = 18.sp,
                    fontFamily = APP_DEFAULT_FONT,
                    modifier = Modifier.onGloballyPositioned {
                        val parentCoordinates = it.parentLayoutCoordinates
                        val parentHeight = parentCoordinates?.size?.height
                        viewModel.setLastWordIndex(index)
                        if (it.positionInParent().y > (parentHeight ?: Int.MAX_VALUE)) {
                            isShown = false
                        }
                    }
                )
            }
        }
    }
}