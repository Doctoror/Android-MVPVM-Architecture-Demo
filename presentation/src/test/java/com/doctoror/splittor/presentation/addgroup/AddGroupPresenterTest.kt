package com.doctoror.splittor.presentation.addgroup

import app.cash.turbine.test
import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.contacts.GetContactDetailsUseCase
import com.doctoror.splittor.domain.groups.InsertGroupUseCase
import com.doctoror.splittor.domain.groups.ValidateAddGroupInputFieldsUseCase
import com.doctoror.splittor.domain.numberformat.StripCurrencyAndGroupingSeparatorsUseCase
import com.doctoror.splittor.presentation.R
import com.doctoror.splittor.presentation.base.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.anyOrNull
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
        getContactDetailsUseCase,
        insertGroupUseCase,
        stripCurrencyAndGroupingSeparatorsUseCase,
        validateAddGroupInputFieldsUseCase,
        viewModel,
        viewModelUpdater
    )

    @Before
    fun setup() {
        val scope = MainScope()
        underTest.viewModelScopeProvider = { scope }

        whenever(viewModel.amount).thenReturn(MutableStateFlow(""))
        whenever(viewModel.contacts).thenReturn(MutableStateFlow(arrayListOf()))
        whenever(viewModel.title).thenReturn(MutableStateFlow(""))
    }

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
        whenever(validateAddGroupInputFieldsUseCase(anyOrNull(), anyOrNull(), anyOrNull()))
            .thenReturn(ValidateAddGroupInputFieldsUseCase.ValidationResult.AMOUNT_MISSING)

        underTest.createGroup()

        verify(viewModelUpdater).setErrorMessageId(viewModel, Optional.of(R.string.amount_not_set))
    }

    @Test
    fun createGroupSetsErrorMessageWhenContactsAreMissing() = runTest {
        whenever(validateAddGroupInputFieldsUseCase(anyOrNull(), anyOrNull(), anyOrNull()))
            .thenReturn(ValidateAddGroupInputFieldsUseCase.ValidationResult.CONTACTS_MISSING)

        underTest.createGroup()

        verify(viewModelUpdater).setErrorMessageId(
            viewModel,
            Optional.of(R.string.no_contacts_added)
        )
    }

    @Test
    fun createGroupSetsErrorMessageWhenTitleIsMissing() = runTest {
        whenever(validateAddGroupInputFieldsUseCase(anyOrNull(), anyOrNull(), anyOrNull()))
            .thenReturn(ValidateAddGroupInputFieldsUseCase.ValidationResult.TITLE_MISSING)

        underTest.createGroup()

        verify(viewModelUpdater).setErrorMessageId(viewModel, Optional.of(R.string.title_not_set))
    }

    @Test
    fun insertsGroupWhenFieldsAreValid() = runTest {
        val amount = "amount"
        val contacts = arrayListOf<ContactDetailsViewModel>()
        val title = "title"
        whenever(viewModel.amount).thenReturn(MutableStateFlow(amount))
        whenever(viewModel.contacts).thenReturn(MutableStateFlow(contacts))
        whenever(viewModel.title).thenReturn(MutableStateFlow(title))

        whenever(validateAddGroupInputFieldsUseCase(amount, contacts, title))
            .thenReturn(ValidateAddGroupInputFieldsUseCase.ValidationResult.VALID)

        val amountStripped = "amountStripped"
        whenever(stripCurrencyAndGroupingSeparatorsUseCase(amount)).thenReturn(amountStripped)

        val expectedInsertionResult = 1L
        whenever(insertGroupUseCase(amountStripped, contacts.map { it.name }, title))
            .thenReturn(expectedInsertionResult)

        launch {
            underTest
                .groupInsertedEvents
                .filter { it.isPresent }
                .map { it.get() }
                .test { assertEquals(expectedInsertionResult, awaitItem()) }
        }

        underTest.createGroup()
    }
}
