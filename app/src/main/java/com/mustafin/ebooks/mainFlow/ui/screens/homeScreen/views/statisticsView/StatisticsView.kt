package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.statisticsView

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.APP_DEFAULT_FONT
import com.mustafin.ebooks.core.domain.enums.LoadingStatus
import com.mustafin.ebooks.core.ui.components.CustomProgressIndicator
import kotlin.math.roundToInt

@Composable
fun StatisticsView() {
    val viewModel: StatisticsViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.loadStatistics()
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .animateContentSize()
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(id = R.color.secondary_background))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (viewModel.loadingStatus == LoadingStatus.LOADING) {
            CustomProgressIndicator(
                color = colorResource(id = R.color.additional),
                size = 21.dp,
            )
        } else {
            Box {
                CircularProgressIndicator(
                    progress = { viewModel.statistics!!.averageProgress },
                    color = colorResource(id = R.color.additional),
                    trackColor = colorResource(id = R.color.background),
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier.size(150.dp),
                    strokeWidth = 6.dp
                )
                Text(
                    text = "${(viewModel.statistics!!.averageProgress * 100).roundToInt()}%",
                    color = colorResource(id = R.color.additional),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 36.sp,
                    fontFamily = APP_DEFAULT_FONT,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(id = R.string.average_progress),
                color = colorResource(id = R.color.text),
                fontSize = 18.sp,
                fontFamily = APP_DEFAULT_FONT,
                fontWeight = FontWeight.Normal
            )
        }
    }
}