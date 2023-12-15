package com.android.navigationcomposeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "registration") {
        composable(route = "registration") {
            RegistrationScreen(navController)
        }
        composable(route = "login") {
            LoginScreen {
                navController.navigate("main/${it}")
            }
        }
        composable(route = "main/{email}", arguments = listOf(
            navArgument("email") {
                type = NavType.StringType
            }
        )) {
           val email = it.arguments!!.getString("email")
            MainScreen(email)
        }
    }
}

@Composable
fun RegistrationScreen(navController: NavHostController) {
    Text(
        text = "Registration",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.clickable {
            navController.navigate("login")
        }
    )
}


@Composable
fun LoginScreen(onClick: (email: String) -> Unit) {
    Text(
        text = "Login",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.clickable {
            onClick("nzdeveloper009@gmail.com")
        }
    )
}


@Composable
fun MainScreen(email: String?) {
    Text(
        text = "Main Screen\nEmail => $email",
        style = MaterialTheme.typography.headlineLarge
    )
}