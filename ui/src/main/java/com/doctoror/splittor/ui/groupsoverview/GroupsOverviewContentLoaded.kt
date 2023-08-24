package com.doctoror.splittor.ui.groupsoverview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.doctoror.splittor.presentation.groupsoverview.GroupsOverviewViewModel
import com.doctoror.splittor.ui.base.AppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupsOverviewContentLoaded(
    onGroupClick: (Long) -> Unit,
    onGroupLongClick: (Long) -> Unit,
    viewModel: GroupsOverviewViewModel
) {
    LazyColumn {
        items(viewModel.groups) {
            Box(
                modifier = Modifier
                    .defaultMinSize(minHeight = 72.dp)
                    .combinedClickable(
                        onClick = { onGroupClick(it.id) },
                        onLongClick = { onGroupLongClick(it.id) }
                    )
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(start = 16.dp, top = 8.dp, end = 24.dp, bottom = 8.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically),
                    ) {
                        Text(
                            color = AppTheme.colorScheme.onSurface,
                            style = AppTheme.typography.bodyLarge.copy(
                                textDecoration = if (it.allMembersPaid) {
                                    TextDecoration.LineThrough
                                } else {
                                    AppTheme.typography.bodyLarge.textDecoration
                                }
                            ),
                            text = it.title
                        )
                        Text(
                            color = AppTheme.colorScheme.onSurfaceVariant,
                            style = AppTheme.typography.bodyMedium,
                            text = it.members
                        )
                    }

                    Text(
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterVertically),
                        color = AppTheme.colorScheme.onSurfaceVariant,
                        style = AppTheme.typography.bodyMedium,
                        text = it.amount
                    )
                }
            }
        }
    }
}
