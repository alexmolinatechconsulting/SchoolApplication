package com.example.schoolapplication.repository

import com.example.schoolapplication.api.SchoolApi
import com.example.schoolapplication.repository.data.SchoolWrapper
import androidx.compose.runtime.mutableStateListOf
import com.example.schoolapplication.data.School
import com.example.schoolapplication.data.SchoolSatScores
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolRepository(private val apiImpl : SchoolApi) {

    private var _schools = mutableStateListOf<SchoolWrapper>()
    val schools : List<SchoolWrapper> = _schools

    fun loadSchools() {
        // get schools and then zip
        CoroutineScope(Dispatchers.IO).launch(Dispatchers.IO) {
            val schoolList = apiImpl.getSchools()
            val schoolSatScoresList = apiImpl.getSatScores()

            // just in case the lists from the api arent one to one
            var zip = mutableMapOf<String, SchoolWrapper>()
            for(school in schoolList){
                if(!zip.containsKey(school.name)){
                    zip[school.name!!] =
                        SchoolWrapper(school.name, null, null, null)
                }
            }

            for(school in schoolSatScoresList){
                if(zip.containsKey(school.name)){
                    zip[school.name]?.satCriticalReadingAvgScore = school.satCriticalReadingAvgScore!!
                    zip[school.name]?.satMathAvgScore = school.satMathAvgScore!!
                    zip[school.name]?.satWritingAvgScore = school.satWritingAvgScore!!
                }
            }

            for(schoolEntry in zip.entries) {
                _schools.add(schoolEntry.value)
            }
        }
    }
}