package com.example.contact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.contact.fragment_contacts.ContactFragment
import com.example.contact.fragment_list.ListFragment
import com.example.contact.model.User
import com.example.contact.navigator.Navigator

class MainActivity : AppCompatActivity(), Navigator {
    override fun navigateToUser(user: User) {
        supportFragmentManager.commit {
            replace(android.R.id.content, ContactFragment.newInstance(user))
            addToBackStack(null)
        }
    }

    override fun navigateToContacts() {
        supportFragmentManager.commit {
            replace(android.R.id.content, ContactFragment())
            addToBackStack(null)
        }
    }

    override fun navigateToList() {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(android.R.id.content, ListFragment())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToList()
    }
}
