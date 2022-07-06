package com.example.noteapp.Fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.noteapp.Model.Notes
import com.example.noteapp.R
import com.example.noteapp.ViewModel.NotesViewModel
import com.example.noteapp.databinding.FragmentEditNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditNoteFragment : Fragment() {
    val oldNotes by navArgs<EditNoteFragmentArgs>()
    lateinit var binding: FragmentEditNoteBinding
    var priority = "1"
    var enable = 0
    val viewmodel : NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        binding.edtTitle.setText(oldNotes.data.title)
        binding.edtSubTitle.setText(oldNotes.data.subTitle)
        binding.edtNotes.setText(oldNotes.data.notes)
        when (oldNotes.data.priority) {
            "1" -> {
                priority = "1"
                binding.pRed.setImageResource(R.drawable.ic_baseline_check_24)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }

            "3" -> {
                priority = "3"
                binding.pYellow.setImageResource(R.drawable.ic_baseline_check_24)
                binding.pGreen.setImageResource(0)
                binding.pRed.setImageResource(0)
            }

            "2" -> {
                priority = "2"
                binding.pGreen.setImageResource(R.drawable.ic_baseline_check_24)
                binding.pYellow.setImageResource(0)
                binding.pRed.setImageResource(0)
            }
        }
        binding.pGreen.setOnClickListener {
            priority = "2"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_check_24)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
        }
        binding.pRed.setOnClickListener {
            priority = "1"
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
        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
        }

            return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.edtTitle.text.toString()
        val subTitle = binding.edtSubTitle.text.toString()
        val note = binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("dd MMMM, yyyy", d.time)
        val data = Notes(oldNotes.data.id, title = title, subTitle = subTitle, notes = note, date = notesDate.toString(),priority)
        viewmodel.updateNotes(data)
        Toast.makeText(context,"Update Successful", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            val bottomSheet : BottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)
            val textYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)
            textYes?.setOnClickListener {
                viewmodel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()
                    Navigation.findNavController(requireView()).navigate(R.id.action_editNoteFragment_to_homeFragment)
            }
            textNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}