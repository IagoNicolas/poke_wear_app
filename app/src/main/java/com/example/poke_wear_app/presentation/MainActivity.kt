package com.example.poke_wear_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.poke_wear_app.presentation.theme.Poke_wear_appTheme
import com.example.poke_wear_app.presentation.widget.WearApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            Poke_wear_appTheme {
                WearApp()
            }
        }
    }
}
