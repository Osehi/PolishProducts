package com.polishone.polishproducts.feature.userprofile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.polishone.polishproducts.common.constants.Resource
import com.polishone.polishproducts.databinding.FragmentUserProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class UserProfileFragment : Fragment() {
    /**
     * declare views and variables
     */
    private val TAG = "USERPROFILE"
    private var _binding: FragmentUserProfileBinding? = null
    val binding: FragmentUserProfileBinding get() = _binding!!
    private val userProfileViewModel: UserProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // make the network call
        userProfileViewModel.getUserProfile()

        initObserver()
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                userProfileViewModel.userProfileResponse.collect {
                    when (it) {
                        is Resource.Success -> {
                            Snackbar.make(
                                binding.root,
                                "Profile is successfully retrieved",
                                Snackbar.LENGTH_LONG
                            ).show()
                            binding.userProfileFragmentUsernameValueTv.text = it.data.name
                            binding.userProfileFragmentEmailValueTv.text = it.data.email
                            binding.userProfileFragmentNumberOfNotesValueTv.text = it.data.id
                        }
                        is Resource.Error -> {
                            Snackbar.make(
                                binding.root,
                                "Error occurred",
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
