package com.doctoror.splittor.data.groups

import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class LocalGroupsDataSourceTest {

    private val groupsDao: GroupsDao = mock()

    private val underTest = LocalGroupsDataSource(groupsDao)

    @Test
    fun observeGroupsWithMembersSignalsErrorWhenDaoSignalsError() {
        val error: Throwable = mock()
        whenever(groupsDao.observeGroupsWithMembers()).thenReturn(Observable.error(error))

        underTest.observe().test().assertError(error)
    }

    @Test
    fun observeGroupsWithMembersSignalsDataFromDao() {
        val data = listOf<GroupWithMembers>(mock())
        whenever(groupsDao.observeGroupsWithMembers()).thenReturn(Observable.just(data))

        underTest.observe().test().assertValue(data)
    }
}
