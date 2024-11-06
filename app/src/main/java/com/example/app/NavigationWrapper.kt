package com.example.app
import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app.view.contactemergency.AddContactScreen
import com.example.app.view.initial.InitialScreen
import com.example.app.view.login.LoginScreen
import com.example.app.view.signup.SignUpScreen
import com.google.firebase.auth.FirebaseAuth


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
            LoginScreen { navHostController.navigate("home") }
        }
        composable("signUp") {
            SignUpScreen{ navHostController.navigate("home") }
        }
        composable("home"){
            HomeScreen(navigateToContact = {navHostController.navigate("contactemergency")})
        }
        composable("contactemergency"){
            AddContactScreen { navHostController.navigate("home")}
        }

        }
    }




