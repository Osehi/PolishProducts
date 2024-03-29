package com.polishone.polishproducts.feature.login.presentation

import android.os.Bundle
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
import com.polishone.polishproducts.common.utils.networkstatus.NetworkStatusHelper
import com.polishone.polishproducts.common.utils.uihelpers.hideKeyboard
import com.polishone.polishproducts.data.sharedpreference.Preferences
import com.polishone.polishproducts.databinding.FragmentLoginBinding
import com.polishone.polishproducts.feature.login.data.network.model.LoginRequestBody
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    /**
     * declare variables and views
     */
    @Inject
    lateinit var preferences: Preferences
    private val TAG = "LOGINFRAGMENT"
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var receivedEmail: String
    private lateinit var receivedPassword: String
    private var pleaseWaitDialog: AlertDialog? = null
    private var _binding: FragmentLoginBinding? = null
    val binding get() = _binding!!

    lateinit var networkStatusHelper: NetworkStatusHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

//        val isNetworkAvailable
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)
        pleaseWaitDialog = myDialog()
        /*
        networkStatusHelper = NetworkStatusHelper(requireContext())
        networkStatusHelper.observe(viewLifecycleOwner) {

            binding.networkStatus.text = when (it) {
                NetworkStatus.Unavailable -> display("There is no Internet")
                NetworkStatus.Available -> display("There is Internet")
            }
        }
         */

        // on click of the login button
        binding.loginFragmentLoginButtonBtn.setOnClickListener {
            hideKeyboard()
            // receive values from the input fields
            receivedEmail = binding.loginFragmentEmailTextinputlayoutEmailTiedt.text.toString()
            receivedPassword = binding.loginFragmentEmailTextinputlayoutPasswordTiedt.text.toString()
            // no validation yet
            if (receivedEmail.isEmpty() || receivedPassword.isEmpty()) {
                Snackbar.make(
                    binding.root,
                    "Input fields cannot be empty",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                // perform the network call
                loginViewModel.getUserLoggeIn(LoginRequestBody(receivedEmail, receivedPassword))
                pleaseWaitDialog?.let { it.show() }
            }
        }

        // on click of Create account, navigate to "Register"
        binding.loginFragmentCreateAccountTv.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

        initObserver()
    }

    fun display(msg: String): String {
        return msg
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.loginResponse.collect {
                    when (it) {
                        is Resource.Success -> {
                            pleaseWaitDialog?.let { it.dismiss() }
                            val token = it.data.token
                            if (token != null) {
                                preferences.putToken(token)
                            }
                            Snackbar.make(
                                binding.root,
                                "You have successfully logged in",
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
