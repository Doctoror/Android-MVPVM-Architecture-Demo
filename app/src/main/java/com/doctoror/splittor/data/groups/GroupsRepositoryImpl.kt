package com.doctoror.splittor.data.groups

import com.doctoror.splittor.domain.groups.Group
import com.doctoror.splittor.domain.groups.GroupsRepository
import io.reactivex.rxjava3.core.Observable

class GroupsRepositoryImpl(private val groupsDataSource: GroupsDataSource) : GroupsRepository {

    override fun observe(): Observable<List<Group>> = groupsDataSource.observe()
}
