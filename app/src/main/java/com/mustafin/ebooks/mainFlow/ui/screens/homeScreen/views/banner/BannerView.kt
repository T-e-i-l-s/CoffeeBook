package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT

// View баннера на главном экране
@Composable
fun BannerView(modifier: Modifier) {
    val viewModel: BannerViewModel = viewModel()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(
                        colorResource(id = R.color.promo_banner_gradient_start),
                        colorResource(id = R.color.promo_banner_gradient_end)
                    )
                )
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = viewModel.selectedBanner.title,
            color = colorResource(id = R.color.background),
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            fontFamily = APP_DEFAULT_FONT,
            modifier = Modifier.weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.arrow_right_icon),
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = colorResource(id = R.color.background),
        )
    }
}