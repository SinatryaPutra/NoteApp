package com.mynotes.notesappmvmkotlin.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.mynotes.notesappmvmkotlin.R
import com.mynotes.notesappmvmkotlin.ViewModel.NotesViewModel
import com.mynotes.notesappmvmkotlin.databinding.FragmentHomeBinding
import com.mynotes.notesappmvmkotlin.ui.Adapter.NotesAdapter

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)

        //menampilkan catatan difragment home
        viewModel.getNotes().observe(viewLifecycleOwner,{ notesList ->
            binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcvAllNotes.adapter= NotesAdapter(requireActivity(),notesList)
        })

        //mengatur tombol add untuk membuat note baru yang terhubung dengan model
        binding.btnAddNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        return binding.root
    }

}