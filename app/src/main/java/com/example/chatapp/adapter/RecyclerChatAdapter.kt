package com.example.chatapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.model.ChatModel
import com.example.chatapp.util.Constants

class RecyclerChatAdapter (private val chatList : ArrayList<ChatModel>) : RecyclerView.Adapter<RecyclerChatAdapter.ViewHolder>() {

    private val MESSAGE_TYPE_LEFT   = 0
    private val MESSAGE_TYPE_RIGHT  = 1


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        lateinit var  textMessage           : TextView

        init {
            // Define click listener for the ViewHolder's View.
            textMessage       = view.findViewById(R.id.tv_message)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        if(viewType == MESSAGE_TYPE_RIGHT){
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_right_side, viewGroup, false)
            return ViewHolder(view)
        }else{
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_left_side , viewGroup, false)
            return ViewHolder(view)
        }

    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.textMessage.text = chatList[position].message

    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = chatList.size

    override fun getItemViewType(position: Int): Int {

        if( chatList[position].senderId == Constants.getCurrentUser()){

            return MESSAGE_TYPE_RIGHT
        }else{
            return MESSAGE_TYPE_LEFT
        }
    }
}