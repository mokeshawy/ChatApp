package com.example.chatapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.ItemListUsersBinding
import com.example.chatapp.model.UserModel
import com.squareup.picasso.Picasso

class RecyclerUserAdapter(private val dataSet: ArrayList<UserModel> ) : RecyclerView.Adapter<RecyclerUserAdapter.ViewHolder>() {

    class ViewHolder(var binding : ItemListUsersBinding) : RecyclerView.ViewHolder(binding.root) {

    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        // Create a new view, which defines the UI of the list item
        return ViewHolder(ItemListUsersBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false))
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.binding.tvUserName.text = dataSet[position].userName
        Picasso.get().load(dataSet[position].profileImage).into(viewHolder.binding.ivUserImage)

    }
    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}