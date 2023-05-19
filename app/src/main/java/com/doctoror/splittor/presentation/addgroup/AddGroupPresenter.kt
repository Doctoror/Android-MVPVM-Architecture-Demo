package com.doctoror.splittor.presentation.addgroup

import androidx.lifecycle.viewModelScope
import com.doctoror.splittor.R
import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.contacts.GetContactDetailsUseCase
import com.doctoror.splittor.domain.groups.InsertGroupUseCase
import com.doctoror.splittor.domain.groups.ValidateAddGroupInputFieldsUseCase
import com.doctoror.splittor.presentation.base.BasePresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Optional

class AddGroupPresenter(
    private val dispatcherIo: CoroutineDispatcher,
    private val getContactDetailsUseCase: GetContactDetailsUseCase,
    private val inputFieldsMonitor: AddGroupInputFieldsMonitor,
    private val insertGroupUseCase: InsertGroupUseCase,
    private val validateAddGroupInputFieldsUseCase: ValidateAddGroupInputFieldsUseCase,
    private val viewModelUpdater: AddGroupViewModelUpdater
) : BasePresenter() {

    private val groupInsertedEventsFlow = MutableSharedFlow<Long>()
    val groupInsertedEvents: Flow<Long> = groupInsertedEventsFlow

    override fun onCreate() {
    }

    fun handleContactPick(uri: String) {
        viewModelScope.launch {
            val contactDetails: Optional<ContactDetails>
            withContext(dispatcherIo) {
                contactDetails = getContactDetailsUseCase(uri)
            }

            if (contactDetails.isPresent) {
                inputFieldsMonitor.addContact(contactDetails.get())
                viewModelUpdater.addContact(contactDetails.get())
            }
        }
    }

    fun createGroup() {
        val validationResult = validateAddGroupInputFieldsUseCase.validate(
            inputFieldsMonitor.amount,
            inputFieldsMonitor.contacts,
            inputFieldsMonitor.title
        )

        viewModelUpdater.setErrorMessageId(
            when (validationResult) {
                ValidateAddGroupInputFieldsUseCase.ValidationResult.AMOUNT_MISSING ->
                    R.string.amount_not_set

                ValidateAddGroupInputFieldsUseCase.ValidationResult.CONTACTS_MISSING ->
                    R.string.no_contacts_added

                ValidateAddGroupInputFieldsUseCase.ValidationResult.TITLE_MISSING ->
                    R.string.title_not_set

                else -> 0
            }
        )

        if (validationResult == ValidateAddGroupInputFieldsUseCase.ValidationResult.VALID) {
            viewModelScope.launch {
                groupInsertedEventsFlow.emit(
                    insertGroupUseCase.insert(
                        inputFieldsMonitor.amount!!.toString(),
                        inputFieldsMonitor.contacts.map { it.name },
                        inputFieldsMonitor.title!!.toString()
                    )
                )
            }
        }
    }
}
