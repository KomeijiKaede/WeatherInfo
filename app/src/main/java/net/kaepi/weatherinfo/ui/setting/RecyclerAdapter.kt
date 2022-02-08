package net.kaepi.weatherinfo.ui.setting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.kaepi.weatherinfo.R
import net.kaepi.weatherinfo.values.AreaData

object RecyclerAdapter {
	interface OnClickListener<in T> { fun onClick(item: T) }

	private fun inflate(parent: ViewGroup) = LayoutInflater.from(parent.context).inflate(R.layout.card_setting, parent, false)

	private fun <T> bind(holder: ViewHolder<T>, name: String, id: String, item: T) {
		holder.areaName.text = name
		holder.bind(item)
	}

	class ViewHolder<T>(itemView: View, private val listener: OnClickListener<T>) : RecyclerView.ViewHolder(itemView) {
		val areaName = itemView.findViewById<TextView>(R.id.area_name)
		fun bind(item: T) {
			itemView.setOnClickListener {
				listener.onClick(item)
				return@setOnClickListener
			}
		}
	}

	class RecyclerAdapter(private val areaList: Array<AreaData>, private val listener: OnClickListener<AreaData>): RecyclerView.Adapter<ViewHolder<AreaData>>() {
		override fun getItemCount() = areaList.size
		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(inflate(parent), listener)
		override fun onBindViewHolder(holder: ViewHolder<AreaData>, position: Int) {
			val currentItem = areaList[position]
			bind(holder, currentItem.name, currentItem.id, currentItem)
		}
	}


}