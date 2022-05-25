package com.example.crudfuncionario.modelos.planossaude

import com.example.crudfuncionario.modelos.Endereco
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
