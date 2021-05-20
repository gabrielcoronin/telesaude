package com.fiap.telesaude.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.telesaude.R
import com.fiap.telesaude.com.fiap.telesaude.ui.fragments.BaseFragment
import com.fiap.telesaude.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        setupListener()
        return viewDataBinding.root
    }

    private fun setupListener() {
        viewDataBinding.apply {
            btnRegisterId.setOnClickListener {
                if (RegisterUser(
                        registerCpfId.toString(),
                        registerEmailId.toString(),
                        registerSenhaId.toString()
                    )
                ) {
                    navigate(R.id.action_registerFragment_to_homeFragment2)
                }
            }
        }
    }

    private fun RegisterUser(cpf: String, email: String, senha: String): Boolean {
        return true;
    }

}