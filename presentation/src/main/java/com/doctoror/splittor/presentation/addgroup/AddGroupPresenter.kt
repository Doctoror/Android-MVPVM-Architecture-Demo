package com.doctoror.splittor.presentation.addgroup

import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.contacts.GetContactDetailsUseCase
import com.doctoror.splittor.domain.groups.InsertGroupUseCase
import com.doctoror.splittor.domain.groups.ValidateAddGroupInputFieldsUseCase
import com.doctoror.splittor.domain.numberformat.StripCurrencyAndGroupingSeparatorsUseCase
import com.doctoror.splittor.presentation.R
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
    private val insertGroupUseCase: InsertGroupUseCase,
    private val stripCurrencyAndGroupingSeparatorsUseCase: StripCurrencyAndGroupingSeparatorsUseCase,
    private val validateAddGroupInputFieldsUseCase: ValidateAddGroupInputFieldsUseCase,
    viewModel: AddGroupViewModel,
    private val viewModelUpdater: AddGroupViewModelUpdater
) : BasePresenter<AddGroupViewModel>(viewModel) {

    private val groupInsertedEventsFlow = MutableSharedFlow<Long>()
    val groupInsertedEvents: Flow<Long> = groupInsertedEventsFlow

    override fun onCreate() {
    }

    fun handleAmountChange(amount: String) {
        viewModelUpdater.updateAmount(viewModel, amount)
    }

    fun handleTitleChange(title: String) {
        viewModelUpdater.updateTitle(viewModel, title)
    }

    fun handleContactPick(uri: String) {
        viewModelScope.launch {
            val contactDetails: Optional<ContactDetails>
            withContext(dispatcherIo) {
                contactDetails = getContactDetailsUseCase(uri)
            }

            if (contactDetails.isPresent) {
                viewModelUpdater.addContact(viewModel, contactDetails.get())
            }
        }
    }

    fun createGroup() {
        val validationResult = validateAddGroupInputFieldsUseCase(
            viewModel.amount,
            viewModel.contacts,
            viewModel.title
        )

        viewModelScope.launch {
            viewModelUpdater.setErrorMessageId(
                viewModel,
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
        }

        if (validationResult == ValidateAddGroupInputFieldsUseCase.ValidationResult.VALID) {
            viewModelScope.launch {
                groupInsertedEventsFlow.emit(
                    insertGroupUseCase(
                        stripCurrencyAndGroupingSeparatorsUseCase(viewModel.amount),
                        viewModel.contacts.map { it.name },
                        viewModel.title
                    )
                )
            }
        }
    }
}
