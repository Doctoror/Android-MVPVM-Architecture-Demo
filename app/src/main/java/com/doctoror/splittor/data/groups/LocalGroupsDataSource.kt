package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.groups.Group
import io.reactivex.rxjava3.core.Observable

class LocalGroupsDataSource(private val groupsDao: GroupsDao) : GroupsDataSource {

    override fun observeGroups(): Observable<List<Group>> = groupsDao
        .observeGroupsWithMembers()
        .map { it as List<Group> }
}
