package com.example.bhuvanapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun clickDatePicker(view:View){
        val myCalendar=Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd =DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
                view,selectedYear,selectedMonth,selectedDayOfMonth->

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate.setText(selectedDate)
            val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val theDate=sdf.parse(selectedDate)

            val selectedDateInDays=theDate!!.time / (1000*60*60*24)

            val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateInDays= currentDate!!.time / (60000*60*24)

            val differenceInDays=currentDateInDays-selectedDateInDays

            tvSelectedDateInDays.text=differenceInDays.toString()


        }
            ,year
            ,month
            ,day)

        dpd.datePicker.setMaxDate(Date().time-86400000)
        dpd.show()
    }

}