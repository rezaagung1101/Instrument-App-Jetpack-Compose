package com.bangkitcapstone.mymusiccompose

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bangkitcapstone.mymusiccompose.navigation.NavigationItem
import com.bangkitcapstone.mymusiccompose.navigation.Screen
import com.bangkitcapstone.mymusiccompose.ui.screen.cart.CartScreen
import com.bangkitcapstone.mymusiccompose.ui.screen.detail.DetailScreen
import com.bangkitcapstone.mymusiccompose.ui.screen.home.HomeScreen

@Composable
fun MyMusicApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailInstrument.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { instrumentId ->
                        navController.navigate(Screen.DetailInstrument.createRoute(instrumentId))
                    }
                )
            }
            composable(Screen.Cart.route) {
                val context = LocalContext.current
                CartScreen(
                    onOrderButtonClicked = { message ->
                        shareOrder(context, message)
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(

                )
            }
            composable(
                route = Screen.DetailInstrument.route,
                arguments = listOf(navArgument("instrumentId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("instrumentId") ?: -1L
                DetailScreen(
                    instrumentId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToCart = {
                        navController.popBackStack()
                        navController.navigate(Screen.Cart.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

private fun shareOrder(context: Context, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.music_instrument))
        putExtra(Intent.EXTRA_TEXT, summary)
    }
    context.startActivity(
        //createChooser merupakan jenis eksekusi Intent yang menampilkan beberapa pilihan aplikasi yang bisa membuka data yang bagikan.
        Intent.createChooser(
            intent,
            context.getString(R.string.music_instrument)
        )
    )
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home_title),
                icon = Icons.Default.Home,
                screen = Screen.Home,
                contentDescription = stringResource(id = R.string.home_page)
            ),
            NavigationItem(
                title = stringResource(R.string.menu_cart_title),
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Cart,
                contentDescription = stringResource(id = R.string.cart_page)
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile_title),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile,
                contentDescription = stringResource(id = R.string.about_page)
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.contentDescription
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MusicComposePreview() {
//    MyMusicComposeTheme() {
//        MyMusicApp()
//    }
//}
