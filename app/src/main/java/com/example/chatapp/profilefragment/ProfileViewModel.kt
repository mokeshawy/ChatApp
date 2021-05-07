package com.example.chatapp.profilefragment

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.chatapp.R
import com.example.chatapp.model.UserModel
import com.example.chatapp.util.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

class ProfileViewModel : ViewModel(){


    private val firebaseDatabase    = FirebaseDatabase.getInstance()
    private val userReference       = firebaseDatabase.getReference(Constants.USER_REFERENCE)
    private val firebaseStorage     = FirebaseStorage.getInstance().reference

    // fun get data for user log in
    fun getDetailsForUserLogIn(context: Context ,
                               tv_user_name : TextView ,
                               iv_user_image : ImageView ,
                               iv_user_image_profile : ImageView ,
                               btn_uploaded_image : Button){

        userReference.orderByChild("userId").equalTo(Constants.getCurrentUser()).addValueEventListener(object :  ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (ds in snapshot.children){

                    btn_uploaded_image.visibility = View.GONE
                    var user = ds.getValue(UserModel::class.java)!!
                    tv_user_name.text = user.userName

                    // when user upload photo for profile will show in image profile when cannot upload image will show default avatar.
                    if(user.profileImage == ""){
                        iv_user_image.setImageResource(R.drawable.ic_background_avatar)
                        iv_user_image_profile.setImageResource(R.drawable.ic_background_avatar)
                    }else{
                        Picasso.get().load(user.profileImage).into(iv_user_image)
                        Picasso.get().load(user.profileImage).into(iv_user_image_profile)

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context , error.message , Toast.LENGTH_SHORT).show()
            }

        })
    }

    // fun upload image profile for user
    fun uploadImageProfile( imageUri : Uri){

        var storage : StorageReference = firebaseStorage.child("Photo/"+System.currentTimeMillis())
        storage.putFile(imageUri).addOnCompleteListener{ uploadImage ->
            if(uploadImage.isSuccessful){

                storage.downloadUrl.addOnSuccessListener { downloadUri ->

                    var map = HashMap<String,Any>()
                    map[Constants.CHILD_PROFILE_IMAGE] = downloadUri.toString()
                    userReference.child(Constants.getCurrentUser()).updateChildren(map)
                }
            }
        }
    }

    fun backToUserPage(view : View, iv_btn_back : ImageView){
        iv_btn_back.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_usersFragment)
        }
    }
}