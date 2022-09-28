package com.example.contactsapp.ui.contacts.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.contactsapp.data.model.Contact

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContactRow(contact: Contact, onDelete: () -> Unit, onUpdate: () -> Unit) {
    Card(
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(),
        onClick = { onUpdate() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = contact.contactName, modifier = Modifier.weight(0.6f))
            Text(text = contact.id.toString(), modifier = Modifier.weight(0.1f))
            IconButton(onClick = { onDelete() }, modifier = Modifier.weight(0.1f)) {
                Icon(Icons.Default.Delete, "", tint = MaterialTheme.colors.onSurface)
            }
            IconButton(onClick = { onUpdate() }, modifier = Modifier.weight(0.1f)) {
                Icon(Icons.Default.Edit, "", tint = MaterialTheme.colors.onSurface)
            }
        }
    }
}