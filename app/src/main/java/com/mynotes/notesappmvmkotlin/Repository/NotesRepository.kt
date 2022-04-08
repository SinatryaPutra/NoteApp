package com.mynotes.notesappmvmkotlin.Repository

import androidx.lifecycle.LiveData
import com.mynotes.notesappmvmkotlin.Dao.NotesDao
import com.mynotes.notesappmvmkotlin.Model.Notes

//repository bertanggung jawab sebagai untuk semua data yang digunakan di aplikasi
// mau itu menyimpan data, menampilkan data, menghapus data, maupun mengubah data
class NotesRepository(val dao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>>{
        return dao.getNotes()
    }

    fun insertNotes(notes : Notes){
        dao.insertNotes(notes)
    }

    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }

}