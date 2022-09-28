package com.example.contactsapp.ui.contact

import com.example.contactsapp.data.model.Contact

sealed class ContactEvent {
    data class UpdateContact(val contact: Contact) : ContactEvent()
    object SaveContact : ContactEvent()
}