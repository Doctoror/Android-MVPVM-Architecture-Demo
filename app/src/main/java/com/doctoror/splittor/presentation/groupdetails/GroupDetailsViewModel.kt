package com.doctoror.splittor.presentation.groupdetails

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class GroupDetailsViewModel : ViewModel() {

    val amount = ObservableField<CharSequence>()

    val members = ObservableField<List<GroupMemberItemViewModel>>()
}
