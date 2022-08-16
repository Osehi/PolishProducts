package com.polishone.polishproducts.feature.listofnotes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
