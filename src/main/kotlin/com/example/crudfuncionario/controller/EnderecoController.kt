package com.example.crudfuncionario.controller

import com.example.crudfuncionario.modelos.Endereco
import com.example.crudfuncionario.repository.EnderecoRepository
import com.example.crudfuncionario.repository.FuncionarioRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("funcionarios")
class EnderecoController (val enderecoRepository: EnderecoRepository, val funcionarioRepository: FuncionarioRepository) {

    @PostMapping("/{idFuncionario}/endereco")
    fun createEndereco(@PathVariable idFuncionario: String, @RequestBody endereco: Endereco): ResponseEntity<Endereco> {
        val funcionarioDB = funcionarioRepository.findById(idFuncionario).orElseThrow { RuntimeException("Funcionário com id $idFuncionario não encontrado!") }

        endereco.idFuncionario = funcionarioDB.id

        return ResponseEntity.ok(enderecoRepository.save(endereco))
    }

    @PutMapping("{idFuncionario}/endereco")
    fun updateEndereco(@PathVariable idFuncionario: String, @RequestBody endereco: Endereco): ResponseEntity<Endereco> {
        val enderecoDB = enderecoRepository.findByIdFuncionario(idFuncionario)

        val toSave = enderecoDB
            .orElseThrow { RuntimeException("Funcionário com id $idFuncionario não encontrado!")}
            .copy()

        return ResponseEntity.ok(enderecoRepository.save(toSave))
    }

    @GetMapping("{idFuncionario}/endereco")
    fun findEnderecoByIdFuncionario(@PathVariable idFuncionario: String) = ResponseEntity.ok(enderecoRepository.findByIdFuncionario(idFuncionario))

    @GetMapping("/enderecos")
    fun findAllEnderecos() = ResponseEntity.ok(enderecoRepository.findAll())

    @DeleteMapping("{idFuncionario}/endereco")
    fun deleteEndereco(@PathVariable idFuncionario: String) = enderecoRepository.findByIdFuncionario(idFuncionario).ifPresent { enderecoRepository.delete(it) }

}