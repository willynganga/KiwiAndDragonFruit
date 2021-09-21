package ke.co.willynganga.modernhorticulture.ui.fragments.buyer

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ke.co.willynganga.modernhorticulture.R
import ke.co.willynganga.modernhorticulture.adapter.BuyerGridViewAdapter
import ke.co.willynganga.modernhorticulture.databinding.FragmentBuyerHomeBinding
import ke.co.willynganga.modernhorticulture.viewmodel.FirestoreViewModel

@AndroidEntryPoint
class BuyerHomeFragment : Fragment(R.layout.fragment_buyer_home) {

    private val binding: FragmentBuyerHomeBinding by viewBinding()

    private val firestoreViewModel: FirestoreViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fetch all fruit advertisements
        firestoreViewModel.getFruitsAds()

        setupObserver()
    }

    private fun setupObserver() {
        firestoreViewModel.fruitAdsList.observe(viewLifecycleOwner) { gridItems ->
            val gridViewAdapter =
                BuyerGridViewAdapter(requireContext(), gridItems, firestoreViewModel)
            binding.gridView.apply {
                adapter = gridViewAdapter
            }
        }
    }
}