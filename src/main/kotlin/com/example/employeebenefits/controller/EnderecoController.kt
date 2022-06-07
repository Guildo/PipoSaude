package com.example.employeebenefits.controller

import com.example.employeebenefits.modelos.Endereco
import com.example.employeebenefits.repository.EnderecoRepository
import com.example.employeebenefits.repository.FuncionarioRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("funcionarios")
class EnderecoController (val enderecoRepository: EnderecoRepository, val funcionarioRepository: FuncionarioRepository) {

    @PostMapping("endereco")
    fun createEndereco(@RequestBody endereco: Endereco): ResponseEntity<Endereco> {
        funcionarioRepository.findById(endereco.idFuncionario).orElseThrow{ RuntimeException("Funcionário com id ${endereco.idFuncionario} não encontrado!") }
        return ResponseEntity.ok(enderecoRepository.save(endereco))
    }

    @PutMapping("endereco")
    fun updateEndereco(@RequestBody endereco: Endereco): ResponseEntity<Endereco> {
        val idFuncionario = endereco.idFuncionario

        val enderecoDB = enderecoRepository.findByIdFuncionario(idFuncionario)

        val toSave = enderecoDB
            .orElseThrow { RuntimeException("Funcionário com id $idFuncionario não encontrado!")}
            .copy()

        return ResponseEntity.ok(enderecoRepository.save(toSave))
    }

    @GetMapping("endereco/byId")
    fun findEnderecoByIdFuncionario(@RequestParam idFuncionario: String) = ResponseEntity.ok(enderecoRepository.findByIdFuncionario(idFuncionario))

    @GetMapping("endereco")
    fun findAllEnderecos() = ResponseEntity.ok(enderecoRepository.findAll())

    @DeleteMapping("endereco")
    fun deleteEndereco(@RequestParam idFuncionario: String) = enderecoRepository.findByIdFuncionario(idFuncionario).ifPresent { enderecoRepository.delete(it) }

}