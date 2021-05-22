package com.s95ammar.contactsapp.ui.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.s95ammar.contactsapp.model.db.entity.Contact
import com.s95ammar.contactsapp.model.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ContactCreateEditViewModel : ViewModel() {

    private val _editedContact = MutableLiveData<Contact>()

    val editedContact: LiveData<Contact> = _editedContact

    fun setEditedContactId(id: Int) {
        if (id != -1 && _editedContact.value == null) {
            Repository.getContactById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ contactAsList ->
                    _editedContact.value = contactAsList.singleOrNull()
                }, {
                    // handle error
                })
        }
    }

    fun save(name: String, number: String) {
        val editedContact = _editedContact.value
        if (editedContact == null) { // edit mode
            val contactToInsert = Contact(name, number.toIntOrNull() ?: 0)
            Repository.insert(contactToInsert)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ id ->
                    // handle success
                }, {
                    // handle error
                })

        } else { // create mode
            val contactToUpdate = Contact(name, number.toIntOrNull() ?: 0)
            contactToUpdate.id = editedContact.id

            Repository.update(contactToUpdate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    // handle success
                }, {
                    // handle error
                })
        }


    }
}