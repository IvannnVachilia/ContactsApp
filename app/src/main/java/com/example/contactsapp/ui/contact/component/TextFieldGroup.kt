package com.example.contactsapp.ui.contact.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.contactsapp.ui.contact.Property

@Composable
fun TextFiledGroup(list: List<Property>, groupName: String) {
    Card(
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 20.dp, top = 5.dp, bottom = 5.dp, end = 20.dp)
        ) {
            Text(text = groupName, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
            Spacer(modifier = Modifier.height(5.dp))
            list.forEach { it ->
                TextField(
                    property = it.property,
                    propertyLabel = it.propertyLabel,
                    onChangeText = it.onChangeText
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
    Spacer(modifier = Modifier.height(5.dp))
}