package com.example.wishlistapp_roomdb_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.wishlistapp_roomdb_compose.data.Wish
import com.example.wishlistapp_roomdb_compose.repository.HomeViewModel
import com.example.wishlistapp_roomdb_compose.ui.theme.WishlistApp_RoomDB_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: HomeViewModel = viewModel()
            val navController = rememberNavController()
            WishlistApp_RoomDB_ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        // dummy data
                    viewModel.addWishInList(Wish(1,"Android Developer", "Want to become a Master in Android App Development"))
                    viewModel.addWishInList(Wish(2,"jetpack compose", "Want to become a Master in jetpack compose App Development"))
                    viewModel.addWishInList(Wish(3,"Flutter", "Also want to learn and become a Master in Flutter App Development"))

                    Navigation(viewModel = viewModel, navController)
                }
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    WishlistApp_RoomDB_ComposeTheme {

    }
}