package com.s95ammar.contactsapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.s95ammar.contactsapp.model.db.entity.Contact
import com.s95ammar.contactsapp.model.repository.Repository

class ContactsListViewModel : ViewModel() {

    val contacts: LiveData<List<Contact>> = Repository.getAllContacts()


}