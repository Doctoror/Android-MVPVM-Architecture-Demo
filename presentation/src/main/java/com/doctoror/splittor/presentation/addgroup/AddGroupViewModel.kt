package com.doctoror.splittor.presentation.addgroup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import kotlinx.coroutines.flow.MutableSharedFlow

@OptIn(SavedStateHandleSaveableApi::class)
class AddGroupViewModel(savedStateHandle: SavedStateHandle) {

    var amount by savedStateHandle.saveable {
        mutableStateOf("")
    }

    var contacts by savedStateHandle.saveable {
        mutableStateOf(emptyList<ContactDetailsViewModel>())
    }

    val errorMessage = MutableSharedFlow<Int>()

    var title by savedStateHandle.saveable {
        mutableStateOf("")
    }
}
