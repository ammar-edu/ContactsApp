package com.s95ammar.contactsapp.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.s95ammar.contactsapp.R
import com.s95ammar.contactsapp.model.db.entity.Contact
import com.s95ammar.contactsapp.ui.contact.ContactCreateEditFragment
import com.s95ammar.contactsapp.ui.list.adapter.ContactsAdapter

class ContactsListFragment : Fragment() {

    companion object {
        fun newInstance() = ContactsListFragment()
    }

    private val viewModel: ContactsListViewModel by viewModels()

    private val adapter = ContactsAdapter { contact ->
        goToCreateEditFragment(contact)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contacts_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView(view)
        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { goToCreateEditFragment() }
        viewModel.contacts.observe(viewLifecycleOwner) { contacts ->
            adapter.submitList(contacts)
        }
    }

    private fun setUpRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun goToCreateEditFragment(contact: Contact? = null) {
        val contactCreateEditFragment = ContactCreateEditFragment.newInstance(contact?.id ?: -1)

        parentFragmentManager.beginTransaction()
            .replace(R.id.container, contactCreateEditFragment)
            .addToBackStack(null)
            .commit()
    }
}