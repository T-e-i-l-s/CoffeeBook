package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.statisticsView

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT
import com.mustafin.ebooks.core.domain.enums.LoadingStatus
import com.mustafin.ebooks.core.domain.extensions.mapMinutesToTimeString
import com.mustafin.ebooks.core.ui.components.CustomProgressIndicator

// View со статистикой
@Composable
fun StatisticsView() {
    val viewModel: StatisticsViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .heightIn(min = 120.dp)
            .animateContentSize()
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(id = R.color.secondary_background))
            .padding(12.dp),
        verticalArrangement = Arrangement.Center
    ) {
        if (viewModel.loadingStatus == LoadingStatus.LOADING) {
            Box(modifier = Modifier.fillMaxSize()) {
                CustomProgressIndicator(
                    color = colorResource(id = R.color.additional),
                    size = 21.dp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            Text(
                text = stringResource(id = R.string.reading_statistics),
                color = colorResource(id = R.color.text),
                fontSize = 18.sp,
                fontFamily = APP_DEFAULT_FONT,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stringResource(id = R.string.reading_time_per_day),
                color = colorResource(id = R.color.gray),
                fontSize = 15.sp,
                fontFamily = APP_DEFAULT_FONT,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = viewModel.statistics!!.readingTimeToday.mapMinutesToTimeString(),
                color = colorResource(id = R.color.text),
                fontSize = 27.sp,
                fontFamily = APP_DEFAULT_FONT,
                fontWeight = FontWeight.Normal
            )


            Text(
                text = stringResource(id = R.string.summary_reading_time),
                color = colorResource(id = R.color.gray),
                fontSize = 15.sp,
                fontFamily = APP_DEFAULT_FONT,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = viewModel.statistics!!.summaryReadingTime.mapMinutesToTimeString(),
                color = colorResource(id = R.color.text),
                fontSize = 27.sp,
                fontFamily = APP_DEFAULT_FONT,
                fontWeight = FontWeight.Normal
            )
        }
    }
}