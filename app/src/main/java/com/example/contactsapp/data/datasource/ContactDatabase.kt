package com.example.contactsapp.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactsapp.data.model.Contact

@Database(
    entities = [Contact::class],
    version = 1
)
abstract class ContactDatabase : RoomDatabase() {

    abstract val contactDao: ContactDao
}