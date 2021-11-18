package com.doctoror.splittor.presentation.addgroup

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel

class AddGroupViewModel : ViewModel() {

    val contacts = ObservableField<MutableSet<ContactDetailsViewModel>>(sortedSetOf())

    val errorMessage = ObservableInt()
}
