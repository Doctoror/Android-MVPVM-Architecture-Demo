package com.doctoror.splittor.domain.groups

import io.reactivex.rxjava3.core.Observable

class GetGroupsUseCase(private val groupsRepository: GroupsRepository) {

    fun observeGroups(): Observable<List<Group>> = groupsRepository.observeGroups()
}
