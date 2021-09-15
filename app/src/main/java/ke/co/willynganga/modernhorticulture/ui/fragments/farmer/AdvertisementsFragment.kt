package ke.co.willynganga.modernhorticulture.ui.fragments.farmer

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ke.co.willynganga.modernhorticulture.R
import ke.co.willynganga.modernhorticulture.adapter.GridViewAdapter
import ke.co.willynganga.modernhorticulture.databinding.FragmentAdvertisementsBinding
import ke.co.willynganga.modernhorticulture.viewmodel.FirestoreViewModel

@AndroidEntryPoint
class AdvertisementsFragment : Fragment(R.layout.fragment_advertisements) {

    private val binding: FragmentAdvertisementsBinding by viewBinding()

    private val fireStoreViewModel: FirestoreViewModel by viewModels()

    private val args: AdvertisementsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fireStoreViewModel.getSellingFruitDetails(args.username)

        setupObservers()
    }

    private fun setupObservers() {
        fireStoreViewModel.fruitDescriptionList.observe(viewLifecycleOwner) {
            val gridViewAdapter = GridViewAdapter(requireContext(), it)
            binding.gridView.apply {
                adapter = gridViewAdapter
            }
        }
    }
}