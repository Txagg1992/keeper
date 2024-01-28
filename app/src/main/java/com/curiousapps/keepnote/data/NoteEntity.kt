package com.curiousapps.keepnote.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.curiousapps.keepnote.NEW_NOTE_ID
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "notesTable")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date: Date,
    var text: String
) : Parcelable {

    constructor() : this(NEW_NOTE_ID, Date(), "")
    constructor(date: Date, text: String) : this(NEW_NOTE_ID, date, text)
}