package com.why.kotlin.coroutines.view

import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.why.kotlin.coroutines.app.TitleGatewayImp
import com.why.kotlin.coroutines.theme.MyTheme
import com.why.kotlin.coroutines.viewnodels.MainViewModel

@Composable
private fun Spacer() {
    Spacer(Modifier.height(40.dp))
}

@Composable
private fun ErrorSnackbar(
    modifier: Modifier,
    type: Typography,
    vm: MainViewModel
) {
    Snackbar(
        modifier = modifier,
        text = {
            Text(
                text = "Reached the max rate limit!",
                style = type.subtitle2
            )
        },
        action = {
            Text(
                text = "Ok",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        vm.hideError()
                    },
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary
                )
            )
        },
    )
}

@Composable
private fun TitleAndCount(
    vm: MainViewModel,
    type: Typography
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = vm.itemTitle,
            style = type.h6.merge(TextStyle(textAlign = TextAlign.Center))
        )
        Spacer()

        Text(text = "${vm.tapsCount} taps", style = type.h5)
        Spacer()
    }
}

@Composable
fun Home(vm: MainViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = vm.topAppBarTitle)
                },
                elevation = 1.dp
            )
        }
    ) {
        Surface(
            modifier = Modifier.clickable(
                indication = null,
                interactionState = remember { InteractionState() }
            ) {
                vm.refreshTopBarTitle()
            }

        ) {
            val type = MaterialTheme.typography

            TitleAndCount(vm, type)
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                if (vm.isLoading) {
                    val spinnerRef = createRef()

                    CircularProgressIndicator(
                        modifier = Modifier
                            .constrainAs(spinnerRef) {
                                bottom.linkTo(parent.bottom)
                                end.linkTo(parent.end)
                            }
                            .padding(8.dp)
                    )
                }

                if (vm.isError) {
                    val snackbarRef = createRef()

                    ErrorSnackbar(
                        modifier = Modifier.constrainAs(snackbarRef) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                        type = type,
                        vm = vm
                    )
                }
            }
        }
    }
}

/**
 *
 *
 * Previews
 *
 *
 **/

@Composable
@Preview
fun HomePreview() {
    MyTheme {
        Home(MainViewModel(TitleGatewayImp()))
    }
}

@Composable
@Preview
fun HomeDarkPreview() {
    MyTheme(isDarkTheme = true) {
        Home(MainViewModel(TitleGatewayImp()))
    }
}
