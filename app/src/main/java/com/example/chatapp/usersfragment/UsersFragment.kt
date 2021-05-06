package com.example.chatapp.usersfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.`interface`.OnClick
import com.example.chatapp.adapter.RecyclerUserAdapter
import com.example.chatapp.databinding.FragmentUsersBinding
import com.example.chatapp.firebase.FirebaseService
import com.example.chatapp.model.UserModel
import com.google.firebase.iid.FirebaseInstanceId

@Suppress("DEPRECATION")
class UsersFragment : Fragment() , OnClick{

    lateinit var binding        : FragmentUsersBinding
    private val usersViewModel  : UsersViewModel by viewModels()

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

        FirebaseService.sharadPreference = requireActivity().getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            FirebaseService.token = it.token
        }

        // get all data for user from data base
        usersViewModel.getUser(requireActivity() , binding.ivUserImageProfile )
        usersViewModel.mUserLiveArrayList.observe(viewLifecycleOwner , Observer {
            binding.rvUsers.adapter = RecyclerUserAdapter(it , this)
        })

        // fun go to profile page
        usersViewModel.goToProfile(view , binding.ivUserImageProfile)
    }

    override fun onClick( viewHolder: RecyclerUserAdapter.ViewHolder, dataSet: UserModel, position: Int ) {
        viewHolder.itemView.setOnClickListener {

            var action = UsersFragmentDirections.actionUsersFragmentToChatFragment(dataSet)
            findNavController().navigate(action)
        }
    }
}