package com.polishone.polishproducts.feature.listofnotes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.polishone.polishproducts.R
import com.polishone.polishproducts.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {
    /**
     * declare views and variables
     */
    private val TAG = "NOTESFRAGMENT"
    private var _binding: FragmentNotesBinding? = null
    val binding: FragmentNotesBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNotesBinding.bind(view)

        // on fab, navigate to createNoteFragment
        binding.notesFragmentFab.setOnClickListener {
            findNavController().navigate(R.id.createNoteFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
