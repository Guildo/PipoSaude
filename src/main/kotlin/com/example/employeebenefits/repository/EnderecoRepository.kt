package com.example.employeebenefits.repository

import com.example.employeebenefits.modelos.Endereco
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface EnderecoRepository: MongoRepository<Endereco, String> {

    fun findByIdFuncionario(idFuncionario: String): Optional<Endereco>
}