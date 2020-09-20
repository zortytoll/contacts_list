package com.example.contact.fragment_contacts

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.contact.R
import com.example.contact.model.User
import com.example.contact.navigator.Navigator
import kotlinx.android.synthetic.main.fragment_add_contact.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ContactFragment : Fragment(), Contract.View {

    private var currentPhotoPath: String = ""
    private val presenter: Contract.Presenter = ContactsPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_add_contact, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        arguments?.getParcelable<User>(USER_KEY)?.let { user ->
            editFirstName.setText(user.firstName)
            editLastName.setText(user.lastName)
            editInputPhone.setText(user.phone)
            viewPhoto.setImageURI(Uri.parse(user.photo))
        }
        viewPhoto.setOnClickListener { presenter.intentTakePicture() }
    }

    override fun initToolbar() {
        toolbar.setOnMenuItemClickListener { menu ->
            when (menu.itemId) {
                R.id.action_add -> {
                    presenter.saveUser(
                        editFirstName.text.toString(),
                        editLastName.text.toString(),
                        editInputPhone.text.toString(),
                        currentPhotoPath
                    )
                    val navigator = activity as Navigator
                    navigator.navigateToList().let { true }
                }
                R.id.action_delete -> {
                    presenter.deleteUser(
                        editFirstName.text.toString(),
                        editLastName.text.toString(),
                        editInputPhone.text.toString(),
                        currentPhotoPath
                    )
                    val navigator = activity as Navigator
                    navigator.navigateToList().let { true }
                }
                R.id.action_refactor -> {
                    presenter.refactorUser(
                        editFirstName.text.toString(),
                        editLastName.text.toString(),
                        editInputPhone.text.toString(),
                        currentPhotoPath
                    )
                    val navigator = activity as Navigator
                    navigator.navigateToList().let { true }
                }
                else -> false
            }
        }

        toolbar.setNavigationOnClickListener {
            val navigator = activity as Navigator
            navigator.navigateToList()
        }
    }

    override fun takePicture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireContext().packageManager).also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireContext(), PHOTO_URI,
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            viewPhoto.setImageURI(Uri.parse(currentPhotoPath))
        }

    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val PHOTO_URI = "com.example.contact.fileprovider"
        private const val USER_KEY: String = "User key"
        fun newInstance(user: User) = ContactFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_KEY, user)
            }
        }
    }
}



