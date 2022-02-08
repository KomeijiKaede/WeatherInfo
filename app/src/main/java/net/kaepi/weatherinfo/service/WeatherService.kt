package net.kaepi.weatherinfo.service

import androidx.lifecycle.MutableLiveData
import net.kaepi.weatherinfo.values.AreaData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

class WeatherInfoData {
	companion object {
		val weatherInfo: MutableLiveData<WeatherInfo> = MutableLiveData()
		val areaData: MutableLiveData<AreaData> = MutableLiveData(AreaData("東京都", "130010"))
	}
}

interface WeatherService {
	@GET("api/forecast/city/{id}")
	suspend fun getWeather(@Path("id") id: String): Response<WeatherInfo>
}

data class WeatherInfo(
	val title: String,
	val description: Description,
	val forecasts: List<Forecast>
)

data class Description(
	val bodyText: String
)

data class Forecast(
	val date: String,
	val dateLabel: String,
	val telop: String,
	val detail: Detail,
	val chanceOfRain: ChanceOfRain,
	val image: Image
)

data class Detail(
	val weather: String,
	val wind: String,
	val wave: String
)

data class ChanceOfRain(
	val T00_06: String,
	val T06_12: String,
	val T12_18: String,
	val T18_24: String
)

data class Image(
	val url: String
)