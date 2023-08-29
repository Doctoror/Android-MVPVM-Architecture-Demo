package com.doctoror.splittor.presentation.addgroup

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.MutableSharedFlow

const val ADD_GROUP_VIEW_MODEL_KEY_AMOUNT = "amount"
const val ADD_GROUP_VIEW_MODEL_KEY_CONTACTS = "contacts"
const val ADD_GROUP_VIEW_MODEL_KEY_TITLE = "title"

class AddGroupViewModel(val savedStateHandle: SavedStateHandle) {

    val amount = savedStateHandle.getStateFlow(ADD_GROUP_VIEW_MODEL_KEY_AMOUNT, "")

    val contacts = savedStateHandle
        .getStateFlow(ADD_GROUP_VIEW_MODEL_KEY_CONTACTS, emptyList<ContactDetailsViewModel>())

    val errorMessage = MutableSharedFlow<Int>()

    val title = savedStateHandle.getStateFlow(ADD_GROUP_VIEW_MODEL_KEY_TITLE, "")
}
