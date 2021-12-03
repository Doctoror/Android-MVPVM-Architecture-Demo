package com.doctoror.splittor.domain.groups

import kotlinx.coroutines.flow.Flow

class ObserveGroupUseCase(private val groupsRepository: GroupsRepository) {

    fun observe(id: Long): Flow<Group> = groupsRepository.observe(id)
}
