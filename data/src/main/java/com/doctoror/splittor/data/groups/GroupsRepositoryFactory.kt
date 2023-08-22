package com.doctoror.splittor.data.groups

import android.content.Context
import androidx.room.Room
import com.doctoror.splittor.data.MainDatabase
import com.doctoror.splittor.domain.groups.GroupsRepository

class GroupsRepositoryFactory(private val androidContext: Context) {

    private val mainDatabase: MainDatabase by lazy {
        Room
            .databaseBuilder(
                androidContext,
                MainDatabase::class.java, "Main.db"
            )
            .build()
    }

    fun newInstance(): GroupsRepository = RoomGroupsRepository(
        currentTimeProvider = { System.currentTimeMillis() },
        groupsDao = mainDatabase.groupsDao(),
        groupWithMembersMapper = GroupWithMembersMapper()
    )
}