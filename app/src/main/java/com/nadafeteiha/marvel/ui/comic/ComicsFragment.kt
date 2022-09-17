package com.nadafeteiha.marvel.ui.comic

import android.os.Bundle
import android.view.View
import com.nadafeteiha.marvel.R
import com.nadafeteiha.marvel.databinding.FragmentComicsBinding
import com.nadafeteiha.marvel.ui.base.BaseFragment

class ComicsFragment : BaseFragment<FragmentComicsBinding, ComicsViewModel>() {

    override val layoutIdFragment = R.layout.fragment_comics
    override val viewModelClass = ComicsViewModel::class.java
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        viewModel.comics.observe(viewLifecycleOwner) {
            it?.let {
                binding.comicsRecycler.adapter = ComicsAdapter(it)
            }
        }
    }

}