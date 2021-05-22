package com.s95ammar.contactsapp.ui.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.s95ammar.contactsapp.Constants
import com.s95ammar.contactsapp.R

class ContactCreateEditFragment : Fragment() {

    companion object {
        fun newInstance(contactId: Int) = ContactCreateEditFragment().apply {
            arguments = bundleOf(Constants.KEY_CONTACT_ID to contactId)
        }
    }

    private val viewModel: ContactCreateEditViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contact_create_edit_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nameEditText = view.findViewById<EditText>(R.id.name_edit_text)
        val numberEditText = view.findViewById<EditText>(R.id.number_edit_text)

        view.findViewById<Button>(R.id.save_button).setOnClickListener {
            viewModel.save(
                name = nameEditText.text.toString(),
                number = numberEditText.text.toString()
            )
        }
        viewModel.setEditedContactId(arguments?.getInt(Constants.KEY_CONTACT_ID) ?: -1)
        viewModel.editedContact.observe(viewLifecycleOwner) {
            nameEditText.setText(it.name)
            numberEditText.setText(it.number.toString())
        }
    }
}