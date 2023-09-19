package com.doctoror.splittor.presentation.addgroup

import com.doctoror.splittor.domain.contacts.GetContactDetailsUseCase
import com.doctoror.splittor.domain.groups.InsertGroupUseCase
import com.doctoror.splittor.domain.groups.ValidateAddGroupInputFieldsUseCase
import com.doctoror.splittor.domain.numberformat.StripCurrencyAndGroupingSeparatorsUseCase
import com.doctoror.splittor.presentation.R
import com.doctoror.splittor.presentation.base.LifecyclePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Optional

class AddGroupPresenter(
    private val getContactDetailsUseCase: GetContactDetailsUseCase,
    private val insertGroupUseCase: InsertGroupUseCase,
    private val stripCurrencyAndGroupingSeparatorsUseCase: StripCurrencyAndGroupingSeparatorsUseCase,
    private val validateAddGroupInputFieldsUseCase: ValidateAddGroupInputFieldsUseCase,
    val viewModel: AddGroupViewModel,
    private val viewModelScope: CoroutineScope,
    private val viewModelUpdater: AddGroupViewModelUpdater
) : LifecyclePresenter() {

    private val groupInsertedEventsFlow = MutableStateFlow<Optional<Long>>(Optional.empty())
    val groupInsertedEvents: Flow<Optional<Long>> = groupInsertedEventsFlow

    fun handleAmountChange(amount: String) {
        viewModelScope.launch {
            viewModelUpdater.updateAmount(viewModel, amount)
        }
    }

    fun handleTitleChange(title: String) {
        viewModelScope.launch {
            viewModelUpdater.updateTitle(viewModel, title)
        }
    }

    fun handleContactPick(uri: String) {
        viewModelScope.launch {
            val contactDetails = getContactDetailsUseCase(uri)
            if (contactDetails.isPresent) {
                viewModelUpdater.addContact(viewModel, contactDetails.get())
            }
        }
    }

    fun createGroup() {
        val validationResult = validateAddGroupInputFieldsUseCase(
            viewModel.amount.value,
            viewModel.contacts.value,
            viewModel.title.value
        )

        viewModelScope.launch {
            viewModelUpdater.setErrorMessageId(
                viewModel,
                Optional.ofNullable(
                    when (validationResult) {
                        ValidateAddGroupInputFieldsUseCase.ValidationResult.AMOUNT_MISSING ->
                            R.string.amount_not_set

                        ValidateAddGroupInputFieldsUseCase.ValidationResult.CONTACTS_MISSING ->
                            R.string.no_contacts_added

                        ValidateAddGroupInputFieldsUseCase.ValidationResult.TITLE_MISSING ->
                            R.string.title_not_set

                        else -> null
                    }
                )
            )
        }

        if (validationResult == ValidateAddGroupInputFieldsUseCase.ValidationResult.VALID) {
            viewModelScope.launch {
                groupInsertedEventsFlow.emit(
                    Optional.of(
                        insertGroupUseCase(
                            stripCurrencyAndGroupingSeparatorsUseCase(viewModel.amount.value),
                            viewModel.contacts.value.map { it.name },
                            viewModel.title.value
                        )
                    )
                )
            }
        }
    }
}
