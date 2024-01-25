package com.curiousapps.keepnote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.curiousapps.keepnote.data.NoteEntity
import com.curiousapps.keepnote.databinding.ListItemBinding

class NotesListAdapter(
    private val notesList: List<NoteEntity>):
    RecyclerView.Adapter<NotesListAdapter.ViewHolder>()
 {
     inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

         val binding = ListItemBinding.bind(itemView)
     }

     override fun onCreateViewHolder(
         parent: ViewGroup,
         viewType: Int
     ): NotesListAdapter.ViewHolder {
         val inflater = LayoutInflater.from(parent.context)
         val view = inflater.inflate(R.layout.list_item, parent, false)
         return ViewHolder(view)
     }

     override fun onBindViewHolder(holder: NotesListAdapter.ViewHolder, position: Int) {
         val note = notesList[position]
         with(holder.binding){
             noteTextView.text = note.text
         }
     }

     override fun getItemCount() = notesList.size
 }