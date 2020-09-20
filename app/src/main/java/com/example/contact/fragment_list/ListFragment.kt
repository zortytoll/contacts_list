package com.example.contact.fragment_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.contact.R
import com.example.contact.fragment_list.ListStorage.contactsList
import com.example.contact.fragment_list.adapter.ContactAdapter
import com.example.contact.model.User
import com.example.contact.navigator.Navigator
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val adapter = ContactAdapter(::navigateToContacts)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = adapter
        adapter.setItems(contactsList)
        fabAdd.setOnClickListener {
            val navigator = activity as Navigator
            navigator.navigateToContacts()
        }
    }

    private fun navigateToContacts(user: User) {
        val navigator = activity as Navigator
        navigator.navigateToUser(user)
    }
}
