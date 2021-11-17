package com.doctoror.splittor.presentation.groups

import com.doctoror.splittor.domain.groups.GetGroupsUseCase
import com.doctoror.splittor.domain.groups.Group
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GroupsPresenterTest {

    private val getGroupsUseCase: GetGroupsUseCase = mock()
    private val viewModelUpdater: GroupsViewModelUpdater = mock()

    private val underTest = GroupsPresenter(
        getGroupsUseCase,
        Schedulers.trampoline(),
        Schedulers.trampoline(),
        viewModelUpdater
    )

    @Test
    fun updatesViewModelWithGroups() {
        val groups = listOf<Group>(mock())
        whenever(getGroupsUseCase.observeGroups()).thenReturn(Observable.just(groups))

        underTest.onCreate()

        verify(viewModelUpdater).updateOnGroupsListLoaded(groups)
    }
}
