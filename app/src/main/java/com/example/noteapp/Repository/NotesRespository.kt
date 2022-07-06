package com.example.noteapp.Repository

import androidx.lifecycle.LiveData
import com.example.noteapp.DAO.NotesDAO
import com.example.noteapp.Model.Notes

class NotesRespository(val dao : NotesDAO) {
    fun getallNotes():LiveData<List<Notes>> = dao.getNotes()

    fun getLowNotes():LiveData<List<Notes>> = dao.getLowNotes()

    fun getHighNotes():LiveData<List<Notes>> = dao.getHighNotes()

    fun getMediumNotes():LiveData<List<Notes>> = dao.getMediumNotes()
    fun insertNotes(notes : Notes){
        dao.insertNotes(notes)
    }
    fun deleteNotes(id : Int){
        dao.deleteNotes(id)
    }
    fun updateNotes(notes : Notes){
        dao.updateNotes(notes)
    }
}