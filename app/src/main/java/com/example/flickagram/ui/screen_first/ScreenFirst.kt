package com.example.flickagram.ui.screen1

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flickagram.R
import com.example.flickagram.databinding.FragmentScreen1Binding


class Screen1 : Fragment(R.layout.fragment_screen1) {
    private lateinit var binding: FragmentScreen1Binding

    private val screenAdapter by lazy { Screen1Adapter(inflater = layoutInflater) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentScreen1Binding.bind(view)
        binding.lifecycleOwner=this

        binding.listItemContainer.apply {
            adapter=screenAdapter
            layoutManager=LinearLayoutManager(requireContext())
        }


    }


}