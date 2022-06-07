package com.example.employeebenefits.controller

import com.example.employeebenefits.modelos.planossaude.DentalSorriso
import com.example.employeebenefits.modelos.planossaude.MenteSaCorpoSao
import com.example.employeebenefits.modelos.planossaude.PampulhaIntermedica
import com.example.employeebenefits.repository.*
import com.example.employeebenefits.utils.CPFUtil
import org.jetbrains.annotations.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Service
@RestController
@RequestMapping("patinhasBank")
class PatinhasBankController (val funcionarioRepository: FuncionarioRepository,
                              val enderecoRepository: EnderecoRepository,
                              val pampulhaIntermedicaRepository: PampulhaIntermedicaRepository,
                              val dentalSorrisoRepository: DentalSorrisoRepository,
                              val menteSaCorpoSaoRepository: MenteSaCorpoSaoRepository) {

    //Benefícios Patinhas Bank
    @PostMapping
    fun addBenefits(@RequestParam @NotNull cpf: String,
                    @RequestParam @NotNull peso: Double,
                    @RequestParam @NotNull altura: Double,
                    @RequestParam @NotNull horasMeditadas: Double): ResponseEntity<String> {

        if (!CPFUtil.validateCPF(cpf)) {
            throw RuntimeException("CPF inválido!")
        }

        if (peso.isNaN() || peso <= 0) {
            throw RuntimeException("O PESO do funcionário deve ser informado por um número maior que zero!")
        }

        if (altura.isNaN() || altura <= 0) {
            throw RuntimeException("A ALTURA do funcionário deve ser informada por um número maior que zero!")
        }

        if (horasMeditadas.isNaN() || horasMeditadas <= 0) {
            throw RuntimeException("As HORAS MEDITADAS do funcionário devem ser informadas por um número maior que zero!")
        }

        val funcionarioDB = funcionarioRepository.findByCpf(cpf).orElseThrow { RuntimeException("Funcionário com cpf $cpf não encontrado!") }

        val enderecoDB = enderecoRepository.findByIdFuncionario(funcionarioDB.id!!)

        // Pampulha Intermedica
        val pampulhaIntermedica = PampulhaIntermedica(
            nome = funcionarioDB.nome,
            idFuncionario = funcionarioDB.id!!,
            cpf = funcionarioDB.cpf,
            dataAdimissao = funcionarioDB.dataAdimissao,
            endereco = enderecoDB.get()
        )

        pampulhaIntermedicaRepository.save(pampulhaIntermedica)

        //Dental Sorriso
        val dentalSorriso = DentalSorriso(
            nome = funcionarioDB.nome, cpf = funcionarioDB.cpf,
            alturaCm = altura,
            pesoKg = peso,
            idFuncionario = funcionarioDB.id!!
        )

        dentalSorrisoRepository.save(dentalSorriso)

        //Mente Sã, Corpo São
        val menteSaCorpoSao = MenteSaCorpoSao(
            cpf = funcionarioDB.cpf,
            idFuncionario = funcionarioDB.id!!,
            horasMeditadas = horasMeditadas
        )

        menteSaCorpoSaoRepository.save(menteSaCorpoSao)

        return ResponseEntity.ok("""{ "result": "Benefícios atribruidos ao funcionário com sucesso!"}""")
    }
}