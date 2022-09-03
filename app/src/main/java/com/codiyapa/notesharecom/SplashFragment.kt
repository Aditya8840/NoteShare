package com.codiyapa.notesharecom

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class SplashFragment : Fragment() {

    private lateinit var view1: View
    private lateinit var mauth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view1 =  inflater.inflate(R.layout.fragment_splash, container, false)
        return view1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mauth = FirebaseAuth.getInstance()
        Handler().postDelayed({
            if(mauth.currentUser != null){

            }
        }, 2500)
    }

}