package ke.co.willynganga.modernhorticulture.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.viewpager2.widget.ViewPager2
import ke.co.willynganga.modernhorticulture.R
import ke.co.willynganga.modernhorticulture.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment(R.layout.fragment_second_screen) {

    private val binding: FragmentSecondScreenBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)

        binding.next.setOnClickListener {
            viewPager?.currentItem = 2
        }

        binding.skip.setOnClickListener {
            viewPager?.currentItem = 2
        }
    }

}