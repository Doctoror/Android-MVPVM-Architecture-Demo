package com.doctoror.splittor.presentation.addgroup

import androidx.lifecycle.SavedStateHandle
import com.doctoror.splittor.domain.groups.InsertGroupUseCase
import com.doctoror.splittor.domain.groups.ValidateAddGroupInputFieldsUseCase
import com.doctoror.splittor.domain.numberformat.StripCurrencyAndGroupingSeparatorsUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

fun provideAddGroupModule() = module {

    viewModel { (handle: SavedStateHandle) -> AddGroupViewModel(handle) }

    factory { parameters ->
        AddGroupViewModelUpdater(
            contactDetailsViewModelMapper = ContactDetailsViewModelMapper(),
            viewModel = parameters.get()
        )
    }

    viewModel { parameters ->
        val stripCurrencyAndGroupingSeparatorsUseCase: StripCurrencyAndGroupingSeparatorsUseCase =
            get()
        AddGroupPresenter(
            dispatcherIo = Dispatchers.IO,
            getContactDetailsUseCase = get(),
            insertGroupUseCase = InsertGroupUseCase(groupsRepository = get()),
            stripCurrencyAndGroupingSeparatorsUseCase = stripCurrencyAndGroupingSeparatorsUseCase,
            validateAddGroupInputFieldsUseCase = ValidateAddGroupInputFieldsUseCase(
                stripCurrencyAndGroupingSeparatorsUseCase
            ),
            viewModel = parameters.get(),
            viewModelUpdater = get { parametersOf(parameters.get<AddGroupViewModel>()) }
        )
    }
}
