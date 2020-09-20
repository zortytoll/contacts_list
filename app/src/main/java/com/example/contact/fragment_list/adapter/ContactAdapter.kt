package com.example.contact.fragment_list.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.R
import com.example.contact.model.User
import kotlinx.android.synthetic.main.view_contact.view.*
import java.io.File

class ContactAdapter(val listener: (User) -> Unit) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private var contactslist: List<User> = emptyList()

    fun setItems(list: List<User>) {
        this.contactslist = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_contact, parent, false)

    )

    override fun getItemCount() = contactslist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contactslist[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: User) {
            itemView.viewFirstNameList.text = item.firstName
            itemView.viewLastNameList.text = item.lastName
            itemView.viewPhone.text = item.phone
            itemView.viewPerson.setImageURI(Uri.fromFile(File(item.photo)))
            itemView.setOnClickListener { listener(item) }
        }
    }
}