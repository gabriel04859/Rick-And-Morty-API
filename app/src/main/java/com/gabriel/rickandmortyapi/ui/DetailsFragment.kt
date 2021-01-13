package com.gabriel.rickandmortyapi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.gabriel.rickandmortyapi.R
import com.gabriel.rickandmortyapi.data.model.Character
import com.gabriel.rickandmortyapi.database.CharacterDatabase
import com.gabriel.rickandmortyapi.databinding.DetailsFragmentBinding
import com.gabriel.rickandmortyapi.repository.DetailsRepository
import com.gabriel.rickandmortyapi.ui.viewmodel.DetailsViewModel
import com.gabriel.rickandmortyapi.utils.Constants.Companion.CHARACTER_KEY
import com.gabriel.rickandmortyapi.utils.Constants.Companion.TAG
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment(R.layout.details_fragment) {
    private var _binding :  DetailsFragmentBinding? = null
    private val binding : DetailsFragmentBinding get() = _binding!!
    private lateinit var characterBundle : Character

    private lateinit var detailsViewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterDAO = CharacterDatabase.getInstance(requireContext()).characterDAO
        detailsViewModel = ViewModelProvider(this,
                DetailsViewModel.DetailsViewModelFactory(DetailsRepository(characterDAO)))
                .get(DetailsViewModel::class.java)
        requireArguments().let {
           characterBundle = it.getParcelable(CHARACTER_KEY)!!
            Log.i(TAG,"Personagem: $characterBundle")
            characterBundle.apply {
                Picasso.with(requireContext()).load(image).into(binding.imageViewDetails)
                binding.textViewNameDetails.text = name
                binding.textViewGenderDetails.text = gender
                binding.textViewSpeciesDetails.text = species
                binding.textViewStatusDetails.text = status
            }

        }

        binding.buttonSaveInFavorites.setOnClickListener {
            detailsViewModel.saveCharacter(characterBundle)
        }


    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}