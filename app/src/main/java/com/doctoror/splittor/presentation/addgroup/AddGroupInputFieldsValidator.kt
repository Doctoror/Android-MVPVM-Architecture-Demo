package com.doctoror.splittor.presentation.addgroup

import androidx.annotation.StringRes
import com.doctoror.splittor.R

class AddGroupInputFieldsValidator(
    private val inputFieldsMonitor: AddGroupInputFieldsMonitor,
    private val viewModel: AddGroupViewModel
) {

    fun validate(): ValidationResult {
        val title = inputFieldsMonitor.title
        if (title.isNullOrBlank()) {
            return ValidationResult(
                valid = false,
                messageId = R.string.title_not_set
            )
        }

        val sum = inputFieldsMonitor.sum
        if (sum.isNullOrBlank()) {
            return ValidationResult(
                valid = false,
                messageId = R.string.amount_not_set
            )
        }

        val contacts = viewModel.contacts.get()
        if (contacts.isNullOrEmpty()) {
            return ValidationResult(
                valid = false,
                messageId = R.string.no_contacts_added
            )
        }

        return ValidationResult(
            valid = true,
            messageId = 0
        )
    }

    data class ValidationResult(
        val valid: Boolean,
        @StringRes val messageId: Int
    )
}
