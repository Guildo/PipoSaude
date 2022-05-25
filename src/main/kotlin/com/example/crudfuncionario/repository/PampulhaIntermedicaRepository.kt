package com.example.crudfuncionario.repository

import com.example.crudfuncionario.modelos.planossaude.PampulhaIntermedica
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface PampulhaIntermedicaRepository: MongoRepository<PampulhaIntermedica, String> {

}