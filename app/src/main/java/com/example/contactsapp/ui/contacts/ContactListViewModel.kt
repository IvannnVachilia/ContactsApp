package com.example.contactsapp.ui.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.data.model.Contact
import com.example.contactsapp.data.repo.ContactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(private val contactsRepo: ContactsRepository) :
    ViewModel() {

    private var _state = MutableStateFlow(ContactListState())
    val state = this._state
    private var _savedAddressBook: Flow<List<Contact>> = contactsRepo.getContacts()
    val savedAddressBook: Flow<List<Contact>> = contactsRepo.getContacts()

    init {
        loadContacts()
    }

    fun onEvent(event: ContactListEvent) {
        when (event) {
            is ContactListEvent.DeleteContactById  -> {
                deleteContactFromList(event.contact)
            }
            ContactListEvent.DisableSearch         -> {
                this._state.value = this._state.value.copy(isEnabledSearched = false)
            }
            ContactListEvent.EnableSearch          -> {
                this._state.value = this._state.value.copy(isEnabledSearched = true)
            }
            is ContactListEvent.ChangeSearchString -> {
                this._state.value = this._state.value.copy(searchString = event.searchString)
            }
        }
    }

    private fun deleteContactFromList(contact: Contact) {
        viewModelScope.launch {
            contactsRepo.delete(contact)
        }
    }

    private fun loadContacts() {
        viewModelScope.launch {
            val contacts = contactsRepo.getContacts()
            _savedAddressBook = contacts
        }
    }
}