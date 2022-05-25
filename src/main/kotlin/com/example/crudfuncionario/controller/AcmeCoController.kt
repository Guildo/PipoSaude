package com.example.crudfuncionario.controller

import com.example.crudfuncionario.modelos.planossaude.DentalSorriso
import com.example.crudfuncionario.modelos.planossaude.NorteEuropa
import com.example.crudfuncionario.repository.DentalSorrisoRepository
import com.example.crudfuncionario.repository.FuncionarioRepository
import com.example.crudfuncionario.repository.NorteEuropaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("acmeCo")
class AcmeCoController (val funcionarioRepository: FuncionarioRepository,
                        val norteEuropaRepository: NorteEuropaRepository,
                        val dentalSorrisoRepository: DentalSorrisoRepository) {

    //Norte Europa
    @PostMapping("/norteEuropa/{cpf}")
    fun addBenefitsNorteEuropa(@PathVariable cpf: String): ResponseEntity<NorteEuropa> {
        val funcionarioDB = funcionarioRepository.findByCpf(cpf).orElseThrow { RuntimeException("Funcionário com cpf $cpf não encontrado!") }

        val norteEuropa = NorteEuropa(
            nome = funcionarioDB.nome,
            cpf = funcionarioDB.cpf,
            dataAdimissao = funcionarioDB.dataAdimissao,
            email = funcionarioDB.email,
            idFuncionario = funcionarioDB.id!!
        )

        return ResponseEntity.ok(norteEuropaRepository.save(norteEuropa))
    }

    @GetMapping("/norteEuropa")
    fun findAllNorteEuropa() = ResponseEntity.ok(norteEuropaRepository.findAll())


    //Dental Sorriso
    @PostMapping("/dentalSorriso/{cpf}/{peso}/{altura}")
    fun addBenefitsDentalSorriso(@PathVariable cpf: String, @PathVariable peso: Double, @PathVariable altura: Double): ResponseEntity<DentalSorriso> {
        val funcionarioDB = funcionarioRepository.findByCpf(cpf).orElseThrow { RuntimeException("Funcionário com cpf $cpf não encontrado!") }

        val dentalSorriso = DentalSorriso(
            nome = funcionarioDB.nome,
            cpf = funcionarioDB.cpf,
            alturaCm = altura,
            pesoKg = peso,
            idFuncionario = funcionarioDB.id!!
        )

        return ResponseEntity.ok(dentalSorrisoRepository.save(dentalSorriso))
    }

    @GetMapping("/dentalSorisso")
    fun findAllDentalSorriso() = ResponseEntity.ok(dentalSorrisoRepository.findAll())
}