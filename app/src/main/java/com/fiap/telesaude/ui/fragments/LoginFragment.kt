package com.fiap.telesaude.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fiap.telesaude.R
import com.fiap.telesaude.com.fiap.telesaude.ui.fragments.BaseFragment
import com.fiap.telesaude.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment() {

    private lateinit var viewDataBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentLoginBinding.inflate(inflater, container, false)
        setupListener()
        return viewDataBinding.root
    }

    private fun setupListener() {
        viewDataBinding.apply {
            btnLoginId.setOnClickListener {
                if (LoginUser(loginEmailId.toString(), loginSenhaId.toString())) {
                    navigate(R.id.action_loginFragment_to_homeFragment2)
                }
            }
        }
    }

    private fun LoginUser(email: String, senha: String): Boolean {
        return true
    }
}