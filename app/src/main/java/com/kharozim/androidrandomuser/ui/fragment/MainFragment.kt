package com.kharozim.androidrandomuser.ui.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kharozim.androidrandomuser.R
import com.kharozim.androidrandomuser.adapters.User
import com.kharozim.androidrandomuser.adapters.UserAdapter
import com.kharozim.androidrandomuser.databinding.FragmentMainBinding
import com.kharozim.androidrandomuser.models.UserModel
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
        binding.rvFragmentMain.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = binding.rvFragmentMain.adapter as UserAdapter
                val pos = viewHolder.adapterPosition
                val item = adapter.getData(pos)
                adapter.removeAt(pos)

                val snack =
                    view?.let {
                        Snackbar.make(
                            it,
                            "Item was removed from the list.",
                            Snackbar.LENGTH_LONG
                        )
                    }
                snack?.setAction("UNDO") {
                    adapter.restoreItem(item, pos)
                    binding.rvFragmentMain.scrollToPosition(pos)
                }
                snack?.setActionTextColor(Color.BLUE)
                snack?.show()
            }

        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.rvFragmentMain)


        return binding.root

    }

    private fun setObserver() {
        userViewModel.users.observe(viewLifecycleOwner, {
            generateProduct(it)
        })
    }

    private fun generateProduct(it: List<UserModel>) {
        val list = mutableListOf<User>()
        val sortedList = it.sortedBy { it.name.first }
        var temp = ""

        sortedList.forEach { model ->
            if (temp != model.name.first[0].toUpperCase().toString()) {
                temp = model.name.first[0].toUpperCase().toString()
                list.add(User.Header(temp))
            }
            list.add(User.Row(model))
        }
        adapter.setData(list)


    }

    private fun setData() {
        userViewModel.setAlluser()
    }

    private fun setViewModel() {
        userViewModel = ViewModelProviders.of(this, UserViewModelFactory(this.context))
            .get(UserViewModel::class.java)
    }

}