package com.s95ammar.contactsapp.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.s95ammar.contactsapp.model.db.dao.ContactsDao
import com.s95ammar.contactsapp.model.db.entity.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactsDatabase : RoomDatabase() {

    companion object {
        const val NAME = "contacts_db"
    }

    abstract val contactsDao: ContactsDao
}
