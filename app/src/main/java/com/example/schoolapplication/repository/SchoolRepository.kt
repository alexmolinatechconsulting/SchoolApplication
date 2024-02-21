package com.example.schoolapplication.repository

import com.example.schoolapplication.api.SchoolApi
import com.example.schoolapplication.repository.data.SchoolWrapper
import androidx.compose.runtime.mutableStateListOf
import com.example.schoolapplication.data.School
import com.example.schoolapplication.data.SchoolSatScores
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolRepository(private val apiImpl : SchoolApi) {

    private var _schools = mutableStateListOf<SchoolWrapper>()
    val schools : List<SchoolWrapper> = _schools

    fun loadSchools() {
        // get schools and then zip
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }

        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch(Dispatchers.IO) {
            val schoolList = apiImpl.getSchools()
            val schoolSatScoresList = apiImpl.getSatScores()

            // just in case the lists from the api arent one to one
            var zip = mutableMapOf<String, SchoolWrapper>()
            for(school in schoolList){
                if(!zip.containsKey(school.name?.lowercase())){
                    zip[school.name!!.lowercase()] =
                        SchoolWrapper(school.name, null, null, null)
                }
            }

            for(school in schoolSatScoresList){
                if(zip.containsKey(school.name?.lowercase())){
                    zip[school.name!!.lowercase()]?.satCriticalReadingAvgScore = school.satCriticalReadingAvgScore!!
                    zip[school.name!!.lowercase()]?.satMathAvgScore = school.satMathAvgScore!!
                    zip[school.name!!.lowercase()]?.satWritingAvgScore = school.satWritingAvgScore!!
                }
            }

            for(schoolEntry in zip.entries) {
                if(schoolEntry.value.satCriticalReadingAvgScore.equals(null) ||
                    schoolEntry.value.satMathAvgScore.equals(null) ||
                    schoolEntry.value.satWritingAvgScore.equals(null)) {
                    continue;
                }
                _schools.add(schoolEntry.value)
            }
        }
    }
}