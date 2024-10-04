package com.mustafin.ebooks.readerFlow.ui.screens.readerScreen

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafin.ebooks.core.domain.enums.LoadingStatus

@Composable
fun ReaderScreenView(bookId: Int) {
    val viewModel: ReaderScreenViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    if (viewModel.loadingStatus == LoadingStatus.LOADED) {
        // Создаём аннотированную строку
        val annotatedString = buildAnnotatedString {
            viewModel.book.content.forEach { word ->
                pushStringAnnotation(tag = "WORD", annotation = word)
                append("$word ")
                pop()
            }
        }

        LazyColumn {
            item {
                ClickableText(
                    text = annotatedString,
                    onClick = { offset ->
                        // Получаем аннотацию, связанную с нажатым словом
                        annotatedString.getStringAnnotations(
                            tag = "WORD",
                            start = offset,
                            end = offset
                        )
                            .firstOrNull()?.let { annotation ->
                                println("Нажато слово: ${annotation.item}")
                            }
                    },
                    modifier = Modifier.statusBarsPadding()
                )
            }
        }
    }
}