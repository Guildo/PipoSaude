package com.example.employeebenefits.controller

import com.example.employeebenefits.modelos.planossaude.DentalSorriso
import com.example.employeebenefits.modelos.planossaude.NorteEuropa
import com.example.employeebenefits.repository.DentalSorrisoRepository
import com.example.employeebenefits.repository.FuncionarioRepository
import com.example.employeebenefits.repository.NorteEuropaRepository
import com.example.employeebenefits.utils.CPFUtil
import org.jetbrains.annotations.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@Service
@RestController
@RequestMapping("acmeCo")
class AcmeCoController (val funcionarioRepository: FuncionarioRepository,
                        val norteEuropaRepository: NorteEuropaRepository,
                        val dentalSorrisoRepository: DentalSorrisoRepository) {

    @PostMapping
    fun addBenefits(@RequestParam @NotNull cpf: String,
                    @RequestParam @NotNull peso: Double,
                    @RequestParam @NotNull altura: Double): ResponseEntity<String> {

        if (cpf.isEmpty()) {
            throw RuntimeException("O CPF do funcionário é obrigatório para este procedimento!")
        }

        if (!CPFUtil.validateCPF(cpf)) {
            throw RuntimeException("CPF inválido!")
        }

        if (peso.isNaN() || peso <= 0) {
            throw RuntimeException("O PESO do funcionário deve ser informado por um número maior que zero!")
        }

        if (altura.isNaN() || altura <= 0) {
            throw RuntimeException("A ALTURA do funcionário deve ser informada por um número maior que zero!")
        }

        val funcionarioDB = funcionarioRepository.findByCpf(cpf).orElseThrow { RuntimeException("Funcionário com cpf $cpf não encontrado!") }

        //Norte Europa
        val norteEuropa = NorteEuropa(
            nome = funcionarioDB.nome,
            cpf = funcionarioDB.cpf,
            dataAdimissao = funcionarioDB.dataAdimissao,
            email = funcionarioDB.email,
            idFuncionario = funcionarioDB.id!!
        )

        norteEuropaRepository.save(norteEuropa)

        //Dental Sorriso
        val dentalSorriso = DentalSorriso(
            nome = funcionarioDB.nome,
            cpf = funcionarioDB.cpf,
            alturaCm = altura,
            pesoKg = peso,
            idFuncionario = funcionarioDB.id!!
        )

        dentalSorrisoRepository.save(dentalSorriso)

        return ResponseEntity.ok("""{ "result": "Benefícios atribruidos ao funcionário com sucesso!"}""")
    }
}