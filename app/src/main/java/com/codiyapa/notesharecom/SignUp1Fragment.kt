package com.codiyapa.notesharecom

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_sign_up1.*
import kotlinx.android.synthetic.main.fragment_signin.*


class SignUp1Fragment : Fragment() {

    private lateinit var view1: View
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view1 =  inflater.inflate(R.layout.fragment_sign_up1, container, false)
        return view1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        imageView2.setOnClickListener {
        val email = signupmail.text.toString()
        val password = signuppassword.text.toString()
        val cnfmpassword = signuppassword1.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
           signupmail.error = "Email isn't correct"
        }
        if(password != cnfmpassword){
            signuppassword.error = "Password and confirm password are not same"
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user != null){
            val user = hashMapOf(
                "name" to firstname.text.toString(),
                "email" to auth.currentUser!!.email.toString()
            )
            val db = Firebase.firestore
            db.collection(auth.currentUser!!.email.toString())
                .document("user").set(user)
                .addOnSuccessListener {
                    Log.d("Tag", "done")
                }
            Navigation.findNavController(view1).navigate(R.id.action_signUp1Fragment_to_homeFragment)
        }else{
            //user is not created
            Snackbar.make(view1, "Something went wrong", Snackbar.LENGTH_SHORT).show()
        }
    }
}