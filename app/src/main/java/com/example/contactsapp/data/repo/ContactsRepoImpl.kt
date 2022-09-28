package com.example.contactsapp.data.repo

import com.example.contactsapp.data.datasource.ContactDao
import com.example.contactsapp.data.model.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactsRepoImpl @Inject constructor(private val dao: ContactDao) : ContactsRepository {

    override fun getContacts(): Flow<List<Contact>> {
        return dao.getContacts()
    }

    override suspend fun getContactById(id: Int): Contact? {
        return dao.getContactById(id)
    }

    override suspend fun insert(contact: Contact) {
        dao.insert(contact)
    }

    override suspend fun delete(contact: Contact) {
        dao.delete(contact)
    }
}