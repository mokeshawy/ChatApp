package com.example.chatapp.util

import com.google.firebase.auth.FirebaseAuth

object Constants {

    // Users Reference
    const val USER_REFERENCE        = "Users"

    // Users child
    const val CHILD_USER_NAME       = "userName"
    const val CHILD_USER_ID         = "userId"
    const val CHILD_PROFILE_IMAGE   = "profileImage"

    // Request code for select image profile
    const val REQUEST_CODE_IMAGE = 1



    // fun return user id
    fun getCurrentUser() : String{

        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUserId = ""
        if( currentUserId !=null){
            currentUserId = currentUser.uid
        }
        return currentUserId
    }

}