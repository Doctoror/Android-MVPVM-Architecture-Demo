package com.doctoror.splittor.data.util

import android.net.Uri
import org.junit.Assert.assertEquals
import org.junit.Test

class UriParserTest {

    private val underTest = UriParser()

    @Test
    fun parsesUri() {
        val uriString = "content://com.android.contacts/contacts/lookup/0r2-2C462C/1"

        val output = underTest.parse(uriString)

        assertEquals(Uri.parse(uriString), output)
    }
}
