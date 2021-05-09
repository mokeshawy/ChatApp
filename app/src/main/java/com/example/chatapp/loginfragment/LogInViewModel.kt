package com.example.chatapp.loginfragment

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.chatapp.R
import com.example.chatapp.util.Constants
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LogInViewModel : ViewModel(){

     val etEnterEmail    = MutableLiveData<String>("")
     val etEnterPassword = MutableLiveData<String>("")

    private val firebaseAuth = FirebaseAuth.getInstance()

    // function operation for log in
    fun logIn( context : Context , view : View){

        // check if user log in then navigate to user screen
//        if( Constants.getCurrentUser() !=null){
//            Navigation.findNavController(view).navigate(R.id.action_logInFragment_to_usersFragment)
//        }

        // Validate input for entry data from user log in
        if (etEnterEmail.value!!.trim().isEmpty()) {
            Snackbar.make(view, context.getString(R.string.err_msg_please_enter_your_email), Snackbar.LENGTH_SHORT).show()
        } else if (etEnterPassword.value!!.trim().isEmpty()) {
            Snackbar.make(view, context.getString(R.string.err_msg_please_enter_your_password), Snackbar.LENGTH_SHORT).show()
        } else {

            // make authentication work with email and password register
            firebaseAuth.signInWithEmailAndPassword(etEnterEmail.value!!, etEnterPassword.value!!).addOnCompleteListener {
                if (it.isSuccessful) {
                    if (firebaseAuth.currentUser?.isEmailVerified!!) {

                        Snackbar.make(view, context.getString(R.string.msg_welcome_user_login), Snackbar.LENGTH_SHORT).show()
                        Navigation.findNavController(view).navigate(R.id.action_logInFragment_to_usersFragment)
                    } else {
                        Snackbar.make(view, context.getText(R.string.err_msg_confirm_email), Snackbar.LENGTH_SHORT).show()
                    }
                } else {
                    Snackbar.make(view, it.exception!!.message.toString(), Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    //Function go create new account
    fun goRegisterPage( view: View , tv_go_sign_up : TextView){
        tv_go_sign_up.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_logInFragment_to_regisrtationFragment)
        }
    }
}