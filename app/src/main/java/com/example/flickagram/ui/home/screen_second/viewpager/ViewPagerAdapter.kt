package com.example.flickagram.ui.home.screen_second.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.flickagram.domian.model.Photo
import com.example.flickagram.ui.home.screen_second.PhotoScreenSecond

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = ArrayList<Fragment>()

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]

    fun addFragments(photos: List<Photo>) {
        val currentItemsCount = fragments.size

        if (photos.size != currentItemsCount) {
            val totalItems = photos.size
            photos.subList(currentItemsCount, totalItems).forEachIndexed { index, photo ->
                fragments.add(PhotoScreenSecond.getInstance(photo))
                notifyItemChanged(index)
            }
        }
    }
}