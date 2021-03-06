package ke.co.willynganga.modernhorticulture.ui.fragments.onboarding.screens

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ke.co.willynganga.modernhorticulture.R
import ke.co.willynganga.modernhorticulture.databinding.FragmentThirdScreenBinding

class ThirdScreen : Fragment(R.layout.fragment_third_screen) {

    private val binding: FragmentThirdScreenBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginFarmer.setOnClickListener {
            findNavController().navigate(
                R.id.action_viewPagerFragment_to_farmer_login_flow
            )
        }

        binding.loginBuyer.setOnClickListener {
            findNavController().navigate(
                R.id.action_viewPagerFragment_to_buyer_login_flow
            )
        }

    }

}