package com.example.chatapp.chatfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.chatapp.R
import com.example.chatapp.adapter.RecyclerChatAdapter
import com.example.chatapp.databinding.FragmentChatBinding
import com.squareup.picasso.Picasso

class ChatFragment : Fragment() {

    lateinit var binding : FragmentChatBinding
    private val chatViewModel : ChatViewModel by viewModels()
    private val usersArgs : ChatFragmentArgs by navArgs()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect with view model
        binding.lifecycleOwner  = this
        binding.chatVarModel    = chatViewModel

        binding.tvUserName.text = usersArgs.userObject.userName

        if( usersArgs.userObject.profileImage == ""){
            binding.ivUserImageProfile.setImageResource(R.drawable.ic_background_avatar)
        }else{
            Picasso.get().load(usersArgs.userObject.profileImage).into(binding.ivUserImageProfile)
        }


        // call fun send message
        binding.btnSendMessage.setOnClickListener {
            chatViewModel.sendMessage( usersArgs.userObject.userId ,
                view ,
                binding.etSendMessage ,
                usersArgs.userObject.userName)
        }

        // show data for message form database
        chatViewModel.readMessage(requireActivity(),usersArgs.userObject.userId)
        chatViewModel.mGetMessageLiveList.observe(viewLifecycleOwner, Observer {
            binding.rvUserMessage.adapter = RecyclerChatAdapter(it)
        })

        // back to user page
        binding.ivBtnBack.setOnClickListener {
            findNavController().navigate(R.id.action_chatFragment_to_usersFragment)
        }
    }
}