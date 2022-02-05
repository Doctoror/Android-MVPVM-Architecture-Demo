package com.doctoror.splittor.presentation.addgroup

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.doctoror.splittor.R
import com.doctoror.splittor.domain.groups.GetContactDetailsUseCase
import com.doctoror.splittor.domain.groups.InsertGroupUseCase
import com.doctoror.splittor.domain.groups.ValidateAddGroupInputFieldsUseCase
import com.doctoror.splittor.presentation.base.BasePresenter
import kotlinx.coroutines.launch

class AddGroupPresenter(
    private val getContactDetailsUseCase: GetContactDetailsUseCase,
    private val inputFieldsMonitor: AddGroupInputFieldsMonitor,
    private val insertGroupUseCase: InsertGroupUseCase,
    private val validateAddGroupInputFieldsUseCase: ValidateAddGroupInputFieldsUseCase,
    private val viewModelUpdater: AddGroupViewModelUpdater
) : BasePresenter() {

    private val groupInsertedEventsSubject = MutableLiveData<Long>()
    val groupInsertedEvents: LiveData<Long> = groupInsertedEventsSubject

    override fun onCreate() {
    }

    fun handleContactPick(uri: Uri) {
        viewModelScope.launch {
            val contactDetails = getContactDetailsUseCase.getForUri(uri)
            if (contactDetails.isPresent) {
                inputFieldsMonitor.contacts.add(contactDetails.get())
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
                groupInsertedEventsSubject.value = insertGroupUseCase.insert(
                    inputFieldsMonitor.amount!!.toString(),
                    inputFieldsMonitor.contacts.toList(),
                    inputFieldsMonitor.title!!.toString()
                )
            }
        }
    }
}
