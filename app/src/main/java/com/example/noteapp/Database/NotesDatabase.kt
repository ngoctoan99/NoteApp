package com.example.noteapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.DAO.NotesDAO
import com.example.noteapp.Model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase :RoomDatabase(){
    abstract fun myNoteDao(): NotesDAO
    companion object
    {
        @Volatile
        var INSTANCE : NotesDatabase?= null
        fun getDatabaseInstance(context : Context):NotesDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val roomDatabaseInstance = Room.databaseBuilder(context,NotesDatabase::class.java,"Notes").allowMainThreadQueries().build()
                INSTANCE =  roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }
}