package com.fiap.telesaude.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fiap.telesaude.R
import com.fiap.telesaude.com.fiap.telesaude.ui.fragments.BaseFragment
import com.fiap.telesaude.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : BaseFragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var viewDataBinding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        auth = Firebase.auth
        viewDataBinding = FragmentLoginBinding.inflate(inflater, container, false)
        setupListeners()
        return viewDataBinding.root
    }

    private fun setupListeners() {
        viewDataBinding.apply {
            btnLoginId.setOnClickListener {
                hideKeyboard()
                startLoading()
                loginUser(loginEmailId.text.toString(), loginSenhaId.text.toString())
            }
        }
    }

    private fun loginUser(email: String, senha: String) = auth.signInWithEmailAndPassword(email, senha)
        .addOnSuccessListener(requireActivity()) {
            stopLoading()
            navigate(R.id.action_loginFragment_to_homeFragment2)
        }
        .addOnFailureListener { exception ->
            Log.e("TELESAUDE", "Login exception: ${exception.message}")
            Toast.makeText(requireContext(), "Erro ao efetuar login.", Toast.LENGTH_SHORT).show()
        }
}