package com.example.crudfuncionario.controller

import com.example.crudfuncionario.modelos.planossaude.DentalSorriso
import com.example.crudfuncionario.modelos.planossaude.MenteSaCorpoSao
import com.example.crudfuncionario.modelos.planossaude.PampulhaIntermedica
import com.example.crudfuncionario.repository.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("patinhasBank")
class PatinhasBankController (val funcionarioRepository: FuncionarioRepository,
                              val enderecoRepository: EnderecoRepository,
                              val pampulhaIntermedicaRepository: PampulhaIntermedicaRepository,
                              val dentalSorrisoRepository: DentalSorrisoRepository,
                              val menteSaCorpoSaoRepository: MenteSaCorpoSaoRepository) {

    //Pampulha Intermédica
    @PostMapping("/pampulha/{cpf}")
    fun addBenefitsPampulha(@PathVariable cpf: String): ResponseEntity<PampulhaIntermedica> {
        val funcionarioDB = funcionarioRepository.findByCpf(cpf).orElseThrow { RuntimeException("Funcionário com cpf $cpf não encontrado!") }

        val enderecoDB = enderecoRepository.findByIdFuncionario(funcionarioDB.id!!)

        val pampulhaIntermedica = PampulhaIntermedica(
            nome = funcionarioDB.nome,
            idFuncionario = funcionarioDB.id!!,
            cpf = funcionarioDB.cpf,
            dataAdimissao = funcionarioDB.dataAdimissao,
            endereco = enderecoDB.get()
        )

        return ResponseEntity.ok(pampulhaIntermedicaRepository.save(pampulhaIntermedica))
    }

    @GetMapping("/pampulha")
    fun findAllPampulhaIntermedica() = ResponseEntity.ok(pampulhaIntermedicaRepository.findAll())

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

    @GetMapping("/dentalSorriso")
    fun findAllDentalSorriso() = ResponseEntity.ok(dentalSorrisoRepository.findAll())

    //Mente Sã, Corpo São
    @PostMapping("/menteSaCorpoSao/{cpf}/{horasMeditadas}")
    fun addBenefitsMenteSaCorpoSao(@PathVariable cpf: String, @PathVariable horasMeditadas: Double): ResponseEntity<MenteSaCorpoSao> {
        val funcionarioDB = funcionarioRepository.findByCpf(cpf).orElseThrow { RuntimeException("Funcionário com cpf '$cpf' não encontrado!") }

        val menteSaCorpoSao = MenteSaCorpoSao(
            cpf = funcionarioDB.cpf,
            idFuncionario = funcionarioDB.id!!,
            horasMeditadas = horasMeditadas
        )

        return ResponseEntity.ok(menteSaCorpoSaoRepository.save(menteSaCorpoSao))
    }
}