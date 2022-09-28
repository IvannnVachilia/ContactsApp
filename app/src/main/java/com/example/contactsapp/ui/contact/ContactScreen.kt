package com.example.contactsapp.ui.contact

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.contactsapp.ui.contact.component.TextFiledGroup

@Composable
fun ContactScreen(viewModel: ContactViewModel, goBack: () -> Unit) {
    val contact by viewModel.contact.collectAsState()

    Scaffold(
        backgroundColor = Color.LightGray,
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = contact.contactName,
                            modifier = Modifier
                                .weight(1f)
                        )
                        IconButton(onClick = {
                            viewModel.eventHandler(ContactEvent.SaveContact)
                            goBack()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "",
                                tint = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { goBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                })
        }
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .fillMaxWidth()
                .verticalScroll(state = scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextFiledGroup(
                list = listOf(
                    Property(
                        contact.contactName,
                        "Contact Name",
                    ) { text ->
                        viewModel.eventHandler(ContactEvent.UpdateContact(contact.copy(contactName = text)))
                    },
                    Property(
                        property = contact.contactTitle,
                        propertyLabel = "Contact Title"
                    ) { text ->
                        viewModel.eventHandler(ContactEvent.UpdateContact(contact.copy(contactTitle = text)))
                    }
                ),
                groupName = "Name"
            )
            TextFiledGroup(
                list = listOf(
                    Property(
                        property = contact.phone,
                        propertyLabel = "Phone"
                    ) { text ->
                        viewModel.eventHandler(ContactEvent.UpdateContact(contact.copy(phone = text)))
                    },
                    Property(
                        property = contact.email,
                        propertyLabel = "Email"
                    ) { text ->
                        viewModel.eventHandler(ContactEvent.UpdateContact(contact.copy(email = text)))
                    }
                ),
                groupName = "Contact Info"
            )
            TextFiledGroup(
                list =
                listOf(
                    Property(
                        property = contact.companyName,
                        propertyLabel = "Company Name"
                    ) { text ->
                        viewModel.eventHandler(ContactEvent.UpdateContact(contact.copy(companyName = text)))
                    }
                ),
                groupName = "Company"
            )
            TextFiledGroup(
                list = listOf(
                    Property(
                        property = contact.country,
                        propertyLabel = "Country"
                    ) { text ->
                        viewModel.eventHandler(ContactEvent.UpdateContact(contact.copy(country = text)))
                    },
                    Property(
                        property = contact.city,
                        propertyLabel = "City"
                    ) { text ->
                        viewModel.eventHandler(ContactEvent.UpdateContact(contact.copy(city = text)))
                    },
                    Property(
                        property = contact.address,
                        propertyLabel = "Address"
                    ) { text ->
                        viewModel.eventHandler(ContactEvent.UpdateContact(contact.copy(address = text)))
                    },
                    Property(
                        property = contact.fax.toString(),
                        propertyLabel = "Fax"
                    ) { text ->
                        viewModel.eventHandler(ContactEvent.UpdateContact(contact.copy(fax = text)))
                    }),
                groupName = "Address"
            )
        }
    }
}

data class Property(
    val property: String,
    val propertyLabel: String,
    val onChangeText: (text: String) -> Unit,
)