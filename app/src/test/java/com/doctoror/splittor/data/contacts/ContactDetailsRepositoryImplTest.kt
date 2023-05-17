package com.doctoror.splittor.data.contacts

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns
import android.provider.ContactsContract
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ContactDetailsRepositoryImplTest {

    private val contentResolver: ContentResolver = mock()

    private val underTest = ContactDetailsRepositoryImpl(contentResolver)

    @Test
    fun getsContactDetailsForUri() = runTest {
        val uri: Uri = mock()
        val id = 22L
        val name = "name"
        val cursor: Cursor = mock {
            whenever(it.moveToFirst()).thenReturn(true)

            whenever(it.getColumnIndex(BaseColumns._ID))
                .thenReturn(0)

            whenever(it.getColumnIndex(ContactsContract.CommonDataKinds.Contactables.DISPLAY_NAME))
                .thenReturn(1)

            whenever(it.getLong(0)).thenReturn(id)
            whenever(it.getString(1)).thenReturn(name)
        }

        whenever(contentResolver.query(uri, null, null, null, null))
            .thenReturn(cursor)

        val output = underTest.getForUri(uri)

        assertFalse(output.isEmpty)
        assertEquals(id, output.get().id)
        assertEquals(name, output.get().name)
    }
}
