package com.fiap.telesaude.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.telesaude.R
import com.fiap.telesaude.com.fiap.telesaude.ui.fragments.BaseFragment
import com.fiap.telesaude.com.fiap.telesaude.ui.viewmodels.HistoryModel
import com.fiap.telesaude.databinding.FragmentHomeBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : BaseFragment() {

    private val db = Firebase.firestore
    private lateinit var viewDataBinding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false)
        setupListeners()
        return viewDataBinding.root
    }

    private fun setupListeners() {
        viewDataBinding.apply {
            consultasHomeId.setOnClickListener {
                navigate(R.id.action_homeFragment_to_consultationListFragment)
            }
            examesHomeId.setOnClickListener {
                navigate(R.id.action_homeFragment_to_consultationListFragment)
            }
        }
        listHistory()
    }

    private fun listHistory() = db.collection("history")
        .get()
        .addOnSuccessListener { result ->
            if (!result.isEmpty) {
                var historyText = ""

                result.toObjects(HistoryModel::class.java).forEach { doc ->
                    historyText += "${doc.consultDate} - ${doc.consultName}\n"
                }

                viewDataBinding.historicoTextId.text = historyText
            }
        }
}