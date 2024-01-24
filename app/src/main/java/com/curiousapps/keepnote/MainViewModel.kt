package com.curiousapps.keepnote

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.curiousapps.keepnote.data.NoteEntity
import com.curiousapps.keepnote.data.SampleDataProvider

class MainViewModel : ViewModel() {

    val notesList = MutableLiveData<List<NoteEntity>>()

    init {
       notesList.value = SampleDataProvider.getNotes()
    }
}