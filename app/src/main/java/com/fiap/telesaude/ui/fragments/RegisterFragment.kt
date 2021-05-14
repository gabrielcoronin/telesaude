package com.fiap.telesaude.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fiap.telesaude.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }
}