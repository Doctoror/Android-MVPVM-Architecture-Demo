package com.doctoror.splittor.presentation.addgroup

import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Optional

class AddGroupViewModel {

    val amount = MutableStateFlow("")

    val contacts = MutableStateFlow(arrayListOf<ContactDetailsViewModel>())

    val errorMessage = MutableStateFlow<Optional<Int>>(Optional.empty())

    val title = MutableStateFlow("")
}
