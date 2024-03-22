package com.example.wishlistapp_roomdb_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlistapp_roomdb_compose.data.Wish
import com.example.wishlistapp_roomdb_compose.repository.HomeViewModel
import com.example.wishlistapp_roomdb_compose.ui.theme.WishlistApp_RoomDB_ComposeTheme
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(id: Long, viewModel: HomeViewModel, navController: NavController) {

    /*var title by remember { mutableStateOf(wish.title) }
    var desc by remember { mutableStateOf(wish.desc) }*/

    val snackMessage = remember{ mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()


    if (id != 0L){
        val selectedWish = viewModel.getWishById(id).collectAsState(initial = Wish())
        selectedWish.value.let {
            viewModel.updateTitle(it.title)
            viewModel.updateDesc(it.desc)
        }
    }else{
        viewModel.updateTitle("")
        viewModel.updateDesc("")
    }



    WishlistApp_RoomDB_ComposeTheme {
        Scaffold(
            topBar = {
                AppBarView(
                    title = if (id != 0L) stringResource(id = R.string.update_wish)
                    else stringResource(id = R.string.new_wish),
                    onBackNavClicked = {
//                        viewModel.updateTitle("")
//                        viewModel.updateDesc("")
                        navController.navigateUp()
                    })
            },
            scaffoldState = scaffoldState,
        ) {
            Surface(modifier = Modifier
                .fillMaxSize()
                .padding(it),
                color = MaterialTheme.colorScheme.background) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.heightIn(16.dp))

                    CustomTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        value = viewModel.title.value,
                        onValueChange = { viewModel.updateTitle(it) },
                        label = "Title",
                        keyboardType = KeyboardType.Text
                    )
                    CustomTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        value = viewModel.desc.value,
                        onValueChange = { viewModel.updateDesc(it) },
                        label = "Description",
                        keyboardType = KeyboardType.Text
                    )
                    Button(onClick = {
                        if (viewModel.title.toString().isNotEmpty() && viewModel.desc.toString().isNotEmpty()){

                            if (id == 0L){
                                // TODO add new wish
                                viewModel.insertWish(Wish(title = viewModel.title.value, desc = viewModel.desc.value))
                                snackMessage.value = "Wish has been created successfully!"
                            }else{
                                // TODO update wish
                                viewModel.updateWish(Wish(id = id, title = viewModel.title.value, desc = viewModel.desc.value))
                                snackMessage.value = "Wish updated successfully!"
                            }

                        }else{
                            // fields are empty
                            snackMessage.value = "Please Enter fields to create a Wish!"
                        }

                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
//                            viewModel.updateTitle("")
//                            viewModel.updateDesc("")
                            navController.navigateUp()
                        }
                    },
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.blue))) {
                        Text(text = if (id != 0L) stringResource(id = R.string.update_wish)
                        else stringResource(id = R.string.new_wish),
                            fontSize = 18.sp,
                            color = Color.White)
                    }

                }
            }

        }
    }

}