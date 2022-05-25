package com.example.crudfuncionario.modelos.planossaude

import java.math.BigInteger
import java.util.Date

data class NorteEuropa (
    val id: String? = null,
    val nome: String,
    val cpf: String,
    val dataAdimissao: Date,
    val email: String,
    val idFuncionario: String
) {
}
