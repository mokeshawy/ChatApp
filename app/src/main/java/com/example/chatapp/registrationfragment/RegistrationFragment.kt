package com.example.chatapp.registrationfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    lateinit var binding                : FragmentRegistrationBinding
    private val registrationViewModel   : RegistrationViewModel by viewModels()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Connect whit view model
        binding.lifecycleOwner          = this
        binding.registrationVarModel    = registrationViewModel


        binding.btnRegisterAccpunt.setOnClickListener {
            // call register function
            registrationViewModel.registerNewAccount(requireActivity() , view )
        }


        binding.btnLogIn.setOnClickListener {
            // call fun go to login page
            registrationViewModel.goLogInPage( view )
        }

    }
}