package com.polishone.polishproducts.feature.createnote.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.polishone.polishproducts.databinding.FragmentCreateNoteBinding

class CreateNoteFragment : Fragment() {
    /**
     * declare variables and views
     */
    private var _binding: FragmentCreateNoteBinding? = null
    val binding: FragmentCreateNoteBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateNoteBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCreateNoteBinding.bind(view)
    }
}
