package com.doctoror.splittor.domain.groups

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ObserveGroupUseCaseTest {

    private val groupsRepository: GroupsRepository = mock()

    private val underTest = ObserveGroupUseCase(groupsRepository)

    @Test
    fun observeEmitsDataFromRepository() = runTest {
        val data: Group = mock()
        val id = 12L
        whenever(groupsRepository.observe(id)).thenReturn(flowOf(data))

        assertEquals(data, underTest(id).single())
    }
}
