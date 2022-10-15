package com.example.runapp

data class Run (
    val activity:String,
    val distance:Float,
    val duration:Float,
    val experience:Int
): java.io.Serializable