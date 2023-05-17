package com.doctoror.splittor.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.doctoror.splittor.data.groups.GroupEntity
import com.doctoror.splittor.data.groups.GroupMemberEntity
import com.doctoror.splittor.data.groups.GroupsDao

@Database(
    entities = [
        GroupEntity::class,
        GroupMemberEntity::class
    ],
    version = 1
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun groupsDao(): GroupsDao
}
