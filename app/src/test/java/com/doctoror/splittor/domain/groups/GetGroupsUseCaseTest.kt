package com.doctoror.splittor.domain.groups

import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetGroupsUseCaseTest {

    private val groupsRepository: GroupsRepository = mock()

    private val underTest = GetGroupsUseCase(groupsRepository)

    @Test
    fun observeGroupsWithMembersSignalsErrorWhenRepositorySignalsError() {
        val error: Throwable = mock()
        whenever(groupsRepository.observeGroups()).thenReturn(Observable.error(error))

        underTest.observeGroups().test().assertError(error)
    }

    @Test
    fun observeGroupsWithMembersSignalsDataFromRepository() {
        val data = listOf<Group>(mock())
        whenever(groupsRepository.observeGroups()).thenReturn(Observable.just(data))

        underTest.observeGroups().test().assertValue(data)
    }
}
