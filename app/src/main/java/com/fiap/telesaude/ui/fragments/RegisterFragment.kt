package com.fiap.telesaude.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fiap.telesaude.R
import com.fiap.telesaude.com.fiap.telesaude.ui.fragments.BaseFragment
import com.fiap.telesaude.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterFragment : BaseFragment() {

    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    private lateinit var viewDataBinding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        auth = Firebase.auth
        viewDataBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        setupListeners()
        return viewDataBinding.root
    }

    private fun setupListeners() {
        viewDataBinding.apply {
            btnRegisterId.setOnClickListener {
                hideKeyboard()
                startLoading()
                registerUser(registerCpfId.text.toString(), registerEmailId.text.toString(), registerSenhaId.text.toString())
            }
        }
    }

    private fun registerUser(cpf: String, email: String, senha: String) = auth.createUserWithEmailAndPassword(email, senha)
        .addOnSuccessListener(requireActivity()) {
            db.collection("users")
                .add(
                    hashMapOf(
                        "userEmail" to email,
                        "userCpf" to cpf
                    )
                )
                .addOnSuccessListener {
                    stopLoading()
                    navigate(R.id.action_registerFragment_to_homeFragment2)
                }
                .addOnFailureListener { exception ->
                    Log.e("TELESAUDE", "Register exception: ${exception.message}")
                    Toast.makeText(requireContext(), "Erro ao cadastrar usuário.", Toast.LENGTH_SHORT).show()
                }
        }
        .addOnFailureListener { exception ->
            Log.e("TELESAUDE", "Register exception: ${exception.message}")
            Toast.makeText(requireContext(), "Erro ao cadastrar usuário.", Toast.LENGTH_SHORT).show()
        }
}