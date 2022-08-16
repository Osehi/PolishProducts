package com.polishone.polishproducts.feature.createnote.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.polishone.polishproducts.R
import com.polishone.polishproducts.common.utils.stattisticdata.PriorityData
import com.polishone.polishproducts.databinding.FragmentCreateNoteBinding

class CreateNoteFragment : Fragment() {
    /**
     * declare variables and views
     */
    private val TAG = "CREATENOTEFRAG"
    lateinit var selectedPriority: String
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
        // populate the spinner
        val prioritySpinner = ArrayAdapter<String>(requireContext(), R.layout.spinner_list_item, PriorityData.priorityData)
        prioritySpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.createNoteFragmentBugetPeriodSpinner.adapter = prioritySpinner
        binding.createNoteFragmentBugetPeriodSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedPriority = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }
        // on click on the saveTask button
        binding.createNoteFragmentSaveTaskButtonBtn.setOnClickListener {

        }
    }
}
