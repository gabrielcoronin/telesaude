package com.fiap.telesaude.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fiap.telesaude.com.fiap.telesaude.ui.fragments.BaseFragment
import com.fiap.telesaude.databinding.FragmentSchedulingBinding

class SchedulingFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentSchedulingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentSchedulingBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    private fun setupListener() {
        viewDataBinding.apply {
            btnAgendarId.setOnClickListener{}
                

        }
    }
}