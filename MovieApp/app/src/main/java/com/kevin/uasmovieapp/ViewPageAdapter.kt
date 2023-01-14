package com.kevin.uasmovieapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.manager.Lifecycle

class ViewPageAdapter(fragmentManager: FragmentManager,lifecycle: androidx.lifecycle.Lifecycle):FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
        when(position){
            0-> fragment = MovieFragment()
            1-> fragment = SeriesFragment()
        }
        return fragment
    }
}