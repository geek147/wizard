package com.example.wizard.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wizard.R
import com.example.wizard.databinding.FragmentHomeBinding
import com.example.wizard.ui.base.BaseFragment
import com.example.wizard.ui.home.adapter.ContactAdapter
import com.example.wizard.utils.Intent
import com.example.wizard.utils.State
import com.example.wizard.utils.ViewState

class HomeFragment :  BaseFragment<Intent,
        State>(){

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ContactAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            invalidate(it)
        }

        setupRecyclerView()
        context?.let { Intent.GetData(it) }?.let { viewModel.onIntentReceived(it) }
    }

    override val layoutResourceId: Int
        get() = R.layout.fragment_home


    private fun setupRecyclerView() {
        with(binding) {
            recyclerView.setHasFixedSize(true)
            val gridLayoutManager = GridLayoutManager(requireContext(), 2)
            recyclerView.layoutManager = gridLayoutManager
            recyclerView.itemAnimator = null
            adapter = ContactAdapter(this@HomeFragment)
            adapter.setHasStableIds(true)
            recyclerView.adapter = adapter
        }
    }

    override fun invalidate(state: State) {
        with(binding) {
            pgProgressList.visibility = if (state.showLoading) View.VISIBLE else View.GONE
        }

        when (state.viewState) {
            ViewState.EmptyGetData -> {
                with(binding) {
                    errorView.visibility = View.VISIBLE
                    errorView.run {
                        setUpErrorView(
                            title = resources.getString(R.string.empty_state_title),
                            message = resources.getString(R.string.empty_state_message)
                        )
                    }
                    adapter.setData(emptyList())
                    recyclerView.visibility = View.GONE
                }
            }
            ViewState.Error -> {
                with(binding) {
                    errorView.visibility = View.VISIBLE
                    errorView.run {
                        setUpErrorView()
                    }
                    adapter.setData(emptyList())
                    recyclerView.visibility = View.GONE
                }
            }

            ViewState.Idle -> {}
            ViewState.SuccessGetData -> {
                with(binding) {
                    //common recycler
                    recyclerView.visibility = View.VISIBLE
                    adapter.setData(state.listData)
                    errorView.visibility = View.GONE
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}