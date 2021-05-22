package com.s95ammar.contactsapp.model.repository

import androidx.lifecycle.LiveData
import androidx.room.Room
import com.s95ammar.contactsapp.ContactsApp
import com.s95ammar.contactsapp.model.db.ContactsDatabase
import com.s95ammar.contactsapp.model.db.entity.Contact
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

object Repository {

    private val db: ContactsDatabase = createDatabase()

    fun insert(contact: Contact): Single<Long> {
        return db.contactsDao.insert(contact)
    }

    fun update(contact: Contact): Completable {
        return db.contactsDao.update(contact)
    }

    fun deleteById(id: Int): Completable {
        return db.contactsDao.deleteById(id)
    }

    fun getAllContacts(): LiveData<List<Contact>> {
        return db.contactsDao.getAllContacts()
    }

    fun getContactById(id: Int): Single<List<Contact>> {
        return db.contactsDao.getContactById(id)
    }

    private fun createDatabase(): ContactsDatabase {
        return Room.databaseBuilder(
            ContactsApp.instance!!,
            ContactsDatabase::class.java,
            ContactsDatabase.NAME
        ).build()
    }

}