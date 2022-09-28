package com.example.contactsapp.ui.contacts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactsapp.data.model.Contact
import com.example.contactsapp.ui.contacts.components.ContactRow

@Composable
fun ContactListScreen(
    viewModel: ContactListViewModel,
    toContactScreen: (contact: Contact) -> Unit,
    toCreateNewContactScreen: () -> Unit,
    openDrawer: () -> Unit
) {
    val state by viewModel.state.collectAsState(initial = ContactListState())
    val contacts by viewModel.savedAddressBook.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            if (state.isEnabledSearched) {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding()
                        ) {
                            OutlinedTextField(
                                value = state.searchString,
                                onValueChange = {
                                    viewModel.onEvent(ContactListEvent.ChangeSearchString(it))
                                },
                                modifier = Modifier.wrapContentSize(),
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = Color.White
                                ),
                                textStyle = TextStyle(fontSize = 16.sp)
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = { viewModel.onEvent(ContactListEvent.DisableSearch) },
                            content = {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = ""
                                )
                            }
                        )
                    }
                )
            } else {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "ContactApp", modifier = Modifier.weight(1f))
                            IconButton(onClick = { viewModel.onEvent(ContactListEvent.EnableSearch) }) {
                                Icon(imageVector = Icons.Default.Search, contentDescription = "")
                            }
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { openDrawer() }, content = {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = ""
                            )
                        }
                        )
                    }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                toCreateNewContactScreen()
            }) {
                Icon(Icons.Default.Add, "")
            }
        },
        content = {
            LazyColumn(modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)) {
                items(contacts) { contact ->
                    ContactRow(
                        contact,
                        onDelete = {
                            viewModel.onEvent(
                                ContactListEvent.DeleteContactById(
                                    contact
                                )
                            )
                        },
                        onUpdate = {
                            toContactScreen(contact)
                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    )
}