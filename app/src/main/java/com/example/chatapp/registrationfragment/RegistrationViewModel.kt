package com.example.chatapp.registrationfragment

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.chatapp.R
import com.example.chatapp.util.Constants
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegistrationViewModel : ViewModel() {

    val etEnterName             = MutableLiveData<String>("")
    val etEnterEmail            = MutableLiveData<String>("")
    val etEnterPassword         = MutableLiveData<String>("")
    val etEnterConfirmPassword  = MutableLiveData<String>("")

    private val firebaseAuth        = FirebaseAuth.getInstance()
    private val firebaseDatabase    = FirebaseDatabase.getInstance()
    private val accountReference    = firebaseDatabase.getReference(Constants.USER_REFERENCE)


    // fun create new account.
    fun registerNewAccount( context : Context , view : View){

        // validate input for entry data.
        if(etEnterName.value!!.trim().isEmpty()){
            Snackbar.make(view , context.getString(R.string.err_msg_please_enter_your_name),Snackbar.LENGTH_SHORT).show()
        }else if(etEnterEmail.value!!.trim().isEmpty()){
            Snackbar.make(view , context.getString(R.string.err_msg_please_enter_your_email),Snackbar.LENGTH_SHORT).show()
        }else if(etEnterPassword.value!!.trim().isEmpty()){
            Snackbar.make(view , context.getString(R.string.err_msg_please_enter_your_password),Snackbar.LENGTH_SHORT).show()
        }else if(etEnterPassword.value!!.length > 6){
            Snackbar.make(view , context.getString(R.string.err_msg_the_password_is_not_less_than),Snackbar.LENGTH_SHORT).show()
        }else if(etEnterConfirmPassword.value!!.trim().isEmpty()){
            Snackbar.make(view , context.getString(R.string.err_msg_please_enter_confirm_password),Snackbar.LENGTH_SHORT).show()
        }else if(etEnterPassword.value!! != etEnterConfirmPassword.value!!){
            Snackbar.make(view , context.getString(R.string.err_msg_password_and_confirm_password_not_mismatch), Snackbar.LENGTH_SHORT).show()
        }else{
            // create new account with firebase authentication.
            firebaseAuth.createUserWithEmailAndPassword(etEnterEmail.value!!.toString() , etEnterPassword.value!!.toString()).addOnCompleteListener { it ->
                if(it.isSuccessful){
                    firebaseAuth.currentUser?.sendEmailVerification()
                   var userId = firebaseAuth.currentUser?.uid

                    // save data in real time data base for user sign up bu hashMap.
                    var map = HashMap<String , String>()

                    map[Constants.CHILD_USER_ID]        = userId.toString()
                    map[Constants.CHILD_USER_NAME]      = etEnterName.value!!.toString()
                    map[Constants.CHILD_PROFILE_IMAGE]  = ""

                    accountReference.push().setValue(map).addOnCompleteListener { setValue ->
                        if(setValue.isSuccessful){
                            Snackbar.make(view , context.getString(R.string.msg_create_account_successful),Snackbar.LENGTH_SHORT).show()
                            Navigation.findNavController(view).navigate(R.id.action_regisrtationFragment_to_homeFragment)
                        }else{
                            Snackbar.make(view , it.exception!!.message.toString(), Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Snackbar.make(view , it.exception!!.message.toString(), Snackbar.LENGTH_SHORT).show()
                }
            }
        }

    }
}