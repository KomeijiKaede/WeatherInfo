package net.kaepi.weatherinfo.ui.forecast.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import net.kaepi.weatherinfo.databinding.FragmentTabitemBinding
import net.kaepi.weatherinfo.service.WeatherInfoData

class TabItemFragment : Fragment() {
	private val viewModel by lazy {
		ViewModelProvider(this)[TabItemViewModel::class.java].apply {
			setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 0)
		}
	}

	private lateinit var _binding: FragmentTabitemBinding
	private val binding get() = _binding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_binding = FragmentTabitemBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onStart() {
		super.onStart()
		val imageLoader = ImageLoader.Builder(requireContext()).componentRegistry {
			add(SvgDecoder(requireContext())) }
			.build()

		Coil.setImageLoader(imageLoader)

		WeatherInfoData.weatherInfo.observe(viewLifecycleOwner, Observer {
			viewModel.forecast.postValue(it.forecasts[viewModel.getIndex()])
		})

		viewModel.forecast.observe(viewLifecycleOwner, Observer {
			if (it != null) {
				binding.icon.load(it.image.url)

				binding.bodyText.text = WeatherInfoData.weatherInfo.value!!.description.bodyText

				binding.city.text = WeatherInfoData.areaData.value!!.name
				binding.telop.text = it.telop
				binding.date.text = it.date

				binding.t1.text = it.chanceOfRain.T00_06
				binding.t2.text = it.chanceOfRain.T06_12
				binding.t3.text = it.chanceOfRain.T12_18
				binding.t4.text = it.chanceOfRain.T18_24
			}
		})
	}

	companion object {
		private const val ARG_SECTION_NUMBER = "section_number"
		@JvmStatic
		fun newInstance(sectionNumber: Int): TabItemFragment {
			return TabItemFragment().apply {
				arguments = Bundle().apply {
					putInt(ARG_SECTION_NUMBER, sectionNumber)
				}
			}
		}
	}
}