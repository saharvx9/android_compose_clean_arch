package com.sahar.composecleanarchitecture.utils

import androidx.navigation.NavHostController

class MainNavigator {

    private lateinit var navigator: NavHostController

    fun initNavHostController(navigator: NavHostController){
        this.navigator = navigator
    }
}