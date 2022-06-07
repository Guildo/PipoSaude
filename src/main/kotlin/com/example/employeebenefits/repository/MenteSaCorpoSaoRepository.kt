package com.example.employeebenefits.repository

import com.example.employeebenefits.modelos.planossaude.MenteSaCorpoSao
import org.springframework.data.mongodb.repository.MongoRepository

interface MenteSaCorpoSaoRepository: MongoRepository<MenteSaCorpoSao, String> {

}