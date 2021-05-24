package com.fiap.telesaude.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.telesaude.R
import com.fiap.telesaude.com.fiap.telesaude.ui.fragments.BaseFragment
import com.fiap.telesaude.com.fiap.telesaude.ui.viewmodels.ConsultModel
import com.fiap.telesaude.databinding.FragmentMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainFragment : BaseFragment() {

    private val db = Firebase.firestore
    private lateinit var viewDataBinding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startLoading()

        val consultList = mutableListOf<ConsultModel>()
        consultList.add(ConsultModel(1, "Acupuntura", 120, "Acupuntura é um método de estimulação de pontos no corpo, feito para aliviar sintomas e tratar doenças do corpo e da mentes.\n\nSintomas aliviados: dores de cabeça, dor na coluna, dor nos membros, insônia, etc."))
        consultList.add(ConsultModel(2, "Fisioterapia", 90, "Teste descrição."))

        consultList.forEach { consult ->
            db.collection("services")
                .whereEqualTo("consultName", consult.consultName)
                .get()
                .addOnSuccessListener { result ->
                    if (result.isEmpty) {
                        db.collection("services")
                            .add(consult.asHashMap())
                    } else {
                        result.forEach { doc ->
                            doc.reference
                                .update(consult.asHashMap())
                        }
                    }
                }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentMainBinding.inflate(inflater, container, false)
        setupListeners()
        return viewDataBinding.root
    }

    private fun setupListeners() {
        viewDataBinding.apply {
            btnRegisterId.setOnClickListener {
                navigate(R.id.action_mainFragment_to_registerFragment)
            }
            btnLoginId.setOnClickListener {
                navigate(R.id.action_mainFragment_to_loginFragment)
            }
        }

        stopLoading()
    }
}