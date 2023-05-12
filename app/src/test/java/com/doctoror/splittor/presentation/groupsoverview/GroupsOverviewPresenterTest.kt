package com.doctoror.splittor.presentation.groupsoverview

import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.groups.ObserveGroupsUseCase
import com.doctoror.splittor.presentation.base.runInViewModelScopeBlocking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GroupsOverviewPresenterTest {

    private val observeGroupsUseCase: ObserveGroupsUseCase = mock()
    private val viewModelUpdater: GroupsOverviewViewModelUpdater = mock()

    private val underTest = GroupsOverviewPresenter(
        Dispatchers.Unconfined,
        observeGroupsUseCase,
        viewModelUpdater
    )

    @Test
    fun updatesViewModelWithGroups() {
        val groups = listOf<Group>(mock())
        whenever(observeGroupsUseCase.observe()).thenReturn(flowOf(groups))

        underTest.runInViewModelScopeBlocking { underTest.onCreate() }

        verify(viewModelUpdater).updateOnGroupsListLoaded(groups)
    }
}
