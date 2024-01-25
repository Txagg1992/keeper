package com.curiousapps.keepnote

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        binding =FragmentMainBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        with(binding.recyclerView){
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
        }

        viewModel.notesList.observe(viewLifecycleOwner, Observer{
            Log.e("VM note Log", it.toString())
            adapter = NotesListAdapter(it, this@MainFragment)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)


        })

        return binding.root
    }

    override fun onItemClick(noteId: Int) {
        Log.e("mainFrag", "onItemClick: received noteId $noteId")

        val action = MainFragmentDirections.actionEditorFragment(noteId)
        findNavController().navigate(action)
    }

}