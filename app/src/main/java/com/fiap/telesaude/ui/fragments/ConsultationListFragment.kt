package com.fiap.telesaude.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fiap.telesaude.databinding.FragmentConsultationListBinding

class ConsultationListFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentConsultationListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentConsultationListBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }
}