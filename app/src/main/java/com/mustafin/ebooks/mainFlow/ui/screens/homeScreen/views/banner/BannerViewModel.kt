package com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.views.banner

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.mustafin.ebooks.R
import com.mustafin.ebooks.mainFlow.domain.models.BannerModel

class BannerViewModel(application: Application) : AndroidViewModel(application) {
    var selectedBanner by mutableStateOf(
        BannerModel(
            "Поддержите проект",
            "https://google.com",
            drawable = application.getDrawable(R.drawable.hands_shaking_icon)!!
        )
    )
}