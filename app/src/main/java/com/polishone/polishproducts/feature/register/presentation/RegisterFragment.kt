package com.polishone.polishproducts.feature.register.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.polishone.polishproducts.common.utils.uihelpers.hideKeyboard
import com.polishone.polishproducts.databinding.FragmentRegisterBinding
import com.polishone.polishproducts.feature.register.data.network.model.RegisterRequestBody
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class RegisterFragment : Fragment() {
    /**
     * declare variables and views
     */
    private val TAG = "REGISTERFRAGMENT"
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val registerViewModel: RegisterViewModel by viewModels()
    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterBinding.bind(view)
        dialog = myDialog()

        /**
         * register button
         */
        binding.registerFragmentButton.setOnClickListener {
            hideKeyboard()
            // get the name, email and password to register
            val name = binding.registerFragmentTextinputedittextName.text.toString()
            val email = binding.registerFragmentTextinputedittextEmail.text.toString()
            val password = binding.registerFragmentTextinputedittextPassword.text.toString()
            Log.d(TAG, "HERE are the inputs: $name, $email, $password")
            // validate input fields
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    "Input fields cannot be empty",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                val registerBody = RegisterRequestBody(email, name, password)
                registerViewModel.getRegistered(registerBody)
                dialog?.let { it.show() }
            }

        }

        /**
         * observe the response
         */
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                registerViewModel.registerResponse.collect {
                    when (it) {
                        is Resource.Success -> {

                            dialog?.let { it.dismiss() }
//                            Toast.makeText(requireContext(), "${it.data.message}", Toast.LENGTH_SHORT).show()
                            Snackbar.make(
                                binding.root,
                                "${it.data.message}",
                                Snackbar.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.loginFragment)
                        }
                        is Resource.Error -> {
//                            dialog = myDialog()
                            dialog?.let { it.dismiss() }
                            Log.d(TAG, "An error occured: ${it.data?.message}")
//                            Toast.makeText(requireContext(), "${it.data?.message}", Toast.LENGTH_SHORT).show()
                            Snackbar.make(
                                binding.root,
                                "here is the error message:${it.data?.message}",
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
