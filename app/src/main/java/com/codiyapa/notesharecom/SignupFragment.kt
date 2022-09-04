package com.codiyapa.notesharecom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_signup.*


class SignupFragment : Fragment() {

    private lateinit var view1 : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view1 = inflater.inflate(R.layout.fragment_signup, container, false)
        return view1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        signin.setOnClickListener {
            Navigation.findNavController(view1).navigate(R.id.action_signupFragment_to_signinFragment2)
        }

        signup.setOnClickListener {
            Navigation.findNavController(view1).navigate(R.id.action_signupFragment_to_signUp1Fragment)
        }
    }

}