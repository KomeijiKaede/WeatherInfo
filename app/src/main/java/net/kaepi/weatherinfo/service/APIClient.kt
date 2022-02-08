package net.kaepi.weatherinfo.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class APIClient {
	private val moshi = Moshi.Builder()
		.add(KotlinJsonAdapterFactory())
		.build()

	private val retrofit = Retrofit.Builder()
		.baseUrl("https://weather.tsukumijima.net/")
		.addConverterFactory(MoshiConverterFactory.create(moshi))
		.build()

	private val weatherService = retrofit.create(WeatherService::class.java)

	suspend fun getWeatherInfo(cityId: String): Response<WeatherInfo> = weatherService.getWeather(cityId)

	companion object Factory {
		val instance: APIClient
			@Synchronized get() = APIClient()
	}
}