package com.fiap.telesaude.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import com.fiap.telesaude.com.fiap.telesaude.ui.adapters.ConsultListAdapter
import com.fiap.telesaude.com.fiap.telesaude.ui.fragments.BaseFragment
import com.fiap.telesaude.databinding.FragmentConsultationListBinding

class ConsultationListFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentConsultationListBinding
    private lateinit var listAdapter: ConsultListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentConsultationListBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    private fun setupListener() {
        listAdapter = ConsultListAdapter(this)
        viewDataBinding.apply {
            consultList.adapter = listAdapter
        }
    }
}