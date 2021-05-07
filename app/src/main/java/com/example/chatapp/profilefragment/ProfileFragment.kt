package com.example.chatapp.profilefragment

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.chatapp.databinding.FragmentProfileBinding
import com.example.chatapp.util.Constants
import java.io.IOException
import java.lang.Exception
import java.util.jar.Manifest

class ProfileFragment : Fragment() {

    lateinit var binding            : FragmentProfileBinding
    private val profileViewModel    : ProfileViewModel by viewModels()
    lateinit var imageUri           : Uri

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connect whit viewModel
        binding.lifecycleOwner  = this
        binding.profileVarModel = profileViewModel


        // operation data for user profile when entry user login profile page
        profileViewModel.getDetailsForUserLogIn( requireActivity() ,
                binding.tvUserName ,
                binding.ivUserImage ,
                binding.ivUserImageProfile ,
                binding.btnUploadedImage)


        // operation for upload image profile
        binding.btnUploadedImage.setOnClickListener {
            profileViewModel.uploadImageProfile(imageUri)
        }

        // back to users page
        profileViewModel.backToUserPage(view , binding.ivBtnBack)

        // select image
        binding.ivUserImage.setOnClickListener {
            // Check for permission
            if(ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
            }else{

                var intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent , Constants.REQUEST_CODE_IMAGE)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Constants.REQUEST_CODE_IMAGE && resultCode == AppCompatActivity.RESULT_OK){
            imageUri = data?.data!!
            try{
                binding.ivUserImage.setImageURI(imageUri)
                binding.btnUploadedImage.visibility = View.VISIBLE
            }catch(e:IOException){
                e.printStackTrace()
            }

        }
    }
}