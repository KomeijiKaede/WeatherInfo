package net.kaepi.weatherinfo.ui.home.tablayout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.kaepi.weatherinfo.service.Forecast

class TabItemViewModel : ViewModel() {
	private val _index = MutableLiveData<Int>()
	val forecast: MutableLiveData<Forecast> = MutableLiveData()

	fun setIndex(index: Int) {
		_index.value = index
	}

	fun getIndex() = _index.value!!
}