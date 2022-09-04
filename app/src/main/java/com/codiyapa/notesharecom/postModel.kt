package com.codiyapa.notesharecom

import com.google.firebase.firestore.auth.User

data class Post(val title: String = "",
                val createdBy: String = "",
                val time: Long = 0L,
                val fileUrl: String = "",
                val topic : String = ""
)