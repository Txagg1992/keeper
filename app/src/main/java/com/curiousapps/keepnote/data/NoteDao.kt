package com.curiousapps.keepnote.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(notes: List<NoteEntity>)
    @Query("SELECT * FROM notesTable ORDER BY date ASC")
    fun getAll(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM notesTable WHERE id = :id")
    fun getNoteById(id: Int):NoteEntity?

    @Query("SELECT COUNT(*) FROM notesTable")
    fun getCount(): Int
    @Delete
    fun deleteNotes(selectedNotes: List<NoteEntity>):Int
    @Query("DELETE FROM notesTable")
    fun deleteAllNotes():Int
    @Delete
    fun deleteNote(note: NoteEntity)

}