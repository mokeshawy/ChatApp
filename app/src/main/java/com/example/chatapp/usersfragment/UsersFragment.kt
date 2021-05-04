package com.example.chatapp.usersfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.chatapp.R
import com.example.chatapp.adapter.RecyclerUserAdapter
import com.example.chatapp.databinding.FragmentUsersBinding
import com.example.chatapp.model.UserModel

class UsersFragment : Fragment() {

    lateinit var binding : FragmentUsersBinding
    private val usersViewModel : UsersViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentUsersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Connect with view model
        binding.lifecycleOwner  = this
        binding.usersVarModel   = usersViewModel

        // get all data for user from data base
        usersViewModel.getUser(requireActivity() , binding.ivUserImageProfile )
        usersViewModel.mUserLiveArrayList.observe(viewLifecycleOwner , Observer {
            binding.rvUsers.adapter = RecyclerUserAdapter(it)
        })

        // fun go to profile page
        usersViewModel.goToProfile(view , binding.ivUserImageProfile)
    }
}