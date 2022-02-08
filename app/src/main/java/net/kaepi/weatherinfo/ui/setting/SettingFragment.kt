package net.kaepi.weatherinfo.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import net.kaepi.weatherinfo.databinding.FragmentSettingBinding
import net.kaepi.weatherinfo.values.AREA_LIST

class SettingFragment : Fragment() {

	private val viewModel by lazy {
		ViewModelProvider(this)[SettingViewModel::class.java]
	}

	private lateinit var _binding: FragmentSettingBinding

	private val binding get() = _binding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

		_binding = FragmentSettingBinding.inflate(inflater, container, false)

		binding.areaList.layoutManager = LinearLayoutManager(this.context)
		binding.areaList.adapter = RecyclerAdapter.RecyclerAdapter(AREA_LIST, viewModel)

		return binding.root
	}
}