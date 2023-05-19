package com.doctoror.splittor.domain.groups

import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class UpdateMemberPaidStatusUseCaseTest {

    private val groupsRepository: GroupsRepository = mock()

    private val underTest = UpdateMemberPaidStatusUseCase(groupsRepository)

    @Test
    fun updatesMemberPaidStatus() = runTest {
        val memberId = 64L
        val paid = true

        underTest(memberId, paid)

        verify(groupsRepository).updateMemberPaidStatus(memberId, paid)
    }
}
