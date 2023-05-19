package com.doctoror.splittor.domain.groups

import kotlinx.coroutines.flow.Flow

class ObserveGroupUseCase(private val groupsRepository: GroupsRepository) {

    operator fun invoke(id: Long): Flow<Group> = groupsRepository.observe(id)
}
