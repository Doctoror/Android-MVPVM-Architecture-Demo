package com.doctoror.splittor.domain.groups

import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class DeleteGroupUseCaseTest {

    private val groupsRepository: GroupsRepository = mock()

    private val underTest = DeleteGroupUseCase(groupsRepository)

    @Test
    fun deletes() = runTest {
        val id = 13L

        underTest(id)

        verify(groupsRepository).delete(id)
    }
}