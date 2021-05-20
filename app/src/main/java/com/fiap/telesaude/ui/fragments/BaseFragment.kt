package com.fiap.telesaude.com.fiap.telesaude.ui.fragments

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController

abstract class BaseFragment : Fragment() {

    fun navigate(@IdRes resId: Int) = with(findNavController()) {
        currentDestination?.getAction(resId)?.let {
            navigate(resId)
        }
    }

    fun navigate(destination: NavDirections) = with(findNavController()) {
        currentDestination?.getAction(destination.actionId)?.let {
            navigate(destination)
        }
    }
}