package com.doctoror.splittor.domain.groups

import io.reactivex.rxjava3.core.Observable

class ObserveGroupUseCase(private val groupsRepository: GroupsRepository) {

    fun observe(id: Long): Observable<Group> = groupsRepository.observe(id)
}
