package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.banner

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.core.domain.enums.LoadingStatus
import com.mustafin.ebooks.mainFlow.domain.models.BannerModel

class BannerViewModel(application: Application): AndroidViewModel(application) {
    var loadingStatus by mutableStateOf(LoadingStatus.LOADING)

    var selectedBanner by mutableStateOf<BannerModel?>(null)

    fun selectBanner() {
        selectedBanner = allBanners[allBanners.indices.random()]
        loadingStatus = LoadingStatus.LOADED
    }

    private val allBanners = listOf(
        BannerModel(
            "Подпишитесь на наш телеграм канал",
            "https://google.com",
            drawable = application.getDrawable(R.drawable.telegram_icon)!!
        ),
        BannerModel(
            "Поддержите проект",
            "https://google.com",
            drawable = application.getDrawable(R.drawable.hands_shaking_icon)!!
        ),
        BannerModel(
            "Сообщите о проблеме",
            "https://google.com",
            drawable = application.getDrawable(R.drawable.bug_icon)!!
        )
    )
}