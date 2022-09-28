package com.example.contactsapp.ui.contact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.data.model.Contact
import com.example.contactsapp.data.model.emptyContact
import com.example.contactsapp.data.repo.ContactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val repository: ContactsRepository,
) :
    ViewModel() {

    private var _contact: MutableStateFlow<Contact> = MutableStateFlow(emptyContact())
    val contact = _contact

    fun eventHandler(event: ContactEvent) {
        when (event) {
            ContactEvent.SaveContact      -> saveContact()
            is ContactEvent.UpdateContact -> updateContact(event.contact)
        }
    }

    private fun updateContact(contact: Contact) {
        println("update contact $contact previous contact: ${_contact.value}")
        _contact.value = contact
    }

    private fun saveContact() {
        viewModelScope.launch {
            withContext(NonCancellable) {
                repository.insert(contact.value)
            }
        }
    }

    private fun loadContact(contactId: Int) {
        viewModelScope.launch {
            if (contactId == -1) {
                _contact.value = emptyContact()
                return@launch
            }
            val contactById = repository.getContactById(contactId)
            contactById?.let {
                _contact.value = it
            }
        }
    }

    fun setId(id: Int) {
        loadContact(id)
    }
}