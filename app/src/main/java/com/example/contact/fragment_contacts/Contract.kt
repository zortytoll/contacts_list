package com.example.contact.fragment_contacts

interface Contract {

    interface View {

        fun takePicture()

        fun initToolbar()
    }

    interface Presenter {

        fun saveUser(firstName: String?, lastName: String?, phone: String?, photoPath: String?)

        fun deleteUser(firstName: String?, lastName: String?, phone: String?, photoPath: String?)

        fun refactorUser(firstName: String?, lastName: String?, phone: String?, photoPath: String?)

        fun intentTakePicture()
    }
}