package com.example.schoolapplication.api

import com.example.schoolapplication.data.School
import com.example.schoolapplication.data.SchoolSatScores
import retrofit2.Retrofit

class SchoolApiImpl(private var retrofit : Retrofit) : SchoolApi {

    override suspend fun getSchools(token : String, select : String) : List<School> {
        val service = retrofit.create(SchoolApi::class.java)
        return service.getSchools(token)
    }

    override suspend fun getSatScores(token : String, select : String): List<SchoolSatScores> {
        val service = retrofit.create(SchoolApi::class.java)
        return service.getSatScores(token)
    }
}