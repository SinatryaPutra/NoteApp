package com.mynotes.notesappmvmkotlin.ui.Fragment

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mynotes.notesappmvmkotlin.Model.Notes
import com.mynotes.notesappmvmkotlin.R
import com.mynotes.notesappmvmkotlin.ViewModel.NotesViewModel
import com.mynotes.notesappmvmkotlin.databinding.FragmentEditNotesBinding
import java.util.*

class EditNotesFragment : Fragment() {

    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding:FragmentEditNotesBinding
    lateinit var mShare : Button
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentEditNotesBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)

        //mengubah data sekaligus menampilkan data note yang sudah di create dan ingin di rubah
        binding.edtTitle.setText(oldNotes.data.title)
        binding.edtSubTitle.setText(oldNotes.data.subTitle)
        binding.edtNotes.setText(oldNotes.data.notes)

        //menampilkan priority yang sudah dibuat di create
        when(oldNotes.data.Priority){
            "1" -> {
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }

        }
        //mengatur tobol dari setiap priority
        binding.pGreen.setOnClickListener{
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }
        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        //mengatur tombol save pada fragment
        binding.btnSaveNotes.setOnClickListener{
            updateNotes(it)
        }

        return binding.root
    }
    private fun updateNotes(it:View){
        val title = binding.edtTitle.text.toString()
        val subTitle = binding.edtSubTitle.text.toString()
        val notes = binding.edtNotes.text.toString()

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMM d, yyy", d.time)
        val data = Notes(oldNotes.data.id,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewModel.updateNotes(data)

        //jika data berhasil di save maka akan ada tampilan pesan
        Toast.makeText(requireContext(), "Pembaruan sukses", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //mengatur button share
        when (item.itemId) {
            R.id.action_share -> {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"

                val shareBody = "uji coba"

                val shareSubject = "coba coba"

                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)

                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject)
                startActivity(Intent.createChooser(sharingIntent, "Share using"))
            }
        }

        //mengatur button delete
        if (item.itemId==R.id.menu_delete){

            val bottomSheet: BottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textviewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textviewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textviewYes?.setOnClickListener{
                viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()
                Toast.makeText(requireContext(), "Penghapusan sukses", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
            }

            textviewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }
}