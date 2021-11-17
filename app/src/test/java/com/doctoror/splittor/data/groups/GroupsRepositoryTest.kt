package com.doctoror.splittor.data.groups

import io.reactivex.rxjava3.core.Observable
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GroupsRepositoryTest {

    private val groupsDataSource: GroupsDataSource = mock()

    private val underTest = GroupsRepositoryImpl(groupsDataSource)

    @Test
    fun observeGroupsWithMembersSignalsErrorWhenDataSourceSignalsError() {
        val error: Throwable = mock()
        whenever(groupsDataSource.observeGroups()).thenReturn(Observable.error(error))

        underTest.observeGroups().test().assertError(error)
    }

    @Test
    fun observeGroupsWithMembersSignalsDataFromDataSource() {
        val data = listOf<GroupWithMembers>(mock())
        whenever(groupsDataSource.observeGroups()).thenReturn(Observable.just(data))

        underTest.observeGroups().test().assertValue(data)
    }
}
