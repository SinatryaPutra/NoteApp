package com.mynotes.notesappmvmkotlin.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mynotes.notesappmvmkotlin.Database.NotesDatabase
import com.mynotes.notesappmvmkotlin.Model.Notes
import com.mynotes.notesappmvmkotlin.Repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val repository: NotesRepository

    init{
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository=NotesRepository(dao)
    }
    //menambahkan catatan
    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }

    //menampilkan catatan
    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    //menghapus catatan
    fun deleteNotes(id: Int){
        repository.deleteNotes(id)
    }

    //mengubah catatan
    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}