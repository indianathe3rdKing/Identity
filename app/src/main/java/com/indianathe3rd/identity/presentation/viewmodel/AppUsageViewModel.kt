package com.indianathe3rd.identity.presentation.viewmodel

import android.app.Application
import android.app.usage.UsageStatsManager
import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indianathe3rd.identity.domain.model.AppUsage
import com.indianathe3rd.identity.domain.repository.ClassificationRepository
import com.indianathe3rd.identity.domain.usecase.usage.GetAppsUsageUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.http.ContentType
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AppUsageViewModel @Inject constructor(

    private val getAppUsageUsecase: GetAppsUsageUsecase,
    private val classificationRepo: ClassificationRepository
): ViewModel(){

    private val _appUsage = mutableStateOf<List<AppUsage>>(emptyList())
    val appUsage: State<List<AppUsage>> = _appUsage

    fun getAppUsageStats(){

        val intervalType = UsageStatsManager.INTERVAL_BEST
        //Define the time frame (e.g from 24 hours ago until now)
        val endtime = System.currentTimeMillis()
        val calender = Calendar.getInstance()
        calender.add(Calendar.DAY_OF_YEAR, -1)
        val startTime = calender.timeInMillis

        viewModelScope.launch {
            val appsUsage =
                getAppUsageUsecase(startTime,endtime,intervalType)

            _appUsage.value = appsUsage
            Log.d(TAG, " AppUsage: $appsUsage")
            appsUsage.map { app->
                testClassification(app.packageName)
            }

        }


    }

    fun testClassification(packageName: String){
        viewModelScope.launch {
            try {
                val result = classificationRepo.createClassification(packageName)
                Log.d(TAG,"Classification Result -> $result")
            }catch (e: Exception){
                Log.e(TAG,"Classification failed", e)
            }
        }
    }
    companion object{
        private val TAG = "AppUsageViewModel"
    }

}