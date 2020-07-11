package com.example.girlyeshop

import android.content.Context

class UserClass(context: Context){
    private val mySharedPreferences = context.getSharedPreferences("myPreferences",0)

    //Save boolean values into the shared preferences
    fun putBoolean(key: String,value: Boolean){
        mySharedPreferences.edit().putBoolean(key,value).apply()
    }

    //Get boolean values
    fun getBoolean(key: String): Boolean{
        return mySharedPreferences.getBoolean(key,false)
    }

    //Save string values
    fun putString(key: String,value: String){
        mySharedPreferences.edit().putString(key, value).apply()
    }

    //Get string values
    fun getString(key: String): String?{
        return mySharedPreferences.getString(key,null)
    }

    fun putInt(key: String,value: Int){
        mySharedPreferences.edit().putInt(key,value).apply()
    }

    fun getInt(key: String): Int{
        return mySharedPreferences.getInt(key,0)
    }
}