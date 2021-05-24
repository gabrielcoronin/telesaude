package com.fiap.telesaude.com.fiap.telesaude.ui.viewmodels

data class ConsultModel(
    var consultId: Int = 0,
    var consultName: String? = null,
    var consultPrice: Int = 0,
    var consultDescription: String? = null
) {

    fun asHashMap() = hashMapOf(
        "consultId" to consultId,
        "consultName" to consultName,
        "consultDescription" to consultDescription,
        "consultPrice" to consultPrice
    )
}