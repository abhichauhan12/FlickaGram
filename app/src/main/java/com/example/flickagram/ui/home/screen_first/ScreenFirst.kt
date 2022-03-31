package com.example.flickagram.ui.home.screen_first

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flickagram.R
import com.example.flickagram.databinding.FragmentScreenFirstBinding
import com.example.flickagram.ui.home.viewmodel.FetchStatus
import com.example.flickagram.ui.home.viewmodel.HomeViewModel
import com.example.flickagram.utils.view.InfiniteScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScreenFirst : Fragment(R.layout.fragment_screen_first) {

    private lateinit var binding: FragmentScreenFirstBinding
    private val homeAdapter by lazy { ScreenFirstAdapter(
        onItemClick = {
            findNavController().navigate(R.id.action_screenFirst_to_viewPagerScreenSecond,Bundle().apply { putInt("position",it) })
        }
    ) }

    private val homeViewModel by activityViewModels<HomeViewModel>()

    private lateinit var linearLayoutManager : LinearLayoutManager

    private val infiniteScrollListener by lazy {
        InfiniteScrollListener(linearLayoutManager, work = {
            homeViewModel.getPhotos()
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentScreenFirstBinding.bind(view)
        binding.lifecycleOwner = this

        linearLayoutManager = LinearLayoutManager(requireContext())

        binding.listItemContainer.apply {
            adapter = homeAdapter
            layoutManager = linearLayoutManager
            addOnScrollListener(infiniteScrollListener)
        }

        observe()
        homeViewModel.getPhotos()

    }

    private fun observe() {
        lifecycleScope.launchWhenStarted {
            launch {
                homeViewModel.fetchStatus.collect {
                    when (it) {
                        FetchStatus.LOADING -> {
                            if (homeViewModel.photoList.value.isNullOrEmpty())
                                binding.emptyListText.visibility = View.VISIBLE
                        }

                        FetchStatus.FAILURE -> {
                            if (homeViewModel.photoList.value.isNullOrEmpty())
                                binding.emptyListText.visibility = View.VISIBLE
                        }

                        FetchStatus.SUCCESS -> Unit
                        null -> Unit
                    }
                }
            }

            launch {
                homeViewModel.photoList.collect {
                    homeAdapter.submitList(it)
                    binding.emptyListText.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                }
            }
        }
    }


}