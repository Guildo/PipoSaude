package com.example.employeebenefits.controller

import com.example.employeebenefits.modelos.Funcionario
import com.example.employeebenefits.repository.EnderecoRepository
import com.example.employeebenefits.repository.FuncionarioRepository
import com.example.employeebenefits.utils.CPFUtil
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("funcionarios")
class FuncionarioController (val repository: FuncionarioRepository, val enderecoRepository: EnderecoRepository) {

    @PostMapping
    fun createFuncionario(@RequestBody funcionario: Funcionario): ResponseEntity<Funcionario> {
        if (!CPFUtil.validateCPF(funcionario.cpf)) {
            throw RuntimeException("CPF inválido!")
        }

        return ResponseEntity.ok(repository.save(funcionario))
    }

    @PutMapping
    fun updateFuncionario(@RequestParam id: String, @RequestBody funcionario: Funcionario): ResponseEntity<Funcionario> {
        val funcionarioDB = repository.findById(id)
        val toSave = funcionarioDB
            .orElseThrow { RuntimeException("Funcionário com id $id não encontrado!")}
            .copy(nome = funcionario.nome, cpf = funcionario.cpf, email = funcionario.email, dataAdimissao = funcionario.dataAdimissao)
        return ResponseEntity.ok(repository.save(toSave))
    }

    @GetMapping("/byId")
    fun findFuncionarioById(@RequestParam id: String) = ResponseEntity.ok(repository.findById(id).orElseThrow { RuntimeException("Funcionário com id $id não encontrado!") })

    @GetMapping
    fun findAllFuncionarios() = ResponseEntity.ok(repository.findAll().first())

    @DeleteMapping
    fun deleteFuncionario(@RequestParam id: String): ResponseEntity<String> {
        enderecoRepository.findByIdFuncionario(id).ifPresent { enderecoRepository.delete(it) } // Deleta o cadastro do endereço associado ao funcionário

        repository.findById(id).ifPresent { repository.delete(it) } // Deleta o cadastro do funcionário

        return ResponseEntity.ok("Dados do funcionário com o id $id deletados com sucesso!")
    }
}