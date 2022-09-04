package com.codiyapa.notesharecom

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.post_item.view.*


class PostAdapter(options: FirestoreRecyclerOptions<Post>, private var mAuth: FirebaseAuth, val listener: HomeFragment) : FirestoreRecyclerAdapter<Post, PostAdapter.PostViewHolder>(
    options
) {
    companion object {
        private const val DOUBLE_CLICK_TIME_DELTA: Long = 300 //milliseconds
        private var click : Int = 0
        private var lastClickTime: Long = 0
    }
    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userImage: ImageView = view.profile_image
        val userName: TextView = view.person_name
        val createdAt: TextView = view.date_time
        val postText: TextView = view.title
        val topic : TextView = view.topic_name
        val postImage : TextView = view.textView4
        val cardView : CardView = view.download_notes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int, model: Post) {
        holder.postText.text = model.title
        Firebase.firestore.collection(model.createdBy.toString()).document("user").get().addOnSuccessListener {
            holder.userName.text = it.getString("name")
        }
        holder.createdAt.text = Utils.getTimeAgo(model.time)
        holder.topic.text = model.topic
        holder.cardView.setOnClickListener {
            HomeFragment().openBrowser(model.fileUrl.toString())
        }
    }
}