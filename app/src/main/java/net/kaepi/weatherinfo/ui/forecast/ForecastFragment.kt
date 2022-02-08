package net.kaepi.weatherinfo.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import net.kaepi.weatherinfo.databinding.FragmentForecastBinding
import net.kaepi.weatherinfo.ui.forecast.tablayout.PagerAdapter
import net.kaepi.weatherinfo.values.TAB_TITLES

class ForecastFragment : Fragment() {
	private var _binding: FragmentForecastBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentForecastBinding.inflate(inflater, container, false)

		val pagerAdapter = PagerAdapter(this)
		binding.viewPager.adapter = pagerAdapter
		TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
			tab.text = getString(TAB_TITLES[position])
		}.attach()

		return binding.root
	}
}