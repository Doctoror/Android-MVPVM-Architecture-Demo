package com.doctoror.splittor.domain.groups

import io.reactivex.rxjava3.core.Completable

class UpdateMemberPaidStatusUseCase(private val groupsRepository: GroupsRepository) {

    fun updateMemberPaidStatus(memberId: Long, paid: Boolean): Completable =
        groupsRepository.updateMemberPaidStatus(memberId, paid)
}
