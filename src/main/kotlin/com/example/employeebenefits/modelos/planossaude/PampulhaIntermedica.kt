package com.example.employeebenefits.modelos.planossaude

import com.example.employeebenefits.modelos.Endereco
import java.util.*

data class PampulhaIntermedica (
    val id: String? = null,
    val nome: String,
    val cpf: String,
    val dataAdimissao: Date,
    val endereco: Endereco,
    val idFuncionario: String
) {

}
