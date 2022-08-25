package com.polishone.polishproducts.feature.createnote.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.polishone.polishproducts.R
import com.polishone.polishproducts.common.constants.Resource
import com.polishone.polishproducts.common.utils.extensions.myDialog
import com.polishone.polishproducts.common.utils.stattisticdata.PriorityData
import com.polishone.polishproducts.common.utils.uihelpers.setPriorityToNumber
import com.polishone.polishproducts.databinding.FragmentCreateNoteBinding
import com.polishone.polishproducts.feature.createnote.data.model.CreateNoteRequestBody
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateNoteFragment : Fragment() {
    /**
     * declare variables and views
     */
    private val TAG = "CREATENOTEFRAG"
    lateinit var selectedPriority: String
    private var pleaseWaitDialog: AlertDialog? = null
    private val createNoteViewModel: CreateNoteViewModel by viewModels()
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
        // Initialize values and views
        pleaseWaitDialog = myDialog()

        // populate the spinner
        val prioritySpinner = ArrayAdapter<String>(requireContext(), R.layout.spinner_list_item, PriorityData.priorityData)
        prioritySpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.createNoteFragmentBugetPeriodSpinner.adapter = prioritySpinner
        binding.createNoteFragmentBugetPeriodSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedPriority = parent?.getItemAtPosition(position).toString()
                Log.d(TAG, "Hello here is the content: $selectedPriority")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }
        // on click on the saveTask button
        binding.createNoteFragmentSaveTaskButtonBtn.setOnClickListener {
            // capture the input field
            val taskTitle = binding.createNoteFragmentTaskTitleTiedt.text?.trim().toString()
            val taskContent = binding.createNoteFragmentTaskContentTiedt.text?.trim().toString()
            // validate the fields
            if (taskTitle.isEmpty() || taskContent.isEmpty() || selectedPriority.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    "Please input fields cannot be empty",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                // make a network call
                createNoteViewModel.userCreateNote(
                    CreateNoteRequestBody(taskContent, setPriorityToNumber(selectedPriority), taskTitle)
                )
                pleaseWaitDialog?.let { it.show() }
            }
        }

        initObserver()
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                createNoteViewModel.createNoteResponse.collect {
                    when (it) {
                        is Resource.Success -> {
                            pleaseWaitDialog?.let { it.dismiss() }
                            Snackbar.make(
                                binding.root,
                                "Note is successfully created",
                                Snackbar.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.notesFragment)
                        }
                        is Resource.Error -> {
                            pleaseWaitDialog?.let { it.dismiss() }
                            Snackbar.make(
                                binding.root,
                                "${it.message}",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                        is Resource.Loading -> {
                        }
                    }
                }
            }
        }
    }
}
