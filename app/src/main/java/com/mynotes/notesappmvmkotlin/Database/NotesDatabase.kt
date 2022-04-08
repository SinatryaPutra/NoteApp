package com.mynotes.notesappmvmkotlin.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mynotes.notesappmvmkotlin.Dao.NotesDao
import com.mynotes.notesappmvmkotlin.Model.Notes
//Database digunakan untuk mendaftarkan DAO dan entities
//class ini berfungsi sebagai titik akses utama bagi koneksi ke local database
@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase(){

    abstract fun myNotesDao(): NotesDao

    companion object{
        @Volatile
        var INSTANCE:NotesDatabase?=null

        fun getDatabaseInstance(context: Context):NotesDatabase{

            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this)
            {
                val roomDatabaseInstance= Room.
                databaseBuilder(context,
                NotesDatabase::class.java,"Notes"
                ).allowMainThreadQueries().build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }
}