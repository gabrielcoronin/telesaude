package com.fiap.telesaude.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fiap.telesaude.R
import com.fiap.telesaude.com.fiap.telesaude.ui.fragments.BaseFragment
import com.fiap.telesaude.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false)
        setupListener()
        return viewDataBinding.root
    }

    private fun setupListener() {
        viewDataBinding.apply {
            consultasHomeId.setOnClickListener {
                navigate(R.id.action_homeFragment_to_consultationListFragment)
            }
            examesHomeId.setOnClickListener {
                navigate(R.id.action_homeFragment_to_consultationListFragment)
            }
        }
    }
}