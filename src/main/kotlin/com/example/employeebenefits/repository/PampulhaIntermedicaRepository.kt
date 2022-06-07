package com.example.employeebenefits.repository

import com.example.employeebenefits.modelos.planossaude.PampulhaIntermedica
import org.springframework.data.mongodb.repository.MongoRepository

interface PampulhaIntermedicaRepository: MongoRepository<PampulhaIntermedica, String> {

}