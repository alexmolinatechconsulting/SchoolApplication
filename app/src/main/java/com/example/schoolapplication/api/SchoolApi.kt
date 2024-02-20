package com.example.schoolapplication.api

import com.example.schoolapplication.BuildConfig
import com.example.schoolapplication.data.School
import com.example.schoolapplication.data.SchoolSatScores
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SchoolApi {
    @GET(BuildConfig.ENDPOINT_SCHOOLS)
    suspend fun getSchools(
        @Query("\$\$app_token", encoded = true) token : String = BuildConfig.APP_TOKEN,
        @Query("\$select", encoded = true) select : String = BuildConfig.SELECT_SCHOOL_COLUMN_QUERY
    ) : List<School>

    @GET(BuildConfig.ENDPOINT_SCHOOL_SAT_SCORES)
    suspend fun getSatScores(
        @Query("\$\$app_token", encoded = true) token : String = BuildConfig.APP_TOKEN,
        @Query("\$select", encoded = true) select : String = BuildConfig.SELECT_SCHOOL_SAT_SCORES_COLUMN_QUERY
    ) : List<SchoolSatScores>
}