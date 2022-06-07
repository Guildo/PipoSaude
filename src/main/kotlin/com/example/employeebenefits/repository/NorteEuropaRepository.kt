package com.example.employeebenefits.repository

import com.example.employeebenefits.modelos.planossaude.NorteEuropa
import org.springframework.data.mongodb.repository.MongoRepository

interface NorteEuropaRepository: MongoRepository<NorteEuropa, String> {

}