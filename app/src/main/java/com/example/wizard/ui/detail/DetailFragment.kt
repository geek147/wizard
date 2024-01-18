package com.example.wizard.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wizard.R
import com.example.wizard.databinding.FragmentDetailBinding
import com.example.wizard.model.Contacts
import com.example.wizard.ui.base.BaseFragment
import com.example.wizard.utils.Intent
import com.example.wizard.utils.State

class DetailFragment : BaseFragment<Intent,
        State>()  {

    companion object {
        val TAG = this::class.simpleName
        const val EXTRA_CONTACT_DETAIL = "EXTRA_CONTACT_DETAIL:"
    }



    private var _binding: FragmentDetailBinding? = null

    var contact: Contacts.Contact? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        contact = arguments?.getParcelable(EXTRA_CONTACT_DETAIL)
        return binding.root
    }

    override fun invalidate(state: State) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUIData()
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_detail

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUIData () {
        with(binding) {
            editTextFirstName.setText(contact?.firstName.orEmpty())
            editTextLastName.setText( contact?.lastName.orEmpty())
            editTextEmail.setText(contact?.email.orEmpty())
            editTextDob.setText(contact?.dob.orEmpty())

        }
    }

}