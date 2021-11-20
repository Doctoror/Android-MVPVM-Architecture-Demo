package com.doctoror.splittor.domain.groups

import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ObserveGroupsUseCaseTest {

    private val groupsRepository: GroupsRepository = mock()

    private val underTest = ObserveGroupsUseCase(groupsRepository)

    @Test
    fun observeSignalsErrorWhenRepositorySignalsError() {
        val error: Throwable = mock()
        whenever(groupsRepository.observe()).thenReturn(Observable.error(error))

        underTest.observe().test().assertError(error)
    }

    @Test
    fun observeSignalsDataFromRepository() {
        val data = listOf<Group>(mock())
        whenever(groupsRepository.observe()).thenReturn(Observable.just(data))

        underTest.observe().test().assertValue(data)
    }
}
