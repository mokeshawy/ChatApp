package com.example.chatapp.chatfragment

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.model.ChatModel
import com.example.chatapp.util.Constants
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatViewModel : ViewModel(){

    val etSendMessage = MutableLiveData<String>("")

    var mGetMessageLiveList = MutableLiveData<ArrayList<ChatModel>>()
    var mGetMessageArrayList = ArrayList<ChatModel>()

    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val chatReference = firebaseDatabase.getReference(Constants.CHAT_REFERENCE)

    // fun send message
    fun sendMessage( receiveUserId : String , view : View , et_message : EditText , userName : String ){

        if(etSendMessage.value!!.isEmpty()){
            Snackbar.make( view , "Message is Empty" , Snackbar.LENGTH_SHORT).show()
            et_message.setText("")
        }else{
            var map = HashMap<String , Any>()

            map[Constants.SENDER_ID]    = Constants.getCurrentUser()
            map[Constants.RECEIVER_ID]  = receiveUserId
            map[Constants.MESSAGE]      = etSendMessage.value!!.toString()

            chatReference.push().setValue(map)
            et_message.setText("")

        }
    }

    // fun read message for firebase data base
    fun readMessage( context : Context , receiverId : String){

        mGetMessageArrayList = ArrayList()

        chatReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                mGetMessageArrayList.clear()
                for( ds in snapshot.children){

                    var chat = ds.getValue(ChatModel::class.java)!!
                    if( chat.senderId == Constants.getCurrentUser() && chat.receiverId == receiverId ||
                        chat.senderId == receiverId && chat.receiverId == Constants.getCurrentUser()){

                        mGetMessageArrayList.add(chat)
                    }
                    mGetMessageLiveList.value = mGetMessageArrayList
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context , error.message , Toast.LENGTH_SHORT).show()
            }
        })
    }
}