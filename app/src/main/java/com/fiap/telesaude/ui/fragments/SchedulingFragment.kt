package com.fiap.telesaude.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.fiap.telesaude.R
import com.fiap.telesaude.com.fiap.telesaude.ui.fragments.BaseFragment
import com.fiap.telesaude.com.fiap.telesaude.ui.viewmodels.HistoryModel
import com.fiap.telesaude.databinding.FragmentSchedulingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SchedulingFragment : BaseFragment() {

    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore
    private val args by navArgs<SchedulingFragmentArgs>()
    private lateinit var viewDataBinding: FragmentSchedulingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        auth = Firebase.auth
        viewDataBinding = FragmentSchedulingBinding.inflate(inflater, container, false)
        setupListeners()
        return viewDataBinding.root
    }

    private fun setupListeners() {
        viewDataBinding.apply {
            db.collection("users")
                .whereEqualTo("userEmail", auth.currentUser?.email)
                .get()
                .addOnSuccessListener { result ->
                    cpfTextId.setText(result.first()["userCpf"].toString())
                    emailTextId.setText(auth.currentUser?.email)
                }

            btnAgendarId.setOnClickListener {
                hideKeyboard()
                scheduleService(cpfTextId.text.toString(), emailTextId.text.toString(), dataConsultaTextId.text.toString(), args.consultName)
            }
        }
    }

    private fun scheduleService(cpf: String, email: String, dataConsulta: String, consultName: String) = db.collection("history")
        .add(HistoryModel(cpf, email, dataConsulta, consultName).asHashMap())
        .addOnSuccessListener {
            navigate(R.id.action_schedulingFragment_to_homeFragment)
        }
        .addOnFailureListener { exception ->
            Log.e("TELESAUDE", "Scheduler exception: ${exception.message}")
            Toast.makeText(requireContext(), "Erro ao agendar consulta.", Toast.LENGTH_SHORT).show()
        }
}