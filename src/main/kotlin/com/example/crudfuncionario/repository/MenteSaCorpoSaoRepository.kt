package com.example.crudfuncionario.repository

import com.example.crudfuncionario.modelos.planossaude.MenteSaCorpoSao
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface MenteSaCorpoSaoRepository: MongoRepository<MenteSaCorpoSao, String> {

}