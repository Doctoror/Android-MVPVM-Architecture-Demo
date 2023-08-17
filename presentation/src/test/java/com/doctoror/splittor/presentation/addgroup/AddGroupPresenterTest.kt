package com.doctoror.splittor.presentation.addgroup

import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.contacts.GetContactDetailsUseCase
import com.doctoror.splittor.domain.groups.InsertGroupUseCase
import com.doctoror.splittor.domain.groups.ValidateAddGroupInputFieldsUseCase
import com.doctoror.splittor.domain.numberformat.StripCurrencyAndGroupingSeparatorsUseCase
import com.doctoror.splittor.presentation.R
import com.doctoror.splittor.presentation.base.MainDispatcherRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever
import java.util.Optional

class AddGroupPresenterTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getContactDetailsUseCase: GetContactDetailsUseCase = mock()
    private val insertGroupUseCase: InsertGroupUseCase = mock()
    private val stripCurrencyAndGroupingSeparatorsUseCase: StripCurrencyAndGroupingSeparatorsUseCase =
        mock()
    private val validateAddGroupInputFieldsUseCase: ValidateAddGroupInputFieldsUseCase = mock()
    private val viewModel: AddGroupViewModel = mock()
    private val viewModelUpdater: AddGroupViewModelUpdater = mock()

    private val underTest = AddGroupPresenter(
        Dispatchers.Unconfined,
        getContactDetailsUseCase,
        insertGroupUseCase,
        stripCurrencyAndGroupingSeparatorsUseCase,
        validateAddGroupInputFieldsUseCase,
        viewModel,
        viewModelUpdater
    )

    @Test
    fun handlesAmountChange() {
        val amount = "amount"

        underTest.handleAmountChange(amount)

        verify(viewModelUpdater).updateAmount(viewModel, amount)
    }

    @Test
    fun handlesTitleChange() {
        val title = "title"

        underTest.handleTitleChange(title)

        verify(viewModelUpdater).updateTitle(viewModel, title)
    }

    @Test
    fun handlesContactPick() = runTest {
        val uri = "content://com.android.contacts/contacts/lookup/0r2-2C462C/84"
        val contactDetails: ContactDetails = mock()
        whenever(getContactDetailsUseCase(uri)).thenReturn(Optional.of(contactDetails))

        underTest.handleContactPick(uri)

        verify(viewModelUpdater).addContact(viewModel, contactDetails)
    }

    @Test
    fun handleContactPickDoesNothingIfFetchedContactDetailsAreMissing() = runTest {
        val uri = "content://com.android.contacts/contacts/lookup/0r2-2C462C/143"
        whenever(getContactDetailsUseCase(uri))
            .thenReturn(Optional.empty<ContactDetails>())

        underTest.handleContactPick(uri)

        verifyNoInteractions(viewModelUpdater)
    }

    @Test
    fun createGroupSetsErrorMessageWhenAmountMissing() = runTest {
        whenever(
            validateAddGroupInputFieldsUseCase(
                viewModel.amount,
                viewModel.contacts,
                viewModel.title
            )
        ).thenReturn(ValidateAddGroupInputFieldsUseCase.ValidationResult.AMOUNT_MISSING)

        underTest.createGroup()

        verify(viewModelUpdater).setErrorMessageId(viewModel, R.string.amount_not_set)
    }

    @Test
    fun createGroupSetsErrorMessageWhenContactsAreMissing() = runTest {
        whenever(
            validateAddGroupInputFieldsUseCase(
                viewModel.amount,
                viewModel.contacts,
                viewModel.title
            )
        ).thenReturn(ValidateAddGroupInputFieldsUseCase.ValidationResult.CONTACTS_MISSING)

        underTest.createGroup()

        verify(viewModelUpdater).setErrorMessageId(viewModel, R.string.no_contacts_added)
    }

    @Test
    fun createGroupSetsErrorMessageWhenTitleIsMissing() = runTest {
        whenever(
            validateAddGroupInputFieldsUseCase(
                viewModel.amount,
                viewModel.contacts,
                viewModel.title
            )
        ).thenReturn(ValidateAddGroupInputFieldsUseCase.ValidationResult.TITLE_MISSING)

        underTest.createGroup()

        verify(viewModelUpdater).setErrorMessageId(viewModel, R.string.title_not_set)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertsGroupWhenFieldsAreValid() {
        val amount = "amount"
        val contacts = emptyList<ContactDetailsViewModel>()
        val title = "title"
        whenever(viewModel.amount).thenReturn(amount)
        whenever(viewModel.contacts).thenReturn(contacts)
        whenever(viewModel.title).thenReturn(title)

        whenever(validateAddGroupInputFieldsUseCase(amount, contacts, title))
            .thenReturn(ValidateAddGroupInputFieldsUseCase.ValidationResult.VALID)

        val amountStripped = "amountStripped"
        whenever(stripCurrencyAndGroupingSeparatorsUseCase(amount)).thenReturn(amountStripped)

        var actualInsertedId = -1L
        val expectedInsertionResult = 1L
        runTest(UnconfinedTestDispatcher()) {
            whenever(insertGroupUseCase(amountStripped, contacts.map { it.name }, title))
                .thenReturn(expectedInsertionResult)

            val collectJob = launch {
                underTest.groupInsertedEvents.collect { actualInsertedId = it }
            }

            underTest.createGroup()
            collectJob.cancel()
        }

        assertEquals(expectedInsertionResult, actualInsertedId)
    }
}
