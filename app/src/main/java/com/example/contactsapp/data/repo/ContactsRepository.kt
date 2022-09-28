package com.example.contactsapp.data.repo

import com.example.contactsapp.data.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {

    fun getContacts(): Flow<List<Contact>>

    suspend fun getContactById(id: Int): Contact?

    suspend fun insert(contact: Contact)

    suspend fun delete(contact: Contact)
}