package com.example.footballmanager_pj

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.feature_navigation.BottomNavItem
import com.example.feature_navigation.CustomBottomNavigation
import com.example.feature_navigation.Route
import com.example.feature_navigation.showBarList
import com.example.feature_topbar.TopAppBar
import com.example.footballmanager_pj.ui.theme.Footballmanager_pjTheme
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Footballmanager_pjTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navHostController = rememberNavController()
                    val uiRoute = remember { mutableStateOf(Route.HOME) }
                    Scaffold(
                        topBar = {
                            if (showBarList.contains(uiRoute.value)){
                                TopAppBar(
                                    modifier = Modifier.height(60.dp),
                                    title = uiRoute.value,
                                    actionIcon = Icons.Default.Menu,
                                    onBack = { }) {
                                    //do Action
                                }
                            }
                        },
                        bottomBar = {
                            if (uiRoute.value == "HOME" || uiRoute.value == "JOIN_CLUB" || uiRoute.value == "SETTINGS"){
                                CustomBottomNavigation(
                                    modifier = Modifier.height(60.dp),
                                    items = listOf(
                                        BottomNavItem(
                                            icon = Icons.Default.Home,
                                            route = Route.HOME,
                                            configuration = Route.HOME
                                        ),
                                        BottomNavItem(
                                            icon = Icons.Default.Favorite,
                                            route = Route.JOIN_CLUB,
                                            configuration = Route.JOIN_CLUB
                                        ),
                                        BottomNavItem(
                                            icon = Icons.Default.Settings,
                                            route = Route.SETTINGS,
                                            configuration = Route.SETTINGS
                                        )
                                    ), navHostController = navHostController
                                ) {
                                    navHostController.navigate(it.route) {
                                        navHostController.popBackStack()
                                    }
                                }
                            }
                        }) {
                        FootBallManagerAppNavigator(navHostController = navHostController, uiRoute){ uiRoute.value = it }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Footballmanager_pjTheme {
        Greeting("Android")
    }
}