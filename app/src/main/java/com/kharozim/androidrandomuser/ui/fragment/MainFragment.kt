package com.kharozim.androidrandomuser.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kharozim.androidrandomuser.R
import com.kharozim.androidrandomuser.adapters.UserAdapter
import com.kharozim.androidrandomuser.databinding.FragmentMainBinding
import com.kharozim.androidrandomuser.viewmodels.UserViewModel
import com.kharozim.androidrandomuser.viewmodels.UserViewModelFactory


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val adapter by lazy { UserAdapter(requireContext()) }
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        setViewModel()
        setData()
        setObserver()
        binding.rvFragmentMain.adapter = adapter
        return binding.root


    }

    private fun setObserver() {
        userViewModel.users.observe(viewLifecycleOwner, {
            adapter?.setData(it)
        })
    }

    private fun setData() {
        userViewModel.setAlluser()
    }

    private fun setViewModel() {
        userViewModel = ViewModelProviders.of(this, UserViewModelFactory(requireContext()))
            .get(UserViewModel::class.java)
    }
}