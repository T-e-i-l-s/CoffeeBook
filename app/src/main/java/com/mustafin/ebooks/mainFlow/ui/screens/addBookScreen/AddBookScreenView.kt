package com.mustafin.ebooks.mainFlow.ui.screens.addBookScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.ui.components.CustomButton
import com.mustafin.ebooks.mainFlow.ui.screens.addBookScreen.views.SelectFileButtonView

@Composable
fun AddBookScreenView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background))
            .padding(12.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_left_icon),
            contentDescription = null,
            tint = colorResource(id = R.color.text),
            modifier = Modifier
                .size(28.dp)
                .align(Alignment.TopStart)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            SelectFileButtonView {}
        }

        CustomButton(
            label = stringResource(id = R.string.add_book),
            background = colorResource(id = R.color.black),
            textColor = colorResource(id = R.color.white),
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {}
    }
}