package com.doctoror.splittor.presentation.addgroup

import com.doctoror.splittor.domain.groups.InsertGroupUseCase
import com.doctoror.splittor.domain.groups.ValidateAddGroupInputFieldsUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

fun provideAddGroupModule() = module {

    viewModel { AddGroupInputFieldsMonitor() }

    viewModel { AddGroupViewModel() }

    factory { parameters ->
        AddGroupViewModelUpdater(
            contactDetailsViewModelMapper = ContactDetailsViewModelMapper(),
            viewModel = parameters.get()
        )
    }

    viewModel { parameters ->
        AddGroupPresenter(
            dispatcherIo = Dispatchers.IO,
            getContactDetailsUseCase = get(),
            inputFieldsMonitor = parameters.get(),
            insertGroupUseCase = InsertGroupUseCase(groupsRepository = get()),
            validateAddGroupInputFieldsUseCase = ValidateAddGroupInputFieldsUseCase(),
            viewModelUpdater = get { parametersOf(parameters.get<AddGroupViewModel>()) }
        )
    }
}
