package com.doctoror.splittor.domain.groups

class DeleteGroupUseCase(private val groupsRepository: GroupsRepository) {

    suspend operator fun invoke(id: Long) = groupsRepository.delete(id)
}