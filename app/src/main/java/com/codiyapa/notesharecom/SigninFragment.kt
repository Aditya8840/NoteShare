package com.codiyapa.notesharecom

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_signin.*


class SigninFragment : Fragment() {

    private lateinit var view1: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view1 = inflater.inflate(R.layout.fragment_signin, container, false)
        return  view1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val auth = FirebaseAuth.getInstance()
        signinbutton.setOnClickListener {
            var edumail = signinmail.text.toString()
            var pass = signinpassword.text.toString()
            auth.signInWithEmailAndPassword(edumail, pass)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithEmail:failure", task.exception)
                        Toast.makeText(requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }

        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if(user != null){
            Navigation.findNavController(view1).navigate(R.id.action_signinFragment2_to_homeFragment)
        }else{
            //login failed
        }
    }
}