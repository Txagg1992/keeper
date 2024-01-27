package com.curiousapps.keepnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiousapps.keepnote.data.AppDatabase
import com.curiousapps.keepnote.data.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditorViewModel(app: Application) : AndroidViewModel(app) {

    private val dataBase = AppDatabase.getInstance(app)
    val currentNote = MutableLiveData<NoteEntity>()

    fun getNoteById(noteId: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val note =
                    if (noteId != NEW_NOTE_ID){
                        dataBase?.noteDao()?.getNoteById(noteId)
                    }else{
                        NoteEntity()
                    }
                currentNote.postValue(note)
            }
        }
    }

    fun updateNote() {

        currentNote.value?.let {
            it.text = it.text.trim()
            if (it.id == NEW_NOTE_ID && it.text.isEmpty()){
                return
            }

            viewModelScope.launch {
                withContext(Dispatchers.IO){
                    if (it.text.isEmpty()){
                        dataBase?.noteDao()?.deleteNote(it)
                    }else{
                        dataBase?.noteDao()?.insertNote(it)
                    }
                }
            }
        }
    }
}