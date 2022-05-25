package com.example.crudfuncionario.modelos.planossaude

import java.math.BigInteger

data class DentalSorriso (
    val id: String? = null,
    val nome: String,
    val cpf: String,
    val pesoKg: Double,
    val alturaCm: Double,
    val idFuncionario: String
) {

}
