package com.example.android.movieapp.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.movieapp.R
import com.example.android.movieapp.database.UserCredentials
import com.example.android.movieapp.databinding.FragmentRegisterBinding
import com.example.android.movieapp.utils.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentRegisterBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register, container, false
        )

        //ViewModel
        val registerViewModel: RegisterViewModel =
            ViewModelProvider(this)[RegisterViewModel::class.java]
        binding.registerViewModel = registerViewModel

        binding.lifecycleOwner = this

        //sign up button clicked
        registerViewModel.onSignUp.observe(viewLifecycleOwner) {

            val userCredentials = UserCredentials(
                name = binding.createUsername.text.toString(),
                password = binding.createPassword.text.toString()
            )

            hideKeyboard()

            registerViewModel.validation(userCredentials)
        }

        //sign un button clicked
        registerViewModel.onSignIn.observe(viewLifecycleOwner) {
            this.findNavController()
                .navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

        //check if new user
        registerViewModel.newUser.observe(viewLifecycleOwner) { credentials ->

            if (credentials != null) {
                this.findNavController()
                    .navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())

                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.registration_successful),
                    Snackbar.LENGTH_SHORT
                ).show()

            } else {
                //if validation is false show message
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.missing_info),
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        }

        // Inflate the layout for this fragment
        return binding.root
    }
}