package com.fiap.telesaude.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.telesaude.com.fiap.telesaude.ui.adapters.ConsultListAdapter
import com.fiap.telesaude.com.fiap.telesaude.ui.fragments.BaseFragment
import com.fiap.telesaude.com.fiap.telesaude.ui.viewmodels.ConsultModel
import com.fiap.telesaude.databinding.FragmentConsultationListBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ConsultationListFragment : BaseFragment() {

    private val db = Firebase.firestore
    private lateinit var viewDataBinding: FragmentConsultationListBinding
    private lateinit var listAdapter: ConsultListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        startLoading()
        viewDataBinding = FragmentConsultationListBinding.inflate(inflater, container, false)
        setupListeners()
        return viewDataBinding.root
    }

    private fun setupListeners() {
        listAdapter = ConsultListAdapter(this)
        viewDataBinding.apply {
            consultList.adapter = listAdapter
        }
        listServices()
    }

    private fun listServices() = db.collection("services")
        .get()
        .addOnSuccessListener { result ->
            listAdapter.submitList(result.toObjects(ConsultModel::class.java))
            stopLoading()
        }
}