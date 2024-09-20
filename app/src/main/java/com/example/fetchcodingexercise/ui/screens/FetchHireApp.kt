package com.example.fetchcodingexercise.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fetchcodingexercise.R
import com.example.fetchcodingexercise.viewModel.FetchHireViewModel

/**
 * This composable creates the topbar and the screen that will hold the results, loading, or error
 * screen when the application is running.
 */
@Composable
fun FetchHireApp() {
    Scaffold(
        modifier = Modifier,
        topBar = {
            FetchHireTopBar(
                modifier = Modifier
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(top = 100.dp),
            color = colorResource(R.color.white)
        ) {
            val viewModel: FetchHireViewModel = viewModel()

            MainScreen(
                hireState = viewModel.hireState,
                contentPadding = it,
                tryAgainButton = { viewModel.getHireList() }
            )

        }
    }
}

/**
 * This composable holds the topbar structure
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FetchHireTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(R.string.top_bar)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Preview
@Composable
fun FetchHireAppPreview() {
    FetchHireApp()
}