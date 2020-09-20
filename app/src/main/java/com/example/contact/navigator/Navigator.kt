package com.example.contact.navigator

import com.example.contact.model.User

interface Navigator {

    fun navigateToUser(user: User)

    fun navigateToContacts()

    fun navigateToList()
}