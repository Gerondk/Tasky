package com.gkp.agenda.presentation.compoments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkp.agenda.presentation.R
import com.gkp.core.designsystem.theme.TaskyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaBackground(
    modifier: Modifier = Modifier,
    title: String = "",
    onClickFab: () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable () -> Unit = {},
    hasFloatingActionButton: Boolean = false,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(

        floatingActionButton = {
            if (hasFloatingActionButton) {
                FloatingActionButton(
                    containerColor = MaterialTheme.colorScheme.primary,
                    onClick = onClickFab,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add_task_event_or_reminder),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.primary
                )
        ) {
            CenterAlignedTopAppBar(
                navigationIcon = navigationIcon,
                title = {
                    Text(text = title)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = { actions() }
            )

            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .fillMaxHeight(0.9f)
                    .background(
                        color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp
                        )
                    )
            ) {
                content(padding)
            }

        }
    }


}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun AgendaBackgroundPreview() {
    TaskyTheme {
        AgendaBackground(
            modifier = Modifier,
            content = {},
            hasFloatingActionButton = true
        )
    }

}