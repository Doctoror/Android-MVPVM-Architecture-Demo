package com.doctoror.splittor.presentation.addgroup

import com.doctoror.splittor.domain.groups.InsertGroupUseCase
import com.doctoror.splittor.domain.groups.ValidateAddGroupInputFieldsUseCase
import com.doctoror.splittor.platform.SCHEDULER_IO
import com.doctoror.splittor.platform.SCHEDULER_MAIN_THREAD
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
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
            contactDetailsResolver = get(),
            contactPickedEvents = parameters.get(),
            inputFieldsMonitor = parameters.get(),
            insertGroupUseCase = InsertGroupUseCase(groupsRepository = get()),
            schedulerIo = get(named(SCHEDULER_IO)),
            schedulerMainThread = get(named(SCHEDULER_MAIN_THREAD)),
            validateAddGroupInputFieldsUseCase = ValidateAddGroupInputFieldsUseCase(),
            viewModelUpdater = get { parametersOf(parameters.get<AddGroupViewModel>()) }
        )
    }
}
