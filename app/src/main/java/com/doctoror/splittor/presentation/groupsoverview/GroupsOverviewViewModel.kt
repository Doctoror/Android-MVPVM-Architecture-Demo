package com.doctoror.splittor.presentation.groupsoverview

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel

class GroupsOverviewViewModel : ViewModel() {

    val displayedChildId = ObservableInt(android.R.id.progress)

    val groups = ObservableField<List<GroupItemViewModel>>()
}
