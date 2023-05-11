package com.wm.petsy

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.compose.rememberNavController
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.google.accompanist.pager.ExperimentalPagerApi
import com.wm.petsy.data.DataStoreRepository
import com.wm.petsy.navigation.Screen
import com.wm.petsy.navigation.SetupNavGraph
import com.wm.petsy.presentation.loginScreen.SignInState
import com.wm.petsy.presentation.loginScreen.SignInViewModel
import com.wm.petsy.presentation.loginScreen.SigninScreen
import com.wm.petsy.ui.theme.PetsyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalAnimationApi
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        val dataStore =  DataStoreRepository(context = this)
       val data =  dataStore.readOnBoardingState()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        actionBar?.hide()
        val flowValue: Boolean
        runBlocking(Dispatchers.IO) {
            flowValue = data.first()
        }
        var startDestination =Screen.Welcome.route
        if(flowValue){startDestination = Screen.Login.route}



        setContent {
            PetsyTheme {
                val navController = rememberNavController()
                SetupNavGraph(
                    navController = navController, startDestination = startDestination)
            }
        }

    }

}
