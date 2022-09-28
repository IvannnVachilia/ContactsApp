package com.example.contactsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beust.klaxon.Json

data class AddressBook(
    @Json(name = "Contact")
    val contact: List<Contact>
)

@Entity
data class Contact(
    @PrimaryKey
    val id: Int? = null,
    @Json(name = "CustomerID")
    val customerID: String,
    @Json(name = "CompanyName")
    val companyName: String,
    @Json(name = "ContactName")
    val contactName: String,
    @Json(name = "ContactTitle")
    val contactTitle: String,
    @Json(name = "Address")
    val address: String,
    @Json(name = "City")
    val city: String,
    @Json(name = "Email")
    val email: String,
    @Json(name = "PostalCode")
    val postalCode: String? = null,
    @Json(name = "Country")
    val country: String,
    @Json(name = "Phone")
    val phone: String,
    @Json(name = "Fax")
    val fax: String? = null,
    @Json(name = "Region")
    val region: String? = null
)

fun emptyContact(): Contact {
    return Contact(
        null,
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )
}