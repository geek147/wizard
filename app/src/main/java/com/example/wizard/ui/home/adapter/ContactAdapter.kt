package com.example.wizard.ui.home.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.wizard.R
import com.example.wizard.databinding.ItemRowBinding
import com.example.wizard.model.Contacts
import com.example.wizard.ui.detail.DetailFragment

class ContactAdapter(private var fragment: Fragment) : RecyclerView.Adapter<ContactAdapter.MainViewHolder>() {
    private var listContact: MutableList<Contacts.Contact> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemBinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return if (listContact.isEmpty()) {
            0
        } else {
            listContact.size
        }
    }

    fun setData(list: List<Contacts.Contact>) {
        this.listContact.clear()
        this.listContact.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        listContact[holder.bindingAdapterPosition].let {
            holder.bindData(it, fragment)
        }
    }

    class MainViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(model: Contacts.Contact, fragment: Fragment) {


            binding.textViewTitle.text = model.firstName

            val bundle = Bundle()
            bundle.putParcelable(DetailFragment.EXTRA_CONTACT_DETAIL, model)
            itemView.setOnClickListener {
                fragment.findNavController()
                    .navigate(R.id.action_listContact_to_detailFragment, bundle)

            }
        }
    }
}
