package com.curiousapps.keepnote

import android.view.InputQueue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.curiousapps.keepnote.data.NoteEntity
import com.curiousapps.keepnote.databinding.ListItemBinding

class NotesListAdapter(
    private val notesList: List<NoteEntity>,
    private val listener: ListItemListener):
    RecyclerView.Adapter<NotesListAdapter.ViewHolder>()
 {

     val selectedNotes = arrayListOf<NoteEntity>()
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
             root.setOnClickListener{
                 listener.onItemClick(noteId = note.id)
             }
             noteFab.setOnClickListener {
                 if (selectedNotes.contains(note)){
                     selectedNotes.remove(note)
                     noteFab.setImageResource(R.drawable.ic_notes)
                 }else{
                     selectedNotes.add(note)
                     noteFab.setImageResource(R.drawable.ic_check)
                 }
                 listener.onItemSelectionChange()
             }
             noteFab.setImageResource(
                 if (selectedNotes.contains(note)){
                     R.drawable.ic_check
                 }else{
                     R.drawable.ic_notes
                 }
             )
         }
     }

     override fun getItemCount() = notesList.size

     interface ListItemListener{
         fun onItemClick(noteId: Int)
         fun onItemSelectionChange()
     }
 }