package com.codiyapa.notesharecom

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User

class postDao {
    private val db = FirebaseFirestore.getInstance()
    val posts = db.collection("content")
    private val mAuth = FirebaseAuth.getInstance()
    val userS = mAuth.currentUser
    fun createPost(text: String, docUrl: String, topic : String){
        val user = userS!!.email
        val time = System.currentTimeMillis()
        val post = user?.let { Post(text, it, time, docUrl, topic) }
        if (post != null) {
            posts.document().set(post)
        }
    }
}