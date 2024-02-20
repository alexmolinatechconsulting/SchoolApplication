package com.example.schoolapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.schoolapplication.repository.SchoolRepository
import org.koin.android.ext.android.get
import androidx.activity.compose.setContent
import com.example.schoolapplication.ui.SchoolWithSatScoresList

class MainActivity : AppCompatActivity() {

    // call the load repo function here once and paste the list - so its not called all the time
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val schoolRepo = get<SchoolRepository>()
        schoolRepo.loadSchools() // how to only load this once

        setContent {
            SchoolWithSatScoresList(sr = schoolRepo)
        }
    }
}