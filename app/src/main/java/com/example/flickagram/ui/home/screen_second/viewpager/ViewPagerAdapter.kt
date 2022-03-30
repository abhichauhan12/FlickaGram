package com.example.flickagram.ui.home.screen_second.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.flickagram.domian.model.Photo

class ViewPagerAdapter(
    fragment: Fragment,
    private val fragments : List<Fragment>

) : FragmentStateAdapter(fragment){

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}