package com.example.flickagram.ui.home.screen_second.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(
            fragment = this,
            fragments = homeViewModel.photoList.value.map {
                PhotoScreenSecond.getInstance(it)
            }
        )
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentViewPagerScreenSecondBinding.bind(view)
        binding.lifecycleOwner=this

        setUpViewPager()

    }

    private fun setUpViewPager() {
        binding.viewPagerContainer.adapter=viewPagerAdapter
    }

}