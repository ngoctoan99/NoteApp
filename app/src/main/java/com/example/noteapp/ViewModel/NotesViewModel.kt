package com.example.noteapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.noteapp.Database.NotesDatabase
import com.example.noteapp.Model.Notes
import com.example.noteapp.Repository.NotesRespository

class NotesViewModel(application: Application): AndroidViewModel(application) {
    val respository : NotesRespository
    init {
        val daoNotes = NotesDatabase.getDatabaseInstance(application).myNoteDao()
        respository = NotesRespository(daoNotes)
    }
    fun addNote(notes : Notes){
        respository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> = respository.getallNotes()

    fun getHighNotes(): LiveData<List<Notes>> = respository.getHighNotes()

    fun getLowNotes(): LiveData<List<Notes>> = respository.getLowNotes()

    fun getMediumNotes(): LiveData<List<Notes>> = respository.getMediumNotes()

    fun deleteNotes(id: Int){
        respository.deleteNotes(id)
    }
    fun updateNotes(notes : Notes){
        respository.updateNotes(notes)
    }
}