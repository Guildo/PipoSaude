package com.example.crudfuncionario.repository

import com.example.crudfuncionario.modelos.planossaude.NorteEuropa
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface NorteEuropaRepository: MongoRepository<NorteEuropa, String> {

}