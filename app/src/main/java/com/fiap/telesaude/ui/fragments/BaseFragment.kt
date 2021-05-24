package com.fiap.telesaude.com.fiap.telesaude.ui.fragments

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.fiap.telesaude.R
import com.fiap.telesaude.databinding.DialogLoadingBinding

abstract class BaseFragment : Fragment() {

    private lateinit var dialogLoading: AlertDialog

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val dialogBuilder = AlertDialog.Builder(requireContext())
        val dialogBinding = DataBindingUtil.inflate<DialogLoadingBinding>(LayoutInflater.from(requireContext()), R.layout.dialog_loading, null, false)
        dialogLoading = dialogBuilder.setView(dialogBinding.root)
            .setCancelable(false)
            .create()
    }

    fun startLoading() {
        dialogLoading.show()
    }

    fun stopLoading() {
        dialogLoading.hide()
    }

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

    fun hideKeyboard() {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view?.rootView?.windowToken, 0)
    }
}