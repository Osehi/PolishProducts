package com.polishone.polishproducts.feature.listofnotes.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.polishone.polishproducts.R
import com.polishone.polishproducts.common.constants.Resource
import com.polishone.polishproducts.databinding.FragmentNotesBinding
import com.polishone.polishproducts.feature.listofnotes.data.model.Note
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesFragment : Fragment(), TaskClicker {
    /**
     * declare views and variables
     */
    private val TAG = "NOTESFRAGMENT"
    private var _binding: FragmentNotesBinding? = null
    val binding: FragmentNotesBinding get() = _binding!!
    private val getTasksViewModel: GetTasksViewModel by viewModels()
    private lateinit var displayTaskAdapter: DisplayTaskAdapter
    private lateinit var myRecyclerView: RecyclerView

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
        // initialize views and variables
        myRecyclerView = binding.notesFragmentRecyclerview
        myRecyclerView.layoutManager = LinearLayoutManager(requireActivity())

        // activate the viewModel
        getTasksViewModel.getAllTasks()

        // on fab, navigate to createNoteFragment
        binding.notesFragmentFab.setOnClickListener {
            findNavController().navigate(R.id.createNoteFragment)
        }

        initObserver()
//        setRecyclerviewScrollingListener()
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                getTasksViewModel.getTasksResponse.collect {
                    when (it) {
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.INVISIBLE
                            Log.d(TAG, "${it.data.notes}")
                            val allNotes: List<Note?>? = it.data.notes
//                            for (item in ) {
//
//                            }
                            displayTaskAdapter = DisplayTaskAdapter(allNotes, this@NotesFragment)
                            myRecyclerView.adapter = displayTaskAdapter
                            displayTaskAdapter.notifyDataSetChanged()
                        }
                        is Resource.Error -> {
                            Snackbar.make(
                                binding.root,
                                "${it.message}",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                        is Resource.Loading -> {
                            Log.d(TAG, "is it sensing loading: $it")
                            binding.progressBar.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun setRecyclerviewScrollingListener() {
        myRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    getTasksViewModel.getAllTasks()
                }
            }
        })
    }

    override fun onclickItem(currentTask: Note, position: Int) {
        Toast.makeText(requireContext(),
            "here is the item content clicked: ${currentTask.title}",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onClickItemEllipses(currentTask: Note, position: Int, view: View) {
        TODO("Not yet implemented")
    }
}
