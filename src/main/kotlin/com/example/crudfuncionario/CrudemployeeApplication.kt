package com.example.crudfuncionario

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class CrudemployeeApplication

fun main(args: Array<String>) {
	runApplication<CrudemployeeApplication>(*args)
}
