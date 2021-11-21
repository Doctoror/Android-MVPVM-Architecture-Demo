package com.doctoror.splittor.presentation.groups

import com.doctoror.splittor.R
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class GroupsViewModelUpdaterTest {

    private val groupItemViewModelMapper: GroupItemViewModelMapper = mock()
    private val viewModel = GroupsViewModel()

    private val underTest = GroupsViewModelUpdater(groupItemViewModelMapper, viewModel)

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
}
