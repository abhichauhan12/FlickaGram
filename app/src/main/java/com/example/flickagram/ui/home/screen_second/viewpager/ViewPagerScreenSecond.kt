package com.example.flickagram.ui.home.screen_second.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.example.flickagram.R
import com.example.flickagram.databinding.FragmentViewPagerScreenSecondBinding
import com.example.flickagram.domian.model.Photo
import com.example.flickagram.ui.home.screen_second.PhotoScreenSecond
import com.example.flickagram.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerScreenSecond : Fragment(R.layout.fragment_view_pager_screen_second) {

    private lateinit var binding : FragmentViewPagerScreenSecondBinding
    private val homeViewModel by activityViewModels<HomeViewModel>()

    private val viewPagerAdapter by lazy { ViewPagerAdapter(fragment = this) }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentViewPagerScreenSecondBinding.bind(view)
        binding.lifecycleOwner=this

        attachObservers()

        setUpViewPager()

    }

    private fun attachObservers() {
        lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.photoList.collect { viewPagerAdapter.addFragments(it) }
            }
        }
    }

    private fun setUpViewPager() {
        val position = requireArguments().getInt("position")
        binding.viewPagerContainer.adapter = viewPagerAdapter
        binding.viewPagerContainer.post {
            binding.viewPagerContainer.setCurrentItem(
                position,
                false
            )
        }

        binding.viewPagerContainer.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(currentPosition: Int) {
                super.onPageSelected(currentPosition)

                if (homeViewModel.hasNextPage) {
                    val currentTotalItems = homeViewModel.photoList.value.size

                    if (currentPosition >= currentTotalItems - 5) homeViewModel.getPhotos()
                }
            }
        })    }

}