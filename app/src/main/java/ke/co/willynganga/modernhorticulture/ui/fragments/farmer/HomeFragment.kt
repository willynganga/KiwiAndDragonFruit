package ke.co.willynganga.modernhorticulture.ui.fragments.farmer

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ke.co.willynganga.modernhorticulture.R
import ke.co.willynganga.modernhorticulture.databinding.FragmentHomeBinding
import ke.co.willynganga.modernhorticulture.util.Constants
import ke.co.willynganga.modernhorticulture.util.Constants.Companion.KIWI_FRUIT_NAME
import ke.co.willynganga.modernhorticulture.viewmodel.FirestoreViewModel

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()

    private val fireStoreViewModel: FirestoreViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        // fetch user's name
        fireStoreViewModel.getUserName(fireStoreViewModel.currentUser.uid)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupOnClickListeners()

    }

    private fun setupOnClickListeners() {
        binding.kiwiFruit.learnMore.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToFruitDescriptionFragment(KIWI_FRUIT_NAME)
            )
        }

        binding.dragonFruit.learnMore.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToFruitDescriptionFragment(Constants.DRAGON_FRUIT_NAME)
            )
        }

        binding.sellFruitsCard.sellFruits.setOnClickListener {
            val username =
                binding.greetUser.text.toString()
                    .substring(6)
                    .replace("!", "")
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToSellFruitFragment(username)
            )
        }
    }

    private fun setupObservers() {
        fireStoreViewModel.username.observe(viewLifecycleOwner, { name ->
            binding.greetUser.text = "Hello $name!"
        })
    }
}