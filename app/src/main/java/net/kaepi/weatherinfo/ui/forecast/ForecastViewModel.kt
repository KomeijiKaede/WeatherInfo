package net.kaepi.weatherinfo.ui.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.kaepi.weatherinfo.service.APIClient
import net.kaepi.weatherinfo.service.WeatherInfoData

class ForecastViewModel : ViewModel() {
	private val apiClient = APIClient.instance

	init {
		loadApi()
	}

	private fun loadApi() {
		viewModelScope.launch {
			try {
				val request = apiClient.getWeatherInfo(WeatherInfoData.areaData.value!!.id)
				if (request.isSuccessful) {
					WeatherInfoData.weatherInfo.postValue(request.body())
				}
			} catch (e: Exception) {
				e.stackTrace
			}
		}
	}
}