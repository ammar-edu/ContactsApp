package com.s95ammar.contactsapp.model.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val name: String,
    val number: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
