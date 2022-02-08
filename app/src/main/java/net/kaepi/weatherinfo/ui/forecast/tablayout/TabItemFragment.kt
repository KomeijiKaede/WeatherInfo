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
			if (it != null) {
				val forecast = it.forecasts[viewModel.getIndex()]

				binding.title.text = it.title
				binding.icon.load(forecast.image.url)
				binding.telop.text = forecast.telop

				val chanceOfRain = forecast.chanceOfRain

				binding.t1.text = chanceOfRain.T00_06
				binding.t2.text = chanceOfRain.T06_12
				binding.t3.text = chanceOfRain.T12_18
				binding.t4.text = chanceOfRain.T18_24

				binding.weather.text = "詳細: ${forecast.detail.weather}"
				binding.wind.text = "風向き: ${forecast.detail.wind}"
				binding.wave.text = "波: ${forecast.detail.wave}"
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