package com.example.crudfuncionario.modelos

import java.math.BigInteger
import java.util.Date

data class Funcionario (
    var id: String? = null,
    val nome: String,
    val cpf: String,
    val email: String,
    val dataAdimissao: Date,
) {

}