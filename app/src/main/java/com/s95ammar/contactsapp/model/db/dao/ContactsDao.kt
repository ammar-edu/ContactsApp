package com.s95ammar.contactsapp.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.s95ammar.contactsapp.model.db.entity.Contact
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ContactsDao {

    @Insert
    fun insert(contact: Contact): Single<Long>

    @Update
    fun update(contact: Contact): Completable

    @Query("DELETE FROM contact WHERE id = :id")
    fun deleteById(id: Int): Completable

    @Query("SELECT * FROM contact")
    fun getAllContacts(): LiveData<List<Contact>>

    @Query("SELECT * FROM contact WHERE id = :id")
    fun getContactById(id: Int): Single<List<Contact>>

}
