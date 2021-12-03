package com.doctoror.splittor.domain.groups

class UpdateMemberPaidStatusUseCase(private val groupsRepository: GroupsRepository) {

    suspend fun updateMemberPaidStatus(memberId: Long, paid: Boolean) =
        groupsRepository.updateMemberPaidStatus(memberId, paid)
}
