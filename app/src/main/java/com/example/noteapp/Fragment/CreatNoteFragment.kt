package com.example.noteapp.Fragment

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.noteapp.Model.Notes
import com.example.noteapp.R
import com.example.noteapp.ViewModel.NotesViewModel
import com.example.noteapp.databinding.FragmentCreatNoteBinding
import java.util.*

class CreatNoteFragment : Fragment() {
    lateinit var binding : FragmentCreatNoteBinding
    var priority: String = "1"
    val viewmodel : NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreatNoteBinding.inflate(layoutInflater,container,false)
        binding.btnSaveNotes.setOnClickListener {
            creatNotes(it)
        }
        binding.pGreen.setOnClickListener {
            priority = "2"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
        }
        binding.pRed.setOnClickListener {
            binding.pRed.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority = "3"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }
        return binding.root
    }

    private fun creatNotes(it: View?) {
        val title = binding.edtTitle.text.toString()
        val subTitle = binding.edtSubTitle.text.toString()
        val note = binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("dd MMMM, yyyy", d.time)
        val data = Notes(null, title = title, subTitle = subTitle, notes = note, date = notesDate.toString(),priority)
        viewmodel.addNote(data)
        Toast.makeText(context,"Successful",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_creatNoteFragment_to_homeFragment)
    }
}