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


    // Chat Reference
    const val CHAT_REFERENCE = "Chat"

    // Child chat reference
    const val SENDER_ID     = "senderId"
    const val RECEIVER_ID   = "receiverId"
    const val MESSAGE       = "message"

    // API Const
    const val BASE_URL      = "https://fcm.googleapis.com"
    const val SERVICE_KEY   = "AAAAtJfUxII:APA91bECyKMP3L6QppOWxaH2GrQRcCgtvqaTjyb3HDlufNx80T96CwrlvprhVA7W07Hr78MbRa5yQtpmlqbkU2vsJ4CHGdcDkD6nCThWUlwcK1vXON7nT4g_PJF1qvxWyO9FORBMTnwn"
    const val CONTENT_TYPE     = "application/json"


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