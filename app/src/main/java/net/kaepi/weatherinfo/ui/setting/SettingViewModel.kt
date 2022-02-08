package net.kaepi.weatherinfo.ui.setting

import androidx.lifecycle.ViewModel
import net.kaepi.weatherinfo.service.WeatherInfoData
import net.kaepi.weatherinfo.values.AreaData

class SettingViewModel : ViewModel(), RecyclerAdapter.OnClickListener<AreaData> {
	override fun onClick(item: AreaData) {
		WeatherInfoData.areaData.postValue(item)
	}
}