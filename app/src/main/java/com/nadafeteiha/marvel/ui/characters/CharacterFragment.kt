package com.nadafeteiha.marvel.ui.characters

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.nadafeteiha.marvel.R
import com.nadafeteiha.marvel.databinding.FragmentCharacterBinding
import com.nadafeteiha.marvel.ui.base.BaseFragment
import com.nadafeteiha.marvel.ui.series.seriesDetails.SeriesDetailsFragmentArgs


class CharacterFragment : BaseFragment<FragmentCharacterBinding, CharacterViewModel>() {

    override val layoutIdFragment = R.layout.fragment_character
    override val viewModelClass = CharacterViewModel::class.java

    private val arguments: CharacterFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initCharacter(arguments.characterID)
    }

}