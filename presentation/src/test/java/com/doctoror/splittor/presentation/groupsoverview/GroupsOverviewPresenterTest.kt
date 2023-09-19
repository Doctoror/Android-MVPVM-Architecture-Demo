package com.doctoror.splittor.presentation.groupsoverview

import com.doctoror.splittor.domain.groups.DeleteGroupUseCase
import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.groups.ObserveGroupsUseCase
import com.doctoror.splittor.presentation.base.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class GroupsOverviewPresenterTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val deleteGroupUseCase: DeleteGroupUseCase = mock()
    private val observeGroupsUseCase: ObserveGroupsUseCase = mock()
    private val viewModel: GroupsOverviewViewModel = mock()
    private val viewModelScope = TestScope()
    private val viewModelUpdater: GroupsOverviewViewModelUpdater = mock()

    private val underTest = GroupsOverviewPresenter(
        deleteGroupUseCase,
        observeGroupsUseCase,
        viewModel,
        viewModelScope,
        viewModelUpdater
    )

    @Test
    fun updatesViewModelWithGroups() = viewModelScope.runTest {
        val groups = listOf<Group>(mock())
        whenever(observeGroupsUseCase()).thenReturn(flowOf(groups))

        underTest.dispatchOnCreateIfNotCreated()

        advanceUntilIdle()
        verify(viewModelUpdater).updateOnGroupsListLoaded(viewModel, groups)
    }

    @Test
    fun deletesGroupOnLongClick() = viewModelScope.runTest {
        val id = 1L

        underTest.onGroupLongClick(id)

        advanceUntilIdle()
        verify(deleteGroupUseCase).invoke(id)
    }
}
