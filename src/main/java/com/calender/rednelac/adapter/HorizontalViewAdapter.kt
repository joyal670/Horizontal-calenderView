package com.calender.horizontalcalenderview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.calender.horizontalcalenderview.R
import kotlinx.android.synthetic.main.recycerview_layout.view.*


class HorizontalViewAdapter(private val dateList: ArrayList<String>) : RecyclerView.Adapter<HorizontalViewAdapter.ViewHold>()
{
    private var context: Context? = null

    class ViewHold(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold
    {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycerview_layout, parent, false)
        return ViewHold(
            view
        )
    }

    override fun getItemCount(): Int
    {
        return dateList.size
    }

    override fun onBindViewHolder(holder: ViewHold, position: Int)
    {
        holder.itemView.tvDates.text = dateList[position]
    }
}