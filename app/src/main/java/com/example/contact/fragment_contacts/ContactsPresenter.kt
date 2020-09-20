package com.example.contact.fragment_contacts

import com.example.contact.fragment_list.ListStorage.contactsList
import com.example.contact.model.User

class ContactsPresenter(private val view: Contract.View) : Contract.Presenter {

    override fun saveUser(
        firstName: String?,
        lastName: String?,
        phone: String?,
        photoPath: String?
    ) {
        contactsList.add(
            User(
                firstName.orEmpty(),
                lastName.orEmpty(),
                phone.orEmpty(),
                photoPath.orEmpty()
            )
        )
    }

    override fun deleteUser(
        firstName: String?,
        lastName: String?,
        phone: String?,
        photoPath: String?
    ) {
        contactsList.remove(
            User(
                firstName.orEmpty(),
                lastName.orEmpty(),
                phone.orEmpty(),
                photoPath.orEmpty()
            )
        )
    }

    override fun refactorUser(
        firstName: String?,
        lastName: String?,
        phone: String?,
        photoPath: String?
    ) {
        if (contactsList.size >= 0) {
            contactsList[0] = User(
                firstName.orEmpty(),
                lastName.orEmpty(),
                phone.orEmpty(),
                photoPath.orEmpty()
            )
        }
    }

    override fun intentTakePicture() {
        view.takePicture()
    }
}