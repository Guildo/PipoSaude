package com.example.crudfuncionario.repository

import com.example.crudfuncionario.modelos.planossaude.DentalSorriso
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface DentalSorrisoRepository: MongoRepository<DentalSorriso, String> {

    //fun findByIdFuncionario(idFuncionario: String): Optional<DentalSorriso>

}