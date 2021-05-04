package com.example.chatapp.usersfragment

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.chatapp.R
import com.example.chatapp.model.UserModel
import com.example.chatapp.util.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class UsersViewModel : ViewModel(){

    val mUserLiveArrayList  =  MutableLiveData<ArrayList<UserModel>>()
    val mUserArrayList      : ArrayList<UserModel> = ArrayList()



    private val firebaseDatabase    = FirebaseDatabase.getInstance()
    private val userReference       = firebaseDatabase.getReference(Constants.USER_REFERENCE)

    //fun get data for users for database
    fun getUser( context: Context , iv_user_image_profile : ImageView){

        userReference.addValueEventListener( object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                mUserArrayList.clear()
                // for loop for get all data for user form database
                for (ds in snapshot.children){

                    var user = ds.getValue(UserModel::class.java)!!
                    if( user.userId == Constants.getCurrentUser()){
                        // when user upload photo for profile will show in image profile when cannot upload image will show default avatar.
                        if(user.profileImage == ""){
                            iv_user_image_profile.setImageResource(R.drawable.ic_background_avatar)
                        }else{
                            Picasso.get().load(user.profileImage).into(iv_user_image_profile)
                        }
                    }
                    // check for show all data for users with out user login
                    if(user.userId!= Constants.getCurrentUser()){
                        mUserArrayList.add(user)
                    }
                }
                mUserLiveArrayList.value = mUserArrayList
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context , error.message , Toast.LENGTH_SHORT).show()
            }

        })
    }

    //fun go to profile page for user log in
    fun goToProfile(view : View , iv_go_user_profile : ImageView){
        iv_go_user_profile.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_usersFragment_to_profileFragment)
        }
    }


}