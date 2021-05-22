package com.s95ammar.contactsapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.s95ammar.contactsapp.R
import com.s95ammar.contactsapp.model.db.entity.Contact

class ContactsAdapter(
    private val clickListener: (Contact) -> Unit
) : ListAdapter<Contact, ContactsAdapter.ContactsViewHolder>(ContactsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactsViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        getItem(position)?.let { contact ->
            holder.bind(contact)
        }
    }

    class ContactsViewHolder(view: View, private val clickListener: (Contact) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bind(contact: Contact) {
            val textView = itemView.findViewById<TextView>(R.id.contact_name_text_view)

            textView.text = contact.name
            textView.setOnClickListener { clickListener(contact) }
        }

    }

    class ContactsDiffUtil : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }
}