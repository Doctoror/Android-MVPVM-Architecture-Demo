package com.doctoror.splittor.domain.groups

class InsertGroupUseCase(private val groupsRepository: GroupsRepository) {

    suspend fun insert(
        amount: String,
        contactNames: List<String>,
        title: String
    ): Long = groupsRepository.insert(
        amount = amount,
        contactNames = contactNames,
        title = title
    )
}
