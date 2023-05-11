package com.wm.petsy.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wm.petsy.navigation.Screen.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.wm.petsy.presentation.HomeScreen.HomeScreen
import com.wm.petsy.presentation.WelcomeScreen.WelcomeScreen
import com.wm.petsy.presentation.loginScreen.SignInState
import com.wm.petsy.presentation.loginScreen.SigninScreen

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {



    NavHost(
        navController = navController,
        startDestination =startDestination
    ) {
        composable(route = Welcome.route) {
            WelcomeScreen(navController = navController)

        }
        composable(route = Home.route){
            HomeScreen()
        }
        composable(route = Login.route){
            SigninScreen(navController =navController )
        }
        }
    }
