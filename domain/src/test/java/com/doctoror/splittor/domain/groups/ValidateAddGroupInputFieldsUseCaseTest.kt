package com.doctoror.splittor.domain.groups

import com.doctoror.splittor.domain.numberformat.StripCurrencyAndGroupingSeparatorsUseCase
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ValidateAddGroupInputFieldsUseCaseTest {

    private val stripCurrencyAndGroupingSeparatorsUseCase: StripCurrencyAndGroupingSeparatorsUseCase =
        mock()

    private val underTest = ValidateAddGroupInputFieldsUseCase(
        stripCurrencyAndGroupingSeparatorsUseCase
    )

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
    fun validateReturnsAmountMissingWhenStrippedAmountIsBlank() {
        val amount = "$"
        whenever(stripCurrencyAndGroupingSeparatorsUseCase(amount)).thenReturn("")

        assertEquals(
            ValidateAddGroupInputFieldsUseCase.ValidationResult.AMOUNT_MISSING,
            underTest(
                amount,
                listOf(mock()),
                "title"
            )
        )

        whenever(stripCurrencyAndGroupingSeparatorsUseCase(amount)).thenReturn(" ")

        assertEquals(
            ValidateAddGroupInputFieldsUseCase.ValidationResult.AMOUNT_MISSING,
            underTest(
                amount,
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
        val amount = "$1,999"
        whenever(stripCurrencyAndGroupingSeparatorsUseCase(amount)).thenReturn("1999")

        assertEquals(
            ValidateAddGroupInputFieldsUseCase.ValidationResult.CONTACTS_MISSING,
            underTest(
                amount,
                null,
                "Title"
            )
        )

        assertEquals(
            ValidateAddGroupInputFieldsUseCase.ValidationResult.CONTACTS_MISSING,
            underTest(
                amount,
                emptyList(),
                "Title"
            )
        )
    }

    @Test
    fun validateReturnsValidWhenAllParametersArePresent() {
        val amount = "$23"
        whenever(stripCurrencyAndGroupingSeparatorsUseCase(amount)).thenReturn("23")

        assertEquals(
            ValidateAddGroupInputFieldsUseCase.ValidationResult.VALID,
            underTest(
                amount,
                listOf(mock()),
                "Title"
            )
        )
    }
}
