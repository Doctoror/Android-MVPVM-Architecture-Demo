package com.doctoror.splittor.domain.groups

import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock

class ValidateAddGroupInputFieldsUseCaseTest {

    private val underTest = ValidateAddGroupInputFieldsUseCase()

    @Test
    fun validateReturnsAmountMissingWhenAmountIsNullOrEmptyOrBlank() {
        assertEquals(
            ValidateAddGroupInputFieldsUseCase.ValidationResult.AMOUNT_MISSING,
            underTest(
                null,
                listOf(mock()),
                "title"
            )
        )

        assertEquals(
            ValidateAddGroupInputFieldsUseCase.ValidationResult.AMOUNT_MISSING,
            underTest(
                "",
                listOf(mock()),
                "title"
            )
        )

        assertEquals(
            ValidateAddGroupInputFieldsUseCase.ValidationResult.AMOUNT_MISSING,
            underTest(
                " ",
                listOf(mock()),
                "title"
            )
        )
    }

    @Test
    fun validateReturnsTitleMissingWhenTitleIsNullOrEmptyOrBlank() {
        assertEquals(
            ValidateAddGroupInputFieldsUseCase.ValidationResult.TITLE_MISSING,
            underTest(
                "16",
                listOf(mock()),
                null
            )
        )

        assertEquals(
            ValidateAddGroupInputFieldsUseCase.ValidationResult.TITLE_MISSING,
            underTest(
                "16",
                listOf(mock()),
                ""
            )
        )

        assertEquals(
            ValidateAddGroupInputFieldsUseCase.ValidationResult.TITLE_MISSING,
            underTest(
                "16",
                listOf(mock()),
                " "
            )
        )
    }

    @Test
    fun validateReturnsTitleMissingWhenAllFieldsAreEmptyOrBlank() {
        assertEquals(
            ValidateAddGroupInputFieldsUseCase.ValidationResult.TITLE_MISSING,
            underTest(
                null,
                emptyList(),
                null
            )
        )
    }

    @Test
    fun validateReturnsContactsMissingWhenContactsAreNullOrEmpty() {
        assertEquals(
            ValidateAddGroupInputFieldsUseCase.ValidationResult.CONTACTS_MISSING,
            underTest(
                "23",
                null,
                "Title"
            )
        )

        assertEquals(
            ValidateAddGroupInputFieldsUseCase.ValidationResult.CONTACTS_MISSING,
            underTest(
                "36",
                emptyList(),
                "Title"
            )
        )
    }

    @Test
    fun validateReturnsValidWhenAllParametersArePresent() {
        assertEquals(
            ValidateAddGroupInputFieldsUseCase.ValidationResult.VALID,
            underTest(
                "23",
                listOf(mock()),
                "Title"
            )
        )
    }
}
