package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT
import com.mustafin.ebooks.core.domain.enums.LoadingStatus
import com.mustafin.ebooks.core.ui.components.CustomProgressIndicator

// View баннера на главном экране
@Composable
fun BannerView(modifier: Modifier) {
    val viewModel: BannerViewModel = viewModel()

    LaunchedEffect(Unit) {
        viewModel.selectBanner()
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(id = R.color.secondary_background))
            .padding(12.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        if (viewModel.loadingStatus == LoadingStatus.LOADED) {
            Text(
                text = viewModel.selectedBanner!!.title,
                color = colorResource(id = R.color.text),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 21.sp,
                fontFamily = APP_DEFAULT_FONT,
                modifier = Modifier.weight(1f)
            )
            
            Spacer(modifier = Modifier.width(12.dp))

            Icon(
                bitmap = viewModel.selectedBanner!!.drawable.toBitmap().asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = colorResource(id = R.color.text)
            )
        } else if (viewModel.loadingStatus == LoadingStatus.LOADING) {
            CustomProgressIndicator(color = colorResource(id = R.color.text), size = 21.dp)
        }
    }
}