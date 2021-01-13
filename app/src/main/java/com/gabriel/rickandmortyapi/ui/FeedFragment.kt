package com.gabriel.rickandmortyapi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gabriel.rickandmortyapi.R
import com.gabriel.rickandmortyapi.data.model.Character
import com.gabriel.rickandmortyapi.databinding.FeedFragmentBinding
import com.gabriel.rickandmortyapi.repository.FeedRepository
import com.gabriel.rickandmortyapi.ui.adapter.FeedAdapter
import com.gabriel.rickandmortyapi.ui.viewmodel.FeedViewModel
import com.gabriel.rickandmortyapi.utils.Constants.Companion.CHARACTER_KEY
import com.gabriel.rickandmortyapi.utils.Constants.Companion.TAG

class FeedFragment : Fragment(R.layout.feed_fragment), FeedAdapter.OnCharacterClickListener {
    private var _binding :  FeedFragmentBinding? = null
    private val binding : FeedFragmentBinding get() = _binding!!

    private lateinit var feedViewModel: FeedViewModel
    private lateinit var feedAdapter : FeedAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FeedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        feedAdapter = FeedAdapter(requireContext(),this)
        feedViewModel = ViewModelProvider(this,FeedViewModel.FeedViewModelFactory(FeedRepository())).get(FeedViewModel::class.java)
        feedViewModel.getAllCharacters()
        feedViewModel.characterList.observe(viewLifecycleOwner, Observer {characters ->
            Log.i(TAG,"List no fragment: $characters")
            feedAdapter.setCharacters(characters)
            binding.recyclerViewFeed.adapter = feedAdapter

        })
        binding.searchViewFeed.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {text ->
                    feedViewModel.getCharacterByName(text)
                }
                return false

            }

            override fun onQueryTextChange(newText: String?) =  false



        })


        /* searchViewMain.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mainViewModel.setDrink(it)

                }
                return false

            }

            override fun onQueryTextChange(newText: String?) = false
*/


    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onCharacterClick(character: Character) {
        val bundle = Bundle()
        bundle.putParcelable(CHARACTER_KEY, character)
        findNavController().navigate(R.id.action_feedFragment_to_detailsFragment,bundle)

    }
}

