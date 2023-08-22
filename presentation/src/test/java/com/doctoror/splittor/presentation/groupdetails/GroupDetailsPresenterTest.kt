package com.doctoror.splittor.presentation.groupdetails

import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.groups.ObserveGroupUseCase
import com.doctoror.splittor.domain.groups.UpdateMemberPaidStatusUseCase
import com.doctoror.splittor.presentation.base.MainDispatcherRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GroupDetailsPresenterTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val groupId = 1923L
    private val observeGroupUseCase: ObserveGroupUseCase = mock()
    private val updateMemberPaidStatusUseCase: UpdateMemberPaidStatusUseCase = mock()
    private val viewModel: GroupDetailsViewModel = mock()
    private val viewModelUpdater: GroupDetailsViewModelUpdater = mock()

    private val underTest = GroupDetailsPresenter(
        Dispatchers.Unconfined,
        groupId,
        observeGroupUseCase,
        updateMemberPaidStatusUseCase,
        viewModel,
        viewModelUpdater
    )

    @Before
    fun setup() {
        val scope = MainScope()
        underTest.viewModelScopeProvider = { scope }
    }

    @Test
    fun observesGroupAndUpdatesViewModelOnCreate() {
        val group: Group = mock()
        whenever(observeGroupUseCase(groupId)).thenReturn(flowOf(group))

        underTest.onCreate()

        verify(viewModelUpdater).update(viewModel, group)
    }

    @Test
    fun updatesMemberPaidStatus() = runTest {
        val memberId = 84L
        val paid = true

        underTest.updateMemberPaidStatus(memberId, paid)

        verify(updateMemberPaidStatusUseCase)(memberId, paid)
    }
}
