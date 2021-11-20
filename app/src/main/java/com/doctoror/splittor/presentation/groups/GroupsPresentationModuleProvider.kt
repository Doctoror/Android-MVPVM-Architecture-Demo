package com.doctoror.splittor.presentation.groups

import com.doctoror.splittor.domain.groups.ObserveGroupsUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun provideGroupsPresentationModule() = module {

    viewModel {
        GroupsViewModel()
    }

    viewModel { parameters ->
        GroupsPresenter(
            observeGroupsUseCase = ObserveGroupsUseCase(groupsRepository = get()),
            schedulerIo = Schedulers.io(),
            schedulerMainThread = AndroidSchedulers.mainThread(),
            viewModelUpdater = GroupsViewModelUpdater(viewModel = parameters.get())
        )
    }
}
