package com.example.contactsapp.ui.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.contactsapp.SharedViewModel
import com.example.contactsapp.data.model.emptyContact
import com.example.contactsapp.ui.contact.ContactScreen
import com.example.contactsapp.ui.contact.ContactViewModel
import com.example.contactsapp.ui.contacts.ContactListScreen
import com.example.contactsapp.ui.contacts.ContactListViewModel
import kotlinx.coroutines.Job

enum class NavPath(
    val route: String,
) {

    ContactList(route = "ContactList"),
    Contact(route = "Contact"),
}

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    scaffoldState: ScaffoldState,
    sharedViewModel: SharedViewModel,
    openDrawer: () -> Job
) {
    NavHost(
        navController = navHostController,
        startDestination = NavPath.ContactList.route,
    ) {
        composable(NavPath.ContactList.route) {
            val viewModel: ContactListViewModel = hiltViewModel()

            ContactListScreen(
                viewModel,
                {
                    sharedViewModel.contact = it
                    navHostController.navigate(route = NavPath.Contact.route)
                },
                {
                    sharedViewModel.contact = emptyContact()
                    navHostController.navigate(route = NavPath.Contact.route)
                },
                openDrawer = { openDrawer() }
            )
        }

        composable(NavPath.Contact.route) {
            val viewModel: ContactViewModel = hiltViewModel()

            sharedViewModel.contact?.id?.let { id -> viewModel.setId(id) }
            ContactScreen(viewModel) { navHostController.navigateUp() }
        }
    }
}