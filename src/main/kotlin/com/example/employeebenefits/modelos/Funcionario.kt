package com.example.employeebenefits.modelos

import java.util.Date

data class Funcionario (
    var id: String? = null,
    val nome: String,
    var cpf: String,
    val email: String,
    val dataAdimissao: Date,
) {

}