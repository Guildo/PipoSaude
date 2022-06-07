package com.example.employeebenefits.repository

import com.example.employeebenefits.modelos.planossaude.DentalSorriso
import org.springframework.data.mongodb.repository.MongoRepository

interface DentalSorrisoRepository: MongoRepository<DentalSorriso, String> {

}