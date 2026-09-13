package com.indianathe3rd.identity.presentation.viewmodel

import android.content.Context
import java.util.Calendar

class AppUsageViewModel {

    fun getAppUsageStats(context: Context){
        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE)

        //Define the time frame (e.g from 24 hours ago until now)
        val endtime = System.currentTimeMillis()
        val calender = Calendar.getInstance()
        calender.add(Calendar.DAY_OF_YEAR, -1)
        val startTime = calender.timeInMillis

    }

}