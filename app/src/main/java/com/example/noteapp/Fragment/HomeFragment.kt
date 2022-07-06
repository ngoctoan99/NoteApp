package com.example.noteapp.Fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.noteapp.Adapter.NotesAdapter
import com.example.noteapp.Model.Notes
import com.example.noteapp.R
import com.example.noteapp.ViewModel.NotesViewModel
import com.example.noteapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding
    val viewModle : NotesViewModel by viewModels()
    var oldMyNote = arrayListOf<Notes>()
    lateinit var  adapter : NotesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        viewModle.getNotes().observe(viewLifecycleOwner,{notesList->
            oldMyNote = notesList as ArrayList<Notes>
           binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
            adapter = NotesAdapter(requireContext(),notesList)
            binding.rcvAllNotes.adapter = adapter

        })
        binding.btnAddNote.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_creatNoteFragment)
        }
        binding.filterHigh.setOnClickListener {
            viewModle.getHighNotes().observe(viewLifecycleOwner,{notesList->
                oldMyNote = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = adapter
            })

        }
        binding.filterMedium.setOnClickListener {
            viewModle.getMediumNotes().observe(viewLifecycleOwner,{notesList->
                oldMyNote = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = adapter
            })

        }
        binding.filterLow.setOnClickListener {
            viewModle.getLowNotes().observe(viewLifecycleOwner,{notesList->
                oldMyNote = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = adapter
            })
        }
        binding.allNote.setOnClickListener {
            viewModle.getNotes().observe(viewLifecycleOwner,{notesList->
                oldMyNote = notesList as ArrayList<Notes>
                adapter = NotesAdapter(requireContext(),notesList)
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = adapter
            })
        }

        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item = menu.findItem(R.id.menu_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint="Enter Note Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                NotesFiltering(p0)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(p0: String?) {
        val newFilteredList = arrayListOf<Notes>()
        for(i in oldMyNote){
            if(i.title.contains(p0!!) || i.subTitle.contains(p0!!)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }

}