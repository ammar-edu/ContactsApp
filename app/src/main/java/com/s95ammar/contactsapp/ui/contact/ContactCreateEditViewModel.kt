package com.s95ammar.contactsapp.ui.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.s95ammar.contactsapp.model.db.entity.Contact
import com.s95ammar.contactsapp.model.repository.Repository
import com.s95ammar.contactsapp.ui.BaseViewModel

class ContactCreateEditViewModel : BaseViewModel() {

    private val _editedContact = MutableLiveData<Contact>()

    val editedContact: LiveData<Contact> = _editedContact

    fun setEditedContactId(id: Int) {
        if (id != -1 && _editedContact.value == null) {
            disposeOnCleared(
                Repository.getContactById(id),
                { contactAsList ->
                    _editedContact.value = contactAsList.singleOrNull()
                }, {
                    // handle error
                }
            )
        }
    }

    fun save(name: String, number: String) {
        val editedContact = _editedContact.value
        if (editedContact == null) { // edit mode
            val contactToInsert = Contact(name, number.toIntOrNull() ?: 0)
            disposeOnCleared(
                Repository.insert(contactToInsert),
                { id ->
                    // handle success
                }, {
                    // handle error
                }
            )
        } else { // create mode
            val contactToUpdate = Contact(name, number.toIntOrNull() ?: 0)
            contactToUpdate.id = editedContact.id

            disposeOnCleared(
                Repository.update(contactToUpdate),
                {
                    // handle success
                }, {
                    // handle error
                }
            )
        }


    }
}