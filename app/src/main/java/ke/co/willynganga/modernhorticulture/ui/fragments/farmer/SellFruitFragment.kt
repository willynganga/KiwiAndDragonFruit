package ke.co.willynganga.modernhorticulture.ui.fragments.farmer

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ke.co.willynganga.modernhorticulture.R
import ke.co.willynganga.modernhorticulture.databinding.FragmentSellFruitBinding
import ke.co.willynganga.modernhorticulture.viewmodel.FirestoreViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SellFruitFragment : Fragment(R.layout.fragment_sell_fruit) {

    private val binding: FragmentSellFruitBinding by viewBinding()

    private val imagesList: MutableList<Uri> = mutableListOf()

    private val firestoreViewModel: FirestoreViewModel by viewModels()

    private val args: SellFruitFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOnCLickListeners()
        setupDropDownMenu()
    }

    private fun setupOnCLickListeners() {
        binding.imageSelector.setOnClickListener {
            chooseImages()
        }

        binding.uploadFruit.setOnClickListener {
            uploadFruit()
        }

        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun chooseImages() {
        val intent = Intent()

        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        resultLauncher.launch(intent)
    }

    private fun uploadFruit() {
        val phoneNumber = binding.phoneNumber.text.toString().trim()
        val typeOfFruit = binding.autoComplete.text.toString().trim()
        val quantity = binding.quantity.text.toString().trim()
        val sellingPrice = binding.pricePerKilo.text.toString().trim()
        val location = binding.location.text.toString().trim()

        if (phoneNumber.isEmpty() || typeOfFruit.isEmpty() || quantity.isEmpty()
            || sellingPrice.isEmpty() || location.isEmpty()
        ) {
            Toast.makeText(requireContext(), "All fields are required!", Toast.LENGTH_SHORT).show()
            return
        }

        if (imagesList.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Please choose at lease one image!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        binding.progressCircular.visibility = View.VISIBLE

        uploadImageToFirestore()
        lifecycleScope.launch {
            delay(3000L)
            uploadSellingFruitDetails(phoneNumber, typeOfFruit, quantity, sellingPrice, location)
        }
    }

    private fun uploadImageToFirestore() {
        firestoreViewModel.uploadImage(imagesList[0])
    }

    private fun uploadSellingFruitDetails(
        phoneNumber: String,
        typeOfFruit: String,
        quantity: String,
        sellingPrice: String,
        location: String
    ) {
        firestoreViewModel.imageDownloadUrl.observe(viewLifecycleOwner) {
            firestoreViewModel.uploadSellingFruitDetails(
                args.username, phoneNumber, typeOfFruit, quantity, sellingPrice, location, it)
        }

        observeUploadTask()
    }

    private fun observeUploadTask() {
        firestoreViewModel.sellingDetailsUpload.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            binding.progressCircular.visibility = View.INVISIBLE
            findNavController().navigate(
                SellFruitFragmentDirections.actionSellFruitFragmentToHomeFragment()
            )
        }
    }

    private fun setupDropDownMenu() {
        val items = listOf("Dragon Fruit", "Kiwi Fruit")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        binding.autoComplete.setAdapter(adapter)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result?.data?.let { intent ->
                    imagesList.clear()
                    imagesList.add(0, intent.data!!)
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please select an image!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
}