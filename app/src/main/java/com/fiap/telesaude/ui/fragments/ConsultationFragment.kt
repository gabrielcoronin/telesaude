package com.fiap.telesaude.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.fiap.telesaude.com.fiap.telesaude.ui.fragments.BaseFragment
import com.fiap.telesaude.databinding.FragmentConsultationBinding

class ConsultationFragment : BaseFragment() {

    private val args by navArgs<ConsultationFragmentArgs>()
    private lateinit var viewDataBinding: FragmentConsultationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentConsultationBinding.inflate(inflater, container, false)
        setupListeners()
        return viewDataBinding.root
    }

    private fun setupListeners() {
        viewDataBinding.apply {
            consultNameId.text = args.consultName
            consultDescriptionId.text = args.consultDescription
            btnAgendarId.setOnClickListener {
                navigate(ConsultationFragmentDirections.actionConsultationFragmentToSchedulingFragment(args.consultName))
            }
        }
    }
}