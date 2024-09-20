package com.example.fetchcodingexercise.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fetchcodingexercise.R
import com.example.fetchcodingexercise.model.Hire
import com.example.fetchcodingexercise.viewModel.FetchHireState

/**
 * This composable determines if the screen is loading, erred, or successful and loads the
 * proper screen accordingly
 */
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    hireState: FetchHireState,
    tryAgainButton: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (hireState) {
        is FetchHireState.Loading -> LoadingScreen(modifier = modifier.fillMaxWidth())
        is FetchHireState.Success -> ResultScreen(
            modifier = modifier,
            hireState.hiredList
        )

        is FetchHireState.Error -> ErrorScreen(
            modifier = modifier.fillMaxWidth(),
            onClick = tryAgainButton
        )
    }
}

/**
 * This composable is used to show that the list is being retrieved and is in a waiting state
 * using a Circular Progress Indicator.
 */
@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = modifier
                .wrapContentSize(),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

/**
 * This composable is used to show that the list was not retrieved properly. The composable also
 * provides a button to retry the retrieval of the list.
 */
@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.error_24px),

                contentDescription = ""
            )
            Text(
                text = stringResource(R.string.error_message),
                color = colorResource(R.color.black),
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = onClick) {
                Text(
                    text = stringResource(R.string.try_again_btn)
                )
            }
        }
    }
}

/**
 * This composable is passed the filtered and sorted list and builds a list view to display the
 * hires. It uses a compose card to display each hire item.
 */
@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    hireList: List<Hire>
) {
    LazyColumn(modifier = modifier) {
        items(hireList) { hire ->
            HireCard(modifier = modifier, hire = hire)
        }
    }
}

/**
 * This composable creates a card that will display the hire data from the list.
 */
@Composable
fun HireCard(
    modifier: Modifier,
    hire: Hire
) {
    ElevatedCard(
        modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    )
    {
        Column(
            modifier
                .padding(8.dp)
        )
        {
            Row {
                Text(
                    modifier = modifier.padding(end = 4.dp),
                    text = stringResource(R.string.id)
                )
                Text(
                    text = hire.id.toString()
                )
            }
            Column(
                modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Row {
                    Text(
                        modifier = modifier.padding(end = 4.dp),
                        text = stringResource(R.string.list_id)
                    )
                    Text(
                        text = hire.listId.toString()
                    )
                }
                Row {
                    Text(
                        modifier = modifier.padding(end = 4.dp),
                        text = stringResource(R.string.name)
                    )
                    Text(
                        text = hire.name ?: ""
                    )
                }
            }
        }
    }
}

/**
 * The following variables are used to provide fake data for the previews.
 */
val hirePreview: Hire = Hire(123, 456, "Tom")
val hireListPreview: List<Hire> = listOf(
    Hire(123, 456, "Tom"),
    Hire(312, 444, "Tim"),
    Hire(267, 956, "Tam")
)


@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(onClick = {})
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(name = "Full Preview", showSystemUi = true)
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    HireCard(modifier = Modifier, hire = hirePreview)
}

@Preview(showBackground = true)
@Composable
fun CardListPreview() {
    ResultScreen(hireList = hireListPreview)
}