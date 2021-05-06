package com.example.chatapp.model

import com.example.chatapp.response.NotificationResponse

data class PushNotificationModel (

    var data : NotificationResponse,
    var to : String
)