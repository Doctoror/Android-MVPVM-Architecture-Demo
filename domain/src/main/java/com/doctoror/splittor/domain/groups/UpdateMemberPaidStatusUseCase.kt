package com.doctoror.splittor.domain.groups

class UpdateMemberPaidStatusUseCase(private val groupsRepository: GroupsRepository) {

    suspend operator fun invoke(memberId: Long, paid: Boolean) =
        groupsRepository.updateMemberPaidStatus(memberId, paid)
}
