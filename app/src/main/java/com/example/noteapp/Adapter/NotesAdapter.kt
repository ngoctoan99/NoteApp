package com.example.noteapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.Fragment.HomeFragmentDirections
import com.example.noteapp.Model.Notes
import com.example.noteapp.R
import com.example.noteapp.databinding.ItemNoteBinding

class NotesAdapter(val requireContext : Context, var notesList: List<Notes>): RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {
    class notesViewHolder( val binding : ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    fun filtering(newFilterList : ArrayList<Notes>){
        notesList = newFilterList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.notesTitle.text = data.title.toString()
        holder.binding.notesSubTitle.text = data.subTitle.toString()
        holder.binding.notesDate.text = data.date.toString()
        when(data.priority){
            "1"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_doy)
            }
            "3"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "2"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }

        }
        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = notesList.size
}