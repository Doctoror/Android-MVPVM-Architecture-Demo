package com.doctoror.splittor.domain.groups

import io.reactivex.rxjava3.core.Observable

class ObserveGroupsUseCase(private val groupsRepository: GroupsRepository) {

    fun observe(): Observable<List<Group>> = groupsRepository.observe()
}
