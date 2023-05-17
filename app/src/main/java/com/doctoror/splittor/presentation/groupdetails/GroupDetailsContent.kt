package com.doctoror.splittor.presentation.groupdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.doctoror.splittor.R
import com.doctoror.splittor.platform.compose.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupDetailsContent(
    onBackClick: () -> Unit,
    onMemberClick: (Long, Boolean) -> Unit,
    viewModel: GroupDetailsViewModel
) {
    AppTheme {
        Scaffold(
            topBar = {
                LargeTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                stringResource(R.string.go_back)
                            )
                        }
                    },
                    title = { Text(viewModel.title.value) }
                )
            }
        ) {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {

                item {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .wrapContentHeight(Alignment.CenterVertically)
                    ) {
                        Text(
                            modifier = Modifier
                                .wrapContentWidth()
                                .align(Alignment.CenterVertically),
                            style = AppTheme.typography.headlineMedium,
                            text = stringResource(R.string.total_),
                            textAlign = TextAlign.End
                        )

                        Spacer(Modifier.weight(1f))

                        Text(
                            modifier = Modifier
                                .wrapContentWidth()
                                .align(Alignment.CenterVertically),
                            style = AppTheme.typography.headlineMedium,
                            text = viewModel.amount.value,
                            textAlign = TextAlign.End
                        )
                    }
                }

                items(viewModel.members) {
                    Box(
                        modifier = Modifier.clickable { onMemberClick(it.id, it.paid) }
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {

                            Checkbox(
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(end = 24.dp),
                                checked = it.paid,
                                onCheckedChange = null
                            )

                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .align(Alignment.CenterVertically),
                            ) {
                                Text(
                                    color = AppTheme.colorScheme.onSurface,
                                    style = AppTheme.typography.bodyLarge.copy(
                                        textDecoration = if (it.paid) {
                                            TextDecoration.LineThrough
                                        } else {
                                            AppTheme.typography.bodyLarge.textDecoration
                                        }
                                    ),
                                    text = it.name
                                )
                                Text(
                                    color = AppTheme.colorScheme.onSurfaceVariant,
                                    style = AppTheme.typography.bodyMedium,
                                    text = it.amount
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GroupDetailsContentPreview() {
    GroupDetailsContent(
        onBackClick = {},
        onMemberClick = { _, _ -> },
        viewModel = GroupDetailsViewModel().apply {
            amount.value = "2,121.00"
            title.value = "Dinner"

            members.add(
                GroupMemberItemViewModel(
                    amount = "$1,099.16",
                    id = 1L,
                    name = "Alice",
                    paid = true
                )
            )

            members.add(
                GroupMemberItemViewModel(
                    amount = "$1,099.16",
                    id = 2L,
                    name = "Bob",
                    paid = false
                )
            )
        }
    )
}
