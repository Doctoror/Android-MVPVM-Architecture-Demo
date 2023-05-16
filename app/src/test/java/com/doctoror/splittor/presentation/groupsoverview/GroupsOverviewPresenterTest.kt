package com.doctoror.splittor.presentation.groupsoverview

import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.groups.ObserveGroupsUseCase
import com.doctoror.splittor.presentation.base.MainDispatcherRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GroupsOverviewPresenterTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

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

        underTest.onCreate()

        verify(viewModelUpdater).updateOnGroupsListLoaded(groups)
    }
}
