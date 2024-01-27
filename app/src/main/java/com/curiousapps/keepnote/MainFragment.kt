package com.curiousapps.keepnote

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.curiousapps.keepnote.databinding.FragmentMainBinding

class MainFragment :
    Fragment(),
    NotesListAdapter.ListItemListener{

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: NotesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)

        binding =FragmentMainBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        with(binding.recyclerView){
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
        }

        viewModel.notesList?.observe(viewLifecycleOwner, Observer{
            Log.e("VM note Log", it.toString())
            adapter = NotesListAdapter(it, this@MainFragment)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)


        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        val menuId =
            if (this::adapter.isInitialized &&
                adapter.selectedNotes.isNotEmpty()){
                R.menu.menu_main_selected_items
            } else {
                R.menu.menu_main
            }
        inflater.inflate(menuId, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_sample_data -> addSampleData()
            R.id.action_delete -> deleteSelectedNotes()
            R.id.action_delete_all -> deleteAllNotes()
            else -> super.onOptionsItemSelected(item)
        }


    }

    private fun deleteAllNotes(): Boolean {
        viewModel.deleteAllNotes()
        return true
    }

    private fun deleteSelectedNotes(): Boolean {

        viewModel.deleteNotes(adapter.selectedNotes)
        Handler(Looper.getMainLooper()).postDelayed({
            adapter.selectedNotes.clear()
            requireActivity().invalidateOptionsMenu()
        }, 100)
        return true
    }

    private fun addSampleData(): Boolean {
        viewModel.addSampleData()
        return true
    }

    override fun onItemClick(noteId: Int) {
        Log.e("mainFrag", "onItemClick: received noteId $noteId")

        val action = MainFragmentDirections.actionEditorFragment(noteId)
        findNavController().navigate(action)
    }

    override fun onItemSelectionChange() {
        requireActivity().invalidateOptionsMenu()
    }

}