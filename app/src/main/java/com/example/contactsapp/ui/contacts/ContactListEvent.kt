package com.example.contactsapp.ui.contacts

import com.example.contactsapp.data.model.Contact

sealed class ContactListEvent {
    data class DeleteContactById(val contact: Contact) : ContactListEvent()
    data class ChangeSearchString(val searchString: String) : ContactListEvent()
    object EnableSearch : ContactListEvent()
    object DisableSearch : ContactListEvent()
}