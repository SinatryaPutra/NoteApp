package com.mynotes.notesappmvmkotlin.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mynotes.notesappmvmkotlin.Model.Notes
//Data Access Object atau DAO berfungsi untuk mengakses data aplikasi menggunakan library persistensi room
// motede ini memungkinkan kami melakukan operasi baca/tulis pada database
@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes")
    fun getNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM Notes WHERE id =:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)
}