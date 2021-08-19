package ke.co.willynganga.modernhorticulture.ui.fragments.farmer

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ke.co.willynganga.modernhorticulture.R
import ke.co.willynganga.modernhorticulture.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding: FragmentSignUpBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOnClickListeners()
    }

    private fun setupOnClickListeners() {
        binding.goToSignIn.setOnClickListener {
            findNavController().navigate(
                R.id.action_signUpFragment_to_signInFragment
            )
        }

        binding.signUpBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_signUpFragment_to_homeFragment
            )
        }
    }

}