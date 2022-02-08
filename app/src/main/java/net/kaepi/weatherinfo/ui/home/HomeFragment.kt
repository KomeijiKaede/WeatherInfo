package net.kaepi.weatherinfo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import net.kaepi.weatherinfo.databinding.FragmentHomeBinding
import net.kaepi.weatherinfo.ui.home.tablayout.PagerAdapter
import net.kaepi.weatherinfo.values.TAB_TITLES

class HomeFragment : Fragment() {
	private lateinit var viewModel: HomeViewModel

	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		val root: View = binding.root

		val pagerAdapter = PagerAdapter(this)
		binding.viewPager.adapter = pagerAdapter
		TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
			tab.text = getString(TAB_TITLES[position])
		}.attach()

		return root
	}
}