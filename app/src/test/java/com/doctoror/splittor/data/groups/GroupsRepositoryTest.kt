package com.doctoror.splittor.data.groups

import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GroupsRepositoryTest {

    private val groupsDataSource: GroupsDataSource = mock()

    private val underTest = GroupsRepositoryImpl(groupsDataSource)

    @Test
    fun observeSignalsErrorWhenDataSourceSignalsError() {
        val error: Throwable = mock()
        whenever(groupsDataSource.observe()).thenReturn(Observable.error(error))

        underTest.observe().test().assertError(error)
    }

    @Test
    fun observeSignalsDataFromDataSource() {
        val data = listOf<GroupWithMembers>(mock())
        whenever(groupsDataSource.observe()).thenReturn(Observable.just(data))

        underTest.observe().test().assertValue(data)
    }
}
