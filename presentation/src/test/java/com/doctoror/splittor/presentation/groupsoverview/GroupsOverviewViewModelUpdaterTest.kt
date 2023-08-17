package com.doctoror.splittor.presentation.groupsoverview

import com.doctoror.splittor.domain.groups.Group
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GroupsOverviewViewModelUpdaterTest {

    private val groupItemViewModelMapper: GroupItemViewModelMapper = mock()
    private val viewModel = GroupsOverviewViewModel()

    private val underTest = GroupsOverviewViewModelUpdater(groupItemViewModelMapper)

    @Test
    fun setsEmptyViewWhenLoadedEmptyList() {
        underTest.updateOnGroupsListLoaded(viewModel, emptyList())
        assertEquals(GroupsOverviewViewModel.ViewType.EMPTY, viewModel.viewType.value)
    }

    @Test
    fun setsContentViewWhenLoadedNonEmptyList() {
        underTest.updateOnGroupsListLoaded(viewModel, listOf(mock()))
        assertEquals(GroupsOverviewViewModel.ViewType.CONTENT, viewModel.viewType.value)
    }

    @Test
    fun setsGroupsViewModels() {
        val group1: Group = mock()
        val group2: Group = mock()
        val group1ViewModel: GroupItemViewModel = mock()
        val group2ViewModel: GroupItemViewModel = mock()
        whenever(groupItemViewModelMapper.map(group1)).thenReturn(group1ViewModel)
        whenever(groupItemViewModelMapper.map(group2)).thenReturn(group2ViewModel)

        underTest.updateOnGroupsListLoaded(viewModel, listOf(group1, group2))

        assertEquals(listOf(group1ViewModel, group2ViewModel), viewModel.groups)
    }
}
