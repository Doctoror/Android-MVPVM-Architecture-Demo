package com.doctoror.splittor.presentation.addgroup

import com.doctoror.splittor.R
import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.contacts.GetContactDetailsUseCase
import com.doctoror.splittor.domain.groups.InsertGroupUseCase
import com.doctoror.splittor.domain.groups.ValidateAddGroupInputFieldsUseCase
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
    private val inputFieldsMonitor: AddGroupInputFieldsMonitor = mock()
    private val insertGroupUseCase: InsertGroupUseCase = mock()
    private val validateAddGroupInputFieldsUseCase: ValidateAddGroupInputFieldsUseCase = mock()
    private val viewModelUpdater: AddGroupViewModelUpdater = mock()

    private val underTest = AddGroupPresenter(
        Dispatchers.Unconfined,
        getContactDetailsUseCase,
        inputFieldsMonitor,
        insertGroupUseCase,
        validateAddGroupInputFieldsUseCase,
        viewModelUpdater
    )

    @Test
    fun handlesContactPick() = runTest {
        val uri = "content://com.android.contacts/contacts/lookup/0r2-2C462C/84"
        val contactDetails: ContactDetails = mock()
        whenever(getContactDetailsUseCase.getForUri(uri)).thenReturn(Optional.of(contactDetails))

        underTest.handleContactPick(uri)

        verify(inputFieldsMonitor).addContact(contactDetails)
        verify(viewModelUpdater).addContact(contactDetails)
    }

    @Test
    fun handleContactPickDoesNothingIfFetchedContactDetailsAreMissing() = runTest {
        val uri = "content://com.android.contacts/contacts/lookup/0r2-2C462C/143"
        whenever(getContactDetailsUseCase.getForUri(uri))
            .thenReturn(Optional.empty<ContactDetails>())

        underTest.handleContactPick(uri)

        verifyNoInteractions(inputFieldsMonitor)
        verifyNoInteractions(viewModelUpdater)
    }

    @Test
    fun createGroupSetsErrorMessageWhenAmountMissing() {
        whenever(
            validateAddGroupInputFieldsUseCase.validate(
                inputFieldsMonitor.amount,
                inputFieldsMonitor.contacts,
                inputFieldsMonitor.title
            )
        ).thenReturn(ValidateAddGroupInputFieldsUseCase.ValidationResult.AMOUNT_MISSING)

        underTest.createGroup()

        verify(viewModelUpdater).setErrorMessageId(R.string.amount_not_set)
    }

    @Test
    fun createGroupSetsErrorMessageWhenContactsAreMissing() {
        whenever(
            validateAddGroupInputFieldsUseCase.validate(
                inputFieldsMonitor.amount,
                inputFieldsMonitor.contacts,
                inputFieldsMonitor.title
            )
        ).thenReturn(ValidateAddGroupInputFieldsUseCase.ValidationResult.CONTACTS_MISSING)

        underTest.createGroup()

        verify(viewModelUpdater).setErrorMessageId(R.string.no_contacts_added)
    }

    @Test
    fun createGroupSetsErrorMessageWhenTitleIsMissing() {
        whenever(
            validateAddGroupInputFieldsUseCase.validate(
                inputFieldsMonitor.amount,
                inputFieldsMonitor.contacts,
                inputFieldsMonitor.title
            )
        ).thenReturn(ValidateAddGroupInputFieldsUseCase.ValidationResult.TITLE_MISSING)

        underTest.createGroup()

        verify(viewModelUpdater).setErrorMessageId(R.string.title_not_set)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertsGroupWhenFieldsAreValid() {
        whenever(inputFieldsMonitor.amount).thenReturn("amount")
        whenever(inputFieldsMonitor.contacts).thenReturn(mutableSetOf())
        whenever(inputFieldsMonitor.title).thenReturn("title")

        whenever(
            validateAddGroupInputFieldsUseCase.validate(
                inputFieldsMonitor.amount,
                inputFieldsMonitor.contacts,
                inputFieldsMonitor.title
            )
        ).thenReturn(ValidateAddGroupInputFieldsUseCase.ValidationResult.VALID)

        var actualInsertedId = -1L
        val expectedInsertionResult = 1L
        runTest(UnconfinedTestDispatcher()) {
            whenever(
                insertGroupUseCase.insert(
                    inputFieldsMonitor.amount!!.toString(),
                    inputFieldsMonitor.contacts.map { it.name },
                    inputFieldsMonitor.title!!.toString()
                )
            ).thenReturn(expectedInsertionResult)

            val collectJob = launch {
                underTest.groupInsertedEvents.collect { actualInsertedId = it }
            }

            underTest.createGroup()
            collectJob.cancel()
        }

        assertEquals(expectedInsertionResult, actualInsertedId)
    }
}
