package com.doctoror.splittor.domain.groups

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ObserveGroupsUseCaseTest {

    private val groupsRepository: GroupsRepository = mock()

    private val underTest = ObserveGroupsUseCase(groupsRepository)

    @Test
    fun observeEmitsDataFromRepository() {
        val data = listOf<Group>(mock())
        whenever(groupsRepository.observe()).thenReturn(flowOf(data))

        runBlocking { assertEquals(data, underTest.observe().single()) }
    }
}
