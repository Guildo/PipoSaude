package com.example.crudfuncionario.modelos

import java.math.BigInteger

data class Endereco (
    var id: String? = null,
    var rua: String,
    var numero: String,
    var cidade: String,
    var estado: String,
    var idFuncionario: String? = null
) {
}