package com.why.kotlin.coroutines.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.why.kotlin.coroutines.theme.MyTheme
import com.why.kotlin.coroutines.viewnodels.MainViewModel

@Composable
private fun Spacer() {
    Spacer(Modifier.height(40.dp))
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
            modifier = Modifier.clickable(indication = null) {
                vm.incTaps()
            }
        ) {
            val type = MaterialTheme.typography
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = vm.itemTitle, style = type.h6)
                Spacer()
                Text(text = "${vm.tapsCount} taps", style = type.h5)
                Spacer()
                if (vm.isLoading) CircularProgressIndicator()
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
        Home(MainViewModel())
    }
}

@Composable
@Preview
fun HomeDarkPreview() {
    MyTheme(isDarkTheme = true) {
        Home(MainViewModel())
    }
}
