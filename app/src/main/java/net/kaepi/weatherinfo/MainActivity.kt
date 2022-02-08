package net.kaepi.weatherinfo

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.kaepi.weatherinfo.databinding.ActivityMainBinding
import net.kaepi.weatherinfo.service.APIClient
import net.kaepi.weatherinfo.service.WeatherInfoData

class MainActivity : AppCompatActivity() {
	private lateinit var viewModel: MainViewModel
	private lateinit var appBarConfiguration: AppBarConfiguration
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewModel = ViewModelProvider(this)[MainViewModel::class.java]
		binding = ActivityMainBinding.inflate(layoutInflater)

		setContentView(binding.root)
		setSupportActionBar(binding.appBarMain.toolbar)

		val drawerLayout: DrawerLayout = binding.drawerLayout
		val navView: NavigationView = binding.navView
		val navController = findNavController(R.id.nav_host_fragment_content_main)

		appBarConfiguration = AppBarConfiguration(
			setOf(R.id.nav_home, R.id.nav_forecast, R.id.nav_setting),
			drawerLayout
		)
		setupActionBarWithNavController(navController, appBarConfiguration)
		navView.setupWithNavController(navController)

		WeatherInfoData.areaData.observe(this, Observer {
			viewModel.load(it.id)
		})
	}

	override fun onSupportNavigateUp(): Boolean {
		val navController = findNavController(R.id.nav_host_fragment_content_main)
		return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
	}
}

class MainViewModel : ViewModel() {
	fun load(id: String) {
		viewModelScope.launch {
			try {
				val request = APIClient.instance.getWeatherInfo(id)
				if (request.isSuccessful) WeatherInfoData.weatherInfo.postValue(request.body())
			} catch (e: Exception) {
				e.stackTrace
			}
		}
	}
}