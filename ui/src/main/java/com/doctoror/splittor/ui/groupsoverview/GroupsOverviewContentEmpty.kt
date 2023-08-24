package com.doctoror.splittor.ui.groupsoverview

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.doctoror.splittor.ui.R
import com.doctoror.splittor.ui.base.AppTheme

@Composable
fun BoxScope.GroupsOverviewContentEmpty(onAddClick: () -> Unit) {
    Card(
        Modifier
            .align(Alignment.Center)
            .padding(bottom = 52.dp)
    ) {
        Column(
            Modifier
                .defaultMinSize(320.dp)
                .padding(16.dp)
        ) {

            Text(
                style = AppTheme.typography.headlineLarge,
                text = stringResource(id = R.string.no_groups_yet)
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                style = AppTheme.typography.titleMedium,
                text = stringResource(id = R.string.add_a_new_one)
            )

            Button(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.End),
                onClick = onAddClick
            ) {

                Text(text = stringResource(R.string.add_new_group))
            }
        }
    }
}
