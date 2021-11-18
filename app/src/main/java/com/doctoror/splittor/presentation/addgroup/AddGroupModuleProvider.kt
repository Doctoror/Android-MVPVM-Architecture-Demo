package com.doctoror.splittor.presentation.addgroup

import com.doctoror.splittor.domain.groups.InsertGroupUseCase
import com.doctoror.splittor.platform.SCHEDULER_IO
import com.doctoror.splittor.platform.SCHEDULER_MAIN_THREAD
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun provideAddGroupModule() = module {

    viewModel { AddGroupInputFieldsMonitor() }

    viewModel { AddGroupViewModel() }

    viewModel { parameters ->
        val viewModelUpdater = AddGroupViewModelUpdater(
            contactDetailsViewModelMapper = ContactDetailsViewModelMapper(),
            viewModel = parameters.get()
        )
        AddGroupPresenter(
            contactDetailsResolver = get(),
            contactPickedEvents = parameters.get(),
            inputFieldsMonitor = parameters.get(),
            insertGroupUseCase = InsertGroupUseCase(
                groupsRepository = get(),
                inputFieldsMonitor = parameters.get(),
                inputFieldsValidator = AddGroupInputFieldsValidator(
                    inputFieldsMonitor = parameters.get(),
                    viewModel = parameters.get()
                ),
                schedulerIo = get(named(SCHEDULER_IO)),
                schedulerMainThread = get(named(SCHEDULER_MAIN_THREAD)),
                viewModelUpdater = viewModelUpdater
            ),
            schedulerIo = get(named(SCHEDULER_IO)),
            schedulerMainThread = get(named(SCHEDULER_MAIN_THREAD)),
            viewModelUpdater = viewModelUpdater
        )
    }
}
