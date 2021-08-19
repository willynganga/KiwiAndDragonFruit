package ke.co.willynganga.modernhorticulture.ui.fragments.farmer

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ke.co.willynganga.modernhorticulture.R
import ke.co.willynganga.modernhorticulture.databinding.FragmentSignInBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val binding: FragmentSignInBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        binding.goToSignUp.setOnClickListener {
            findNavController().navigate(
                R.id.action_signInFragment_to_signUpFragment
            )
        }

        binding.signUpBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_signInFragment_to_homeFragment
            )
        }
    }

}