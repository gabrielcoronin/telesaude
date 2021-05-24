package com.fiap.telesaude.com.fiap.telesaude.ui.viewmodels

data class HistoryModel(
    var userCpf: String? = null,
    var userEmail: String? = null,
    var consultDate: String? = null,
    var consultName: String? = null
) {

    fun asHashMap() = hashMapOf(
        "userCpf" to userCpf,
        "userEmail" to userEmail,
        "consultDate" to consultDate,
        "consultName" to consultName
    )
}