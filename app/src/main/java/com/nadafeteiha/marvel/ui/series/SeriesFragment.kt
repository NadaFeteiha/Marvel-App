package com.nadafeteiha.marvel.ui.series

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.nadafeteiha.marvel.R
import com.nadafeteiha.marvel.databinding.FragmentSeriesBinding
import com.nadafeteiha.marvel.ui.base.BaseFragment
import com.nadafeteiha.marvel.util.EventObserve

class SeriesFragment : BaseFragment<FragmentSeriesBinding, SeriesViewModel>() {

    override val layoutIdFragment = R.layout.fragment_series
    override val viewModelClass = SeriesViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        navigateToComics()
        navigateToSeriesDetails()
    }

    private fun initData() {
        viewModel.series.observe(viewLifecycleOwner) {
            it?.let {
                binding.recyclerSeries.adapter = SeriesAdapter(it, viewModel)
            }
        }
    }

    private fun navigateToSeriesDetails() {
        viewModel.navigateToSeriesDetails.observe(viewLifecycleOwner, EventObserve {
            if (it != -1) {
                findNavController().navigate(
                    SeriesFragmentDirections.actionSeriesFragmentToSeriesDetailsFragment(
                        it
                    )
                )
            }
        })
    }

    private fun navigateToComics() {
        viewModel.navigateToComics.observe(viewLifecycleOwner, EventObserve {
            if (it) {
                findNavController().navigate(SeriesFragmentDirections.actionSeriesFragmentToComicsFragment())
            }
        })
    }

}