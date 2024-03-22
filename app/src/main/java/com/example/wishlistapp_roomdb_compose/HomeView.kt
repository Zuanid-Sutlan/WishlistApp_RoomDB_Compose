package com.example.wishlistapp_roomdb_compose

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.wishlistapp_roomdb_compose.data.Wish
import com.example.wishlistapp_roomdb_compose.repository.HomeViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(viewModel: HomeViewModel, navController: NavController) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppBarView(title = "WishList", onBackNavClicked = {
                // on click event for navigation icon
                Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
            })
        },
        floatingActionButton = {
            FloatingActionButtonAdd(
                modifier = Modifier
                    .padding(bottom = 20.dp, end = 20.dp),
                onClick = {
                    // onclick event for floating action button add wish
                    navController.navigate(Screen.AddScreen.route + "/0L")
//                    viewModel.updateTitle("")
//                    viewModel.updateDesc("")
                })
        }
    ) {
        val wishList = viewModel.allWishes.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(wishList.value, key = { wish -> wish.id }) { wish ->

                val dismissState = rememberDismissState(confirmStateChange = {
                    if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                        viewModel.deleteWish(wish)
                    }
                    true
                })

                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color = animateColorAsState(
                            if (dismissState.dismissDirection == androidx.compose.material.DismissDirection.StartToEnd) Color.Red else Color.Blue,
                            label = ""
                        )
                        val alignment =
                            if (dismissState.dismissDirection == androidx.compose.material.DismissDirection.StartToEnd) Alignment.CenterStart else Alignment.CenterEnd
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 8.dp, start = 12.dp, end = 12.dp)
                                .background(color = color.value, shape = RoundedCornerShape(12.dp)),
                            contentAlignment = alignment,
                        ) {
                            Row {
                                Spacer(modifier = Modifier.width(20.dp))
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                            }

                        }
                    },
                    directions = setOf(
                        androidx.compose.material.DismissDirection.StartToEnd,
                        androidx.compose.material.DismissDirection.EndToStart
                    ),
                    // it matters on distance from the corners while deleting item
                    dismissThresholds = { FractionalThreshold(0.25f) },
                    dismissContent = {
                        WishListItemView(
                            wish = wish,
                            onClick = { navController.navigate(Screen.AddScreen.route + "/${wish.id}") })
                    })


            }
        }
    }
}

@Composable
fun WishListItemView(wish: Wish, onClick: () -> Unit) {
    Card(
        shape = CardDefaults.shape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 12.dp, end = 12.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(hoveredElevation = 8.dp)
    )
    {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.desc)
        }
    }
}