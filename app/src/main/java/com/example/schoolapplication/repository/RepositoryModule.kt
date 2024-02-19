package com.example.schoolapplication.repository

import org.koin.dsl.module

val RepositoryModule = module {
    single{SchoolRepository(get())}
}