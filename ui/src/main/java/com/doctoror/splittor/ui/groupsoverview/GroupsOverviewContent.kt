package com.doctoror.splittor.ui.groupsoverview

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.doctoror.splittor.presentation.groupsoverview.GroupItemViewModel
import com.doctoror.splittor.presentation.groupsoverview.GroupsOverviewViewModel
import com.doctoror.splittor.ui.R
import com.doctoror.splittor.ui.base.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupsOverviewContent(
    onAddClick: () -> Unit,
    onGroupClick: (Long) -> Unit,
    onGroupLongClick: (Long) -> Unit,
    viewModel: GroupsOverviewViewModel
) {
    val surfaceColor = AppTheme.colorScheme.surface
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    LaunchedEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = surfaceColor,
            darkIcons = useDarkIcons
        )
    }

    fun shouldShowFab() = viewModel.viewType.value != GroupsOverviewViewModel.ViewType.LOADING

    val viewType = viewModel.viewType.collectAsStateWithLifecycle()

    AppTheme {
        Scaffold(
            floatingActionButton = {
                if (shouldShowFab()) {
                    FloatingActionButton(onClick = onAddClick) {
                        Image(
                            imageVector = Icons.Filled.Add,
                            contentDescription = stringResource(id = R.string.add_new_group)
                        )
                    }
                }
            },

            topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                when (viewType.value) {
                    GroupsOverviewViewModel.ViewType.LOADING -> GroupsOverviewContentLoading()

                    GroupsOverviewViewModel.ViewType.EMPTY -> GroupsOverviewContentEmpty(onAddClick)

                    GroupsOverviewViewModel.ViewType.CONTENT -> GroupsOverviewContentLoaded(
                        onGroupClick,
                        onGroupLongClick,
                        viewModel
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun GroupsOverviewContentLoadingPreview() {
    GroupsOverviewContent(
        onAddClick = {},
        onGroupClick = {},
        onGroupLongClick = {},
        viewModel = GroupsOverviewViewModel().apply {
            viewType.value = GroupsOverviewViewModel.ViewType.LOADING
        }
    )
}

@Preview
@Composable
fun GroupsOverviewContentEmptyPreview() {
    GroupsOverviewContent(
        onAddClick = {},
        onGroupClick = {},
        onGroupLongClick = {},
        viewModel = GroupsOverviewViewModel().apply {
            viewType.value = GroupsOverviewViewModel.ViewType.EMPTY
        }
    )
}

@Preview
@Composable
fun GroupsOverviewContentLoadedPreview() {
    GroupsOverviewContent(
        onAddClick = {},
        onGroupClick = {},
        onGroupLongClick = {},
        viewModel = GroupsOverviewViewModel().apply {
            viewType.value = GroupsOverviewViewModel.ViewType.CONTENT

            groups.value = listOf(
                GroupItemViewModel(
                    amount = "$12.99",
                    allMembersPaid = false,
                    id = 1L,
                    members = "2 members",
                    title = "Lunch"
                ),

                GroupItemViewModel(
                    amount = "$1,000.58",
                    allMembersPaid = false,
                    id = 1L,
                    members = "4 members",
                    title = "Dinner"
                ),

                GroupItemViewModel(
                    amount = "$1,322",
                    allMembersPaid = true,
                    id = 1L,
                    members = "4 members",
                    title = "Some old stuff"
                )
            )
        }
    )
}
