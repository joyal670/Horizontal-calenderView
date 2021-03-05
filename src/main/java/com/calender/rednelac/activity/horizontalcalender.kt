package com.calender.horizontalcalenderview.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.calender.horizontalcalenderview.R
import com.calender.horizontalcalenderview.adapter.DaysOfMonthAdapter
import com.calender.horizontalcalenderview.model.DateModal
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.log

class horizontalcalender(context: Context) : AppCompatActivity()
{
    private val calendar = Calendar.getInstance()
    private var currentMonth = 0
    private val dateList: MutableList<Date> = mutableListOf()
    private var scrollPosition = 0
    private val dateModifiedList:ArrayList<DateModal> =ArrayList()
    val listMonths = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November","December")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setting current month
        calendar.time = Date()
        currentMonth = calendar[Calendar.MONTH]
        tvCurrentMonth.text=listMonths[currentMonth]
        setDates(getFutureDatesOfCurrentMonth())
        //Getting position of current month


        //Previous month
        ivLeft.setOnClickListener {
            setDates(getDatesOfPreviousMonth())
        }

        //Next month
        ivRight.setOnClickListener {
            setDates(getDatesOfNextMonth())
        }

    }

    /*private fun getDatesInMonth(year: Int, month: Int)
    {
        val fmt = SimpleDateFormat("d - c", Locale.getDefault())
        val cal: Calendar = Calendar.getInstance()
        cal.clear()
        dateList.clear()
        cal.set(year, month - 1, 1)
        val daysInMonth: Int = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in 0 until daysInMonth)
        {
            dateList.add(fmt.format(cal.getTime()))
            cal.add(Calendar.DAY_OF_MONTH, 1)
        }

        //Setting the recyclerview
        rvHorizontal.adapter = HorizontalViewAdapter(dateList)
    }*/

    private fun setDates(newDateList: List<Date>) {
        dateList.clear()
        dateModifiedList.clear()
        dateList.addAll(newDateList)
        dateList.forEach {
            dateModifiedList.add(
                DateModal(
                    it.date,
                    false
                )
            )
        }
        rvHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rvHorizontal.adapter= DaysOfMonthAdapter(this,dateModifiedList,dateList)

        if (scrollPosition > dateList.size - 1)
            scrollPosition = dateList.size - 1
        rvHorizontal.scrollToPosition(scrollPosition)
    }

    @SuppressLint("LongLogTag")
    private fun getFutureDatesOfCurrentMonth(): List<Date> {
        currentMonth = calendar[Calendar.MONTH]
        return getDates(mutableListOf())
    }

    private fun getDates(list: MutableList<Date>): List<Date> {
        calendar.set(Calendar.MONTH, currentMonth)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        list.add(calendar.time)
        while (currentMonth == calendar[Calendar.MONTH]) {
            calendar.add(Calendar.DATE, +1)
            if (calendar[Calendar.MONTH] == currentMonth)
                list.add(calendar.time)
        }
        calendar.add(Calendar.DATE, -1)
        return list
    }

    private fun getDatesOfNextMonth(): List<Date> {
        currentMonth++ // + because we want next month
        if (currentMonth == 12) {
            // we will switch to january of next year, when we reach last month of year
            calendar.set(Calendar.YEAR, calendar[Calendar.YEAR] + 1)
            currentMonth = 0 // 0 == january
        }
        tvCurrentMonth.text=listMonths[currentMonth]
        return getDates(mutableListOf())
    }

    private fun getDatesOfPreviousMonth(): List<Date> {
        currentMonth-- // - because we want previous month
        if (currentMonth == -1) {
            // we will switch to december of previous year, when we reach first month of year
            calendar.set(Calendar.YEAR, calendar[Calendar.YEAR] - 1)
            currentMonth = 11 // 11 == december
        }
        tvCurrentMonth.text=listMonths[currentMonth]
        return getDates(mutableListOf())
    }

}