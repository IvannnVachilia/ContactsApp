package com.example.contactsapp.ui.contact.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TextField(
    property: String,
    bottomPadding: Dp = 5.dp,
    propertyLabel: String,
    onChangeText: (text: String) -> Unit,
) {
    androidx.compose.material.TextField(
        value = property,
        onValueChange = { text -> onChangeText(text) },
        label = { Text(propertyLabel) }
    )
    Spacer(modifier = Modifier.height(bottomPadding))
}