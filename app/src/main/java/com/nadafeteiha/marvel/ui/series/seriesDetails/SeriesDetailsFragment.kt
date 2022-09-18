package com.nadafeteiha.marvel.ui.series.seriesDetails

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nadafeteiha.marvel.R
import com.nadafeteiha.marvel.databinding.FragmentSeriesDetailsBinding
import com.nadafeteiha.marvel.ui.base.BaseFragment
import com.nadafeteiha.marvel.ui.series.SeriesFragmentDirections
import com.nadafeteiha.marvel.util.EventObserve

class SeriesDetailsFragment : BaseFragment<FragmentSeriesDetailsBinding, SeriesDetailsViewModel>() {

    override val layoutIdFragment = R.layout.fragment_series_details
    override val viewModelClass = SeriesDetailsViewModel::class.java
    private val arguments: SeriesDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setSeriesID(arguments.seriesID)
        initData()
        navigateToCharacterDetails()
    }

    private fun initData() {
        viewModel.details.observe(viewLifecycleOwner) {
            it?.let {
                binding.charactersRecycler.adapter =
                    SeriesCharacterAdapter(it.characterResponse, viewModel)
            }
        }
    }

    private fun navigateToCharacterDetails() {
        viewModel.navigateToCharactersDetails.observe(viewLifecycleOwner, EventObserve {
            if (it != -1) {
                findNavController().navigate(
                    SeriesDetailsFragmentDirections
                        .actionSeriesDetailsFragmentToCharacterFragment(it)
                )
            }
        })
    }

}