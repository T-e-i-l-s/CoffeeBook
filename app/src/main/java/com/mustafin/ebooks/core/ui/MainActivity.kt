package com.mustafin.ebooks.core.ui

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mustafin.ebooks.core.ui.navigation.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        Запрещаем горизонтальное положение экрана
        Это необходимо при текущем подходе к реализации ридера.
        При повороте экрана текст в ридере может отображаться неправильно
        */
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        enableEdgeToEdge()
        setContent {
            NavigationGraph()
        }
    }
}