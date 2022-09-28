package com.example.contactsapp.data.datasource

import androidx.room.*
import com.example.contactsapp.data.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Query("SELECT * FROM contact")
    fun getContacts(): Flow<List<Contact>>

    @Query("SELECT * FROM contact WHERE id = :id")
    suspend fun getContactById(id: Int): Contact?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)
}