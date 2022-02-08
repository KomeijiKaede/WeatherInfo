package net.kaepi.weatherinfo.ui.forecast.tablayout

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TabItemViewModel : ViewModel() {
	private val _index = MutableLiveData<Int>()

	fun setIndex(index: Int) {
		_index.value = index
	}

	fun getIndex() = _index.value!!
}