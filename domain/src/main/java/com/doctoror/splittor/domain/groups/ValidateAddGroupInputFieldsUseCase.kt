package com.doctoror.splittor.domain.groups

import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.numberformat.StripCurrencyAndGroupingSeparatorsUseCase

class ValidateAddGroupInputFieldsUseCase(
    private val stripCurrencyAndGroupingSeparatorsUseCase: StripCurrencyAndGroupingSeparatorsUseCase
) {

    operator fun invoke(
        amount: String?,
        contacts: Collection<ContactDetails>?,
        title: CharSequence?
    ): ValidationResult = when {
        title.isNullOrBlank() -> ValidationResult.TITLE_MISSING

        amount.isNullOrBlank() || stripCurrencyAndGroupingSeparatorsUseCase(amount).isBlank()
        -> ValidationResult.AMOUNT_MISSING

        contacts.isNullOrEmpty() -> ValidationResult.CONTACTS_MISSING

        else -> ValidationResult.VALID
    }

    enum class ValidationResult {
        VALID, AMOUNT_MISSING, CONTACTS_MISSING, TITLE_MISSING
    }
}
