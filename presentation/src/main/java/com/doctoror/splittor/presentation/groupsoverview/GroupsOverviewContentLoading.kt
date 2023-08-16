package com.doctoror.splittor.presentation.groupsoverview

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BoxScope.GroupsOverviewContentLoading() {
    CircularProgressIndicator(
        Modifier
            .width(52.dp)
            .padding(bottom = 52.dp)
            .align(Alignment.Center)
    )
}
