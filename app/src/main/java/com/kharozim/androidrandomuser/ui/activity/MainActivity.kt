package com.kharozim.androidrandomuser.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.kharozim.androidrandomuser.databinding.ActivityMainBinding
import com.kharozim.androidrandomuser.ui.fragment.MainFragment
import com.kharozim.androidrandomuser.ui.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val pages = listOf(
            Page("Users", MainFragment()),
            Page("Profile", ProfileFragment())
        )
        val adapter = MainViewPagerAdapter(pages, supportFragmentManager, lifecycle)

        binding.vpMain.adapter = adapter
        TabLayoutMediator(binding.tlMain, binding.vpMain) { tab, index ->
            tab.text = pages[index].title
        }.attach()
    }

    data class Page(
        val title: String,
        val fragment: Fragment
    )

    class MainViewPagerAdapter(
        private val list: List<Page>,
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle
    ) : FragmentStateAdapter(fragmentManager, lifecycle) {

        override fun getItemCount(): Int = list.size

        override fun createFragment(position: Int): Fragment {
            return list[position].fragment
        }

    }
}