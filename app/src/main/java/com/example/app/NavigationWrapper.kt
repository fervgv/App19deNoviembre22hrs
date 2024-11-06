package com.example.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app.presentation.initial.InitialScreen
import com.example.app.presentation.login.LoginScreen
import com.example.app.presentation.signup.SignUpScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.app.presentation.home.HomeScreen


@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth
) {

    NavHost(navController = navHostController, startDestination = "initial") {
        composable("initial") {
            InitialScreen(navigateToLogin = { navHostController.navigate("logIn") },
                navigateToSignUp = { navHostController.navigate("signUp") })
        }
        composable("logIn") {
            LoginScreen(auth){ navHostController.navigate("home") }
        }
        composable("signUp") {
            SignUpScreen(auth)
        }
        composable("home"){
            HomeScreen()
        }
    }
}