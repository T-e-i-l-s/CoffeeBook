package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen.views.bookContentView

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ContextualFlowRowOverflow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContentFlowRow(
    currentPageContent: List<String>,
    setLastWordIndex: (Int) -> Unit,
) {
    val isLastWordSelected = remember { mutableStateOf(false) }
    val parentHeight = remember { mutableStateOf<Int?>(null) }

    ContextualFlowRow(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .wrapContentHeight(align = Alignment.Top)
            .onGloballyPositioned {
                val parentCoordinates = it.parentLayoutCoordinates
                parentHeight.value = parentCoordinates?.size?.height
            },
        overflow = ContextualFlowRowOverflow.Clip,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        itemCount = 500 // Максимальное количество слов на страницы(увы, пока константа)
    ) { index ->
        var isShown by remember { mutableStateOf(true) }

        if (isShown && index < currentPageContent.size) {
            if (currentPageContent[index] == "\n") {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                )
            } else {
                // FIXME: Исправить порядок рендера слов, иногда они обрезаются
                Text(
                    text = currentPageContent[index],
                    color = colorResource(id = R.color.text),
                    fontSize = 18.sp,
                    fontFamily = APP_DEFAULT_FONT,
                    modifier = Modifier.onGloballyPositioned {
                        if (
                            // FIXME: Сдлеать чтобы расчет высоы шел красиво
                            it.positionInParent().y + 200 >
                            (parentHeight.value ?: Int.MAX_VALUE)
                        ) {
                            if (!isLastWordSelected.value) {
                                setLastWordIndex(index)
                                isLastWordSelected.value = true
                            }
                            isShown = false
                        }
                    }
                )
            }
        }
    }
}