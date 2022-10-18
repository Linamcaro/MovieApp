package com.example.android.movieapp.ui.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.movieapp.databinding.FragmentLoginBinding
import com.example.android.movieapp.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            com.example.android.movieapp.R.layout.fragment_login, container, false
        )

        //viewModel
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.loginViewModel = loginViewModel

        binding.lifecycleOwner = this

        //sign in button clicked
        loginViewModel.onSignIn.observe(viewLifecycleOwner) {
            if (it == true) {
                val name = binding.usernameEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                hideKeyboard()

                loginViewModel.validation(name, password)
            }
        }

        //sign up button clicked
        loginViewModel.onSignUp.observe(viewLifecycleOwner) {
            if (it == true) {
                this.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }

        //login
        loginViewModel.loginData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())

                loginViewModel.isLoggedIn()
            } else {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(com.example.android.movieapp.R.string.invalid_login),
                    Snackbar.LENGTH_SHORT
                ).show()

                loginViewModel.isNotLoggedIn()
            }
        })

        return binding.root
    }

}