package com.sejongunivclub.feature_navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

//임시 브랜치 병합 후 제거예정
val mainTheme = Color(0xFF232A39)

@Composable
fun CustomBottomNavigation(
    modifier: Modifier = Modifier,
    items: List<BottomNavItem>,
    navHostController: NavHostController,
    onNavigate: (BottomNavItem) -> Unit
) {
    val backStackEntry = navHostController.currentBackStackEntryAsState()
    Row(
        modifier
            .fillMaxWidth()
            .background(mainTheme),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEach { navItem ->
            key(navItem.route) {
                val isSelected = navItem.route == backStackEntry.value?.destination?.route
                NavItem(item = navItem, selected = isSelected) { destination ->
                    onNavigate(destination)
                }
            }
        }
    }
}

@Composable
private fun NavItem(item: BottomNavItem, selected: Boolean, onItemClick: (BottomNavItem) -> Unit) {
    Column(
        Modifier
            .clickable {
                onItemClick(item)
            }
            .padding(10.dp)) {
        if (selected) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.configuration,
                tint = Color.Black
            )
        } else {
            Icon(
                imageVector = item.icon,
                contentDescription = item.configuration,
                tint = Color.White
            )
        }
    }
}

@Composable
@Preview
fun CustomBottomNavigationPreview() {
    CustomBottomNavigation(
        items = listOf(
            BottomNavItem(
                icon = Icons.Default.Home,
                route = Route.HOME,
                configuration = Route.HOME
            ),
            BottomNavItem(
                icon = Icons.Default.Favorite,
                route = Route.SCHEDULE,
                configuration = Route.SCHEDULE
            ),
            BottomNavItem(
                icon = Icons.Default.Settings,
                route = Route.SETTINGS,
                configuration = Route.SETTINGS
            )
        ), navHostController = rememberNavController()
    ) {

    }
}

@Composable
@Preview
fun NavItemPreview() {
    NavItem(
        item = BottomNavItem(
            icon = Icons.Default.Home,
            route = Route.HOME,
            configuration = Route.HOME
        ),
        selected = true
    ) {

    }
}