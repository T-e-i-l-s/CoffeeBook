package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.addBookSheet

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT
import com.mustafin.ebooks.core.ui.components.CustomButton

@Composable
fun AddBookBottomSheetView(closeSheet: () -> Unit) {
    val viewModel: AddBookViewModel = viewModel()

    // Лаунчер для выбора файла
    val selectFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            // Обработка выбранного файла
            viewModel.onFileSelected(uri)
        }
    }

    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.background))
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.End
    ) {
        SelectFileButtonView {
            selectFileLauncher.launch(arrayOf("application/pdf"))
        }

        if (viewModel.isSelected) {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = viewModel.selectedFileName!!,
                color = colorResource(id = R.color.text),
                fontSize = 15.sp,
                fontFamily = APP_DEFAULT_FONT,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        CustomButton(
            label = stringResource(id = R.string.add_book),
            background = colorResource(id = R.color.text),
            textColor = colorResource(id = R.color.background),
            enabled = viewModel.isSelected,
            modifier = Modifier.fillMaxWidth()
        ) { closeSheet() }
    }
}