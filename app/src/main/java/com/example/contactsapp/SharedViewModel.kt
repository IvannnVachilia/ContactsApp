package com.example.contactsapp

import androidx.lifecycle.ViewModel
import com.example.contactsapp.data.model.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    var contact: Contact? = null
}