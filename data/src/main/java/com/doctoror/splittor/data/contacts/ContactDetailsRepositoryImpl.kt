package com.doctoror.splittor.data.contacts

import android.content.ContentResolver
import android.provider.BaseColumns
import android.provider.ContactsContract
import android.util.Log
import com.doctoror.splittor.data.util.UriParser
import com.doctoror.splittor.domain.contacts.ContactDetails
import com.doctoror.splittor.domain.contacts.ContactDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.Optional

internal class ContactDetailsRepositoryImpl(
    private val contentResolver: ContentResolver,
    private val dispatcherIo: CoroutineDispatcher,
    private val uriParser: UriParser
) : ContactDetailsRepository {

    private val tag by lazy { "ContactDetailsResolverImpl" }

    override suspend fun getForUri(uri: String): Optional<ContactDetails> =
        withContext(dispatcherIo) {
            contentResolver
                .query(uriParser.parse(uri), null, null, null, null)
                ?.use {
                    if (!it.moveToFirst()) {
                        Log.w(tag, "Got empty Cursor for Uri: $uri")
                        return@withContext Optional.empty()
                    }

                    val idColumnIndex = it.getColumnIndex(BaseColumns._ID)

                    val nameColumnIndex =
                        it.getColumnIndex(ContactsContract.CommonDataKinds.Contactables.DISPLAY_NAME)

                    if (idColumnIndex == -1 || nameColumnIndex == -1) {
                        Log.w(tag, "No _ID or DISPLAY_NAME column for Uri: $uri")
                        return@withContext Optional.empty()
                    }

                    val name = it.getString(nameColumnIndex)
                    if (name == null) {
                        Log.w(tag, "No DISPLAY_NAME for contact for Uri: $uri")
                        return@withContext Optional.empty()
                    }

                    return@withContext Optional.of(
                        ContactDetails(
                            id = it.getLong(idColumnIndex),
                            name = name
                        )
                    )
                }

            return@withContext Optional.empty()
        }
}
