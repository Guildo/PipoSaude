package com.example.crudfuncionario.repository

import com.example.crudfuncionario.modelos.Funcionario
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface FuncionarioRepository : MongoRepository<Funcionario, String> {

    fun findByCpf(cpf: String): Optional<Funcionario>
}