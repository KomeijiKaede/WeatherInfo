package net.kaepi.weatherinfo.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.kaepi.weatherinfo.service.APIClient
import net.kaepi.weatherinfo.service.WeatherInfoData
import net.kaepi.weatherinfo.values.AreaData

class SettingViewModel : ViewModel(), RecyclerAdapter.OnClickListener<AreaData> {
	private val apiClient = APIClient.instance

	override fun onClick(item: AreaData) {
		WeatherInfoData.areaData.postValue(item)
	}

	private fun loadApi() {
		viewModelScope.launch {
			try {
				val request = apiClient.getWeatherInfo(WeatherInfoData.areaData.value!!.id)
				if (request.isSuccessful) WeatherInfoData.weatherInfo.postValue(request.body())
			} catch (e: Exception) {
				e.stackTrace
			}
		}
	}
}