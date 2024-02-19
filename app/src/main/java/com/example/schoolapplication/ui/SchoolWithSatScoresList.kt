package com.example.schoolapplication.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TextButton
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.schoolapplication.repository.SchoolRepository

// keep a remember state on the repository for schools
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolWithSatScoresList(sr : SchoolRepository) {

    var schoolList = remember{sr.schools}
    var isExpandedMap = BooleanArray(schoolList.size) // maybe this should be remembered

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text("Schools")
                    }
                }
            )
        },
        content = {
            Column {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(schoolList.size) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    TextButton(
                                        onClick = {isExpandedMap[it] = true},
                                    ){
                                        Text(text = schoolList[it].name!!)
                                    }
                                }
                                if(isExpandedMap[it]){
                                    //chevron button down as a second element of the row
                                    Column {

                                    }
                                }
                            }

                            if(isExpandedMap[it]){
                                Row {
                                    Text("R : ${schoolList[it].satCriticalReadingAvgScore}")
                                    Text("M : ${schoolList[it].satMathAvgScore}")
                                    Text("W : ${schoolList[it].satWritingAvgScore}")
                                }
                            }

                            Divider()
                        }
                    }
                }
            }
        }
    )
}