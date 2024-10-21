package com.mustafin.ebooks.core.ui

import android.app.Application
import com.mustafin.ebooks.core.domain.APP_METRICA_KEY
import dagger.hilt.android.HiltAndroidApp
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig

@HiltAndroidApp
class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        // Подключение к AppMetrica
        val config = AppMetricaConfig.newConfigBuilder(APP_METRICA_KEY).build()
        AppMetrica.activate(this, config)
    }
}