package com.example.crudfuncionario.controller

import com.example.crudfuncionario.modelos.Funcionario
import com.example.crudfuncionario.repository.EnderecoRepository
import com.example.crudfuncionario.repository.FuncionarioRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("funcionarios")
class FuncionarioController (val repository: FuncionarioRepository, val enderecoRepository: EnderecoRepository) {

    @PostMapping
    fun createFuncionario(@RequestBody funcionario: Funcionario) = ResponseEntity.ok(repository.save(funcionario))

    @PutMapping("{id}")
    fun updateFuncionario(@PathVariable id: String, @RequestBody funcionario: Funcionario): ResponseEntity<Funcionario> {
        val funcionarioDB = repository.findById(id)
        val toSave = funcionarioDB
            .orElseThrow { RuntimeException("Funcionário com id $id não encontrado!")}
            .copy(nome = funcionario.nome, cpf = funcionario.cpf, email = funcionario.email, dataAdimissao = funcionario.dataAdimissao)
        return ResponseEntity.ok(repository.save(toSave))
    }

    @GetMapping("{id}")
    fun findFuncionarioById(@PathVariable id: String) = ResponseEntity.ok(repository.findById(id).orElseThrow { RuntimeException("Funcionário com id $id não encontrado!") })

    @GetMapping
    fun findAllFuncionarios() = ResponseEntity.ok(repository.findAll())

    @DeleteMapping("{id}")
    fun deleteFuncionario(@PathVariable id: String): ResponseEntity<String> {
        enderecoRepository.findByIdFuncionario(id).ifPresent { enderecoRepository.delete(it) } // Deleta o cadastro do endereço associado ao funcionário

        repository.findById(id).ifPresent { repository.delete(it) } // Deleta o cadastro do funcionário

        return ResponseEntity.ok("Dados do funcionário com o id $id deletados com sucesso!")
    }
}