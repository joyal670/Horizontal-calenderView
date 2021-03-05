package com.calender.horizontalcalenderview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.calender.horizontalcalenderview.R
import com.calender.horizontalcalenderview.model.DateModal
import com.calender.horizontalcalenderview.utils.DateUtils
import kotlinx.android.synthetic.main.calendar_item.view.*
import java.util.*



class DaysOfMonthAdapter(
    private var context: Context,
    private val newDateList: List<DateModal>,
    private val dateList: MutableList<Date>
) : RecyclerView.Adapter<DaysOfMonthAdapter.ViewHold>() {
    inner class ViewHold(itemView: View, private var context: Context) :
        RecyclerView.ViewHolder(itemView) {


        @RequiresApi(Build.VERSION_CODES.M)
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bindItems(
            data: DateModal,
            date: Date,
            context: Context
        ) {
            itemView.tv_date_calendar_item.text = DateUtils.getDayNumber(date)
            itemView.tv_day_calendar_item.text = DateUtils.getDay3LettersName(date)
            if (data.isActive) {
                itemView.cl_calendar_item.background =
                    context.getDrawable(R.drawable.selected_calendar_item_background)
                itemView.tv_date_calendar_item.setTextColor(context.getColor(R.color.white))
                itemView.tv_date_calendar_item_dash.setTextColor(context.getColor(R.color.white))
                itemView.tv_day_calendar_item.setTextColor(context.getColor(R.color.white))
            } else {
                itemView.cl_calendar_item.background =
                    this.context.getDrawable(R.drawable.calendar_item_background)
                itemView.tv_date_calendar_item.setTextColor(context.getColor(R.color.black))
                itemView.tv_date_calendar_item_dash.setTextColor(context.getColor(R.color.black))
                itemView.tv_day_calendar_item.setTextColor(context.getColor(R.color.black))
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHold {
        context = parent.context
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.calendar_item, parent, false)
        return ViewHold(
            view, context
        )
    }

    override fun getItemCount(): Int {
        return newDateList.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHold, position: Int) {
        try{
            holder.bindItems(newDateList[position], dateList[position],context)
            holder.itemView.cl_calendar_item.setOnClickListener {
                newDateList.forEach {
                    if (it.date == newDateList[position].date) {
                        it.isActive = !it.isActive

                    } else {
                        it.isActive = false

                    }
                    notifyDataSetChanged()
                }
            }
        }catch (ex:Exception){
            Log.e("exception",ex.toString())
        }



    }
}