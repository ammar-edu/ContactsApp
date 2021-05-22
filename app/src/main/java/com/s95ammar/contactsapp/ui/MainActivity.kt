package com.s95ammar.contactsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.s95ammar.contactsapp.R
import com.s95ammar.contactsapp.ui.list.ContactsListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.fragments.isEmpty())
            addContactsFragment()
    }

    private fun addContactsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ContactsListFragment.newInstance())
            .commit()
    }
}