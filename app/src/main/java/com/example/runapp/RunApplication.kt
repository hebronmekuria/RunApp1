package com.example.runapp

import android.app.Application

class RunApplication: Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}