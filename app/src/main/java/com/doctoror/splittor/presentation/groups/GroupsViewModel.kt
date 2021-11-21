package com.doctoror.splittor.presentation.groups

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel

class GroupsViewModel : ViewModel() {

    val displayedChildId = ObservableInt(android.R.id.progress)

    val groups = ObservableField<List<GroupItemViewModel>>()
}
