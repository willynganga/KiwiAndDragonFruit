package ke.co.willynganga.modernhorticulture.ui.fragments.onboarding

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import ke.co.willynganga.modernhorticulture.R
import ke.co.willynganga.modernhorticulture.adapter.ViewPagerAdapter
import ke.co.willynganga.modernhorticulture.databinding.FragmentViewPagerBinding
import ke.co.willynganga.modernhorticulture.ui.fragments.onboarding.screens.FirstScreen
import ke.co.willynganga.modernhorticulture.ui.fragments.onboarding.screens.SecondScreen
import ke.co.willynganga.modernhorticulture.ui.fragments.onboarding.screens.ThirdScreen

class ViewPagerFragment : Fragment(R.layout.fragment_view_pager) {

    private val binding: FragmentViewPagerBinding by viewBinding()

    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null) {
            findNavController().navigate(
                ViewPagerFragmentDirections.actionViewPagerFragmentToFarmerLoginFlow()
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter
    }
}