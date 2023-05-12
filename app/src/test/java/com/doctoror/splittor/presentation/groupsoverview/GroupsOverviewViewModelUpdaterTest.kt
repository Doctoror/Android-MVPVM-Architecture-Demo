package com.doctoror.splittor.presentation.groupsoverview

import com.doctoror.splittor.R
import com.doctoror.splittor.domain.groups.Group
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GroupsOverviewViewModelUpdaterTest {

    private val groupItemViewModelMapper: GroupItemViewModelMapper = mock()
    private val viewModel = GroupsOverviewViewModel()

    private val underTest = GroupsOverviewViewModelUpdater(groupItemViewModelMapper, viewModel)

    @Test
    fun setsEmptyViewWhenLoadedEmptyList() {
        underTest.updateOnGroupsListLoaded(emptyList())
        assertEquals(R.id.fragmentGroupsEmpty, viewModel.displayedChildId.get())
    }

    @Test
    fun setsContentViewWhenLoadedNonEmptyList() {
        underTest.updateOnGroupsListLoaded(listOf(mock()))
        assertEquals(R.id.fragmentGroupsContent, viewModel.displayedChildId.get())
    }

    @Test
    fun setsGroupsViewModels() {
        val group1: Group = mock()
        val group2: Group = mock()
        val group1ViewModel: GroupItemViewModel = mock()
        val group2ViewModel: GroupItemViewModel = mock()
        whenever(groupItemViewModelMapper.map(group1)).thenReturn(group1ViewModel)
        whenever(groupItemViewModelMapper.map(group2)).thenReturn(group2ViewModel)

        underTest.updateOnGroupsListLoaded(listOf(group1, group2))

        assertEquals(listOf(group1ViewModel, group2ViewModel), viewModel.groups.get())
    }
}
