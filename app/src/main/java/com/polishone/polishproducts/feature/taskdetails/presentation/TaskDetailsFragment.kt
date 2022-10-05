package com.polishone.polishproducts.feature.taskdetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.polishone.polishproducts.R
import com.polishone.polishproducts.common.constants.DataConstant
import com.polishone.polishproducts.common.utils.converter.PriorityConverter
import com.polishone.polishproducts.databinding.FragmentTaskDetailsBinding
import com.polishone.polishproducts.feature.listofnotes.data.model.Note

class TaskDetailsFragment : Fragment() {
    /**
     * declare views and variables
     */
    private var _binding: FragmentTaskDetailsBinding? = null
    private val binding: FragmentTaskDetailsBinding get() = _binding!!
    private lateinit var taskData: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // capture the task details from bundle
        taskData = arguments?.getParcelable<Note>(DataConstant.TASK_DATA)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTaskDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // set data to views
        binding.taskDetailsFragmentTaskTitleTv.text = taskData.title
        binding.askDetailsFragmentTaskContentTv.text = taskData.content
        binding.taskDetailsFragmentTaskPriorityTv.text = taskData.taskPriority?.let {
            PriorityConverter.convertPriorityNumberToString(
                it
            )
        }
        // on click on the back navigation
        binding.taskDetailsFragmentBackNavCv.setOnClickListener {
            findNavController().navigate(R.id.notesFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
