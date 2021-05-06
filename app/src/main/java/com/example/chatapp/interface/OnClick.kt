package com.example.chatapp.`interface`


import com.example.chatapp.adapter.RecyclerUserAdapter
import com.example.chatapp.model.UserModel

interface OnClick {

    fun onClick( viewHolder : RecyclerUserAdapter.ViewHolder , dataSet : UserModel , position : Int)
}