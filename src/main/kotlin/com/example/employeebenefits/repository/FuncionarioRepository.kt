package com.example.employeebenefits.repository

import com.example.employeebenefits.modelos.Funcionario
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface FuncionarioRepository : MongoRepository<Funcionario, String> {

    fun findByCpf(cpf: String): Optional<Funcionario>
}