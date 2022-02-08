package net.kaepi.weatherinfo.ui.forecast.tablayout

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
	override fun getItemCount(): Int = 3
	override fun createFragment(position: Int): Fragment = TabItemFragment.newInstance(position)
}