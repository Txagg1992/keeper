package com.curiousapps.keepnote

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.curiousapps.keepnote.data.AppDatabase
import com.curiousapps.keepnote.data.NoteDao
import com.curiousapps.keepnote.data.NoteEntity
import com.curiousapps.keepnote.data.SampleDataProvider
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var dao: NoteDao
    private lateinit var database: AppDatabase
    @Before
    fun createDb(){

        val appContext = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.noteDao()!!
    }

    @After
    fun closeDb(){
        database.close()

    }
    @Test
    fun createNotes() {

        dao.insertAll(SampleDataProvider.getNotes())
        val count = dao.getCount()
        assertEquals(count, SampleDataProvider.getNotes().size)
    }

    @Test
    fun insertNote() {

        val note = NoteEntity()
        note.text = "Nice Tits"
        dao.insertNote(note)

        val savedNote = dao.getNoteById(1)
        assertEquals(savedNote?.id ?:0, 1)
        assertEquals(savedNote?.text ?: "", "Nice Tits")
    }

}