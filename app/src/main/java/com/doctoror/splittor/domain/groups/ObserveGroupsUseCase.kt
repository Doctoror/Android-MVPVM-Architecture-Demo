package com.doctoror.splittor.domain.groups

import kotlinx.coroutines.flow.Flow

class ObserveGroupsUseCase(private val groupsRepository: GroupsRepository) {

    fun observe(): Flow<List<Group>> = groupsRepository.observe()
}
