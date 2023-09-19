package com.doctoror.splittor.addgroup

import androidx.lifecycle.viewModelScope
import com.doctoror.splittor.domain.contacts.GetContactDetailsUseCase
import com.doctoror.splittor.domain.groups.GroupsRepository
import com.doctoror.splittor.domain.groups.InsertGroupUseCase
import com.doctoror.splittor.domain.groups.ValidateAddGroupInputFieldsUseCase
import com.doctoror.splittor.domain.numberformat.StripCurrencyAndGroupingSeparatorsUseCase
import com.doctoror.splittor.framework.PresenterWrapper
import com.doctoror.splittor.presentation.addgroup.AddGroupPresenter
import com.doctoror.splittor.presentation.addgroup.AddGroupViewModel
import com.doctoror.splittor.presentation.addgroup.AddGroupViewModelUpdater
import com.doctoror.splittor.presentation.addgroup.ContactDetailsViewModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddGroupPresenterWrapper @Inject constructor(
    private val getContactDetailsUseCase: GetContactDetailsUseCase,
    private val groupsRepository: GroupsRepository,
    private val stripCurrencyAndGroupingSeparatorsUseCase: StripCurrencyAndGroupingSeparatorsUseCase,
) : PresenterWrapper<AddGroupPresenter>() {

    override fun makeWrapped() =     AddGroupPresenter(
        getContactDetailsUseCase = getContactDetailsUseCase,
        insertGroupUseCase = InsertGroupUseCase(groupsRepository),
        stripCurrencyAndGroupingSeparatorsUseCase = stripCurrencyAndGroupingSeparatorsUseCase,
        validateAddGroupInputFieldsUseCase = ValidateAddGroupInputFieldsUseCase(
            stripCurrencyAndGroupingSeparatorsUseCase
        ),
        viewModel = AddGroupViewModel(),
        viewModelScope = viewModelScope,
        viewModelUpdater = AddGroupViewModelUpdater(
            ContactDetailsViewModelMapper()
        )
    )
}
