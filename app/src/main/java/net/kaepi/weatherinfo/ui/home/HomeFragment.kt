package net.kaepi.weatherinfo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import coil.Coil
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import net.kaepi.weatherinfo.databinding.FragmentHomeBinding
import net.kaepi.weatherinfo.service.WeatherInfoData

class HomeFragment : Fragment() {
	private lateinit var _binding: FragmentHomeBinding
	private val binding get() = _binding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_binding = FragmentHomeBinding.inflate(inflater, container, false)
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
				val forecast = it.forecasts[0]

				binding.title.text = it.title
				binding.icon.load(forecast.image.url)
				binding.telop.text = forecast.telop

				val chanceOfRain = forecast.chanceOfRain

				binding.t1.text = chanceOfRain.T00_06
				binding.t2.text = chanceOfRain.T06_12
				binding.t3.text = chanceOfRain.T12_18
				binding.t4.text = chanceOfRain.T18_24

				binding.homeBodyText.text = it.description.bodyText
			}
		})
	}
}