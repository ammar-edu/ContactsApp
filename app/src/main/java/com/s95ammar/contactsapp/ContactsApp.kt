package com.s95ammar.contactsapp

import android.app.Application

class ContactsApp : Application() {

    companion object {
        var instance: ContactsApp? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}