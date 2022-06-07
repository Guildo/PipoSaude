package com.example.employeebenefits.modelos.planossaude

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
