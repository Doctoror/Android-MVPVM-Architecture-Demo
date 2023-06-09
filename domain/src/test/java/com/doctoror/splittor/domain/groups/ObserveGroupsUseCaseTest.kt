package com.doctoror.splittor.domain.groups

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ObserveGroupsUseCaseTest {

    private val groupsRepository: GroupsRepository = mock()

    private val underTest = ObserveGroupsUseCase(groupsRepository)

    @Test
    fun observeEmitsDataFromRepository() = runTest {
        val data = listOf<Group>(mock())
        whenever(groupsRepository.observe()).thenReturn(flowOf(data))

        assertEquals(data, underTest().single())
    }
}
