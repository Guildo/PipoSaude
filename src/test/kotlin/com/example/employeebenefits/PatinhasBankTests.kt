package com.example.employeebenefits

import com.example.employeebenefits.modelos.Endereco
import com.example.employeebenefits.modelos.Funcionario
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
)
@TestPropertySource(properties = ["spring.mongodb.embedded.version=3.5.5"])
class PatinhasBankTests(@Autowired var restTemplate: TestRestTemplate) {

    var funcionario = Funcionario(
        null,
        "Guilherme Jin Kajimura",
        "484.306.638-94",
        "gui@gmail.com",
        Calendar.getInstance().time
    )

    var endereco = Endereco(
        null,
        "Joaquim Nabuco",
        "957",
        "Presidente Prudente",
        "SP",
        ""
    )

    @Test
    fun `Sucesso na atribuição de benefícios - Tio Patinhas Bank`() {

        val headers = HttpHeaders()
        headers.contentType = MediaType.MULTIPART_FORM_DATA

        val map: MultiValueMap<String, Any> = LinkedMultiValueMap()

        map.add("cpf", "484.306.638-94") // VÁLIDO
        map.add("peso", 62.0) // VÁLIDO
        map.add("altura", 1.60) // VÁLIDO
        map.add("horasMeditadas", 5.2) // VÁLIDO

        val request = HttpEntity<MultiValueMap<String, Any>>(map, headers)

        // Cria funcionário temporário
        val funcionarioTemp = restTemplate.postForEntity("/funcionarios", funcionario, Funcionario::class.java)

        endereco.idFuncionario = funcionarioTemp.body?.id.toString()
        restTemplate.postForEntity("/funcionarios/endereco", endereco, Endereco::class.java)

        val result = restTemplate.postForEntity("/patinhasBank", request, Any::class.java)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(HttpStatus.OK, result.statusCode)
    }

    @Test
    fun `Falha na atribuição de benefícios - Tio Patinhas Bank - CPF inválido`() {

        val headers = HttpHeaders()
        headers.contentType = MediaType.MULTIPART_FORM_DATA

        val map: MultiValueMap<String, Any> = LinkedMultiValueMap()

        map.add("cpf", "111.333.888-22") // INVÁLIDO
        map.add("peso", 62.0) // VÁLIDO
        map.add("altura", 1.60) // VÁLIDO
        map.add("horasMeditadas", 5.2) // VÁLIDO

        val request = HttpEntity<MultiValueMap<String, Any>>(map, headers)

        // Cria funcionário temporário
        funcionario.cpf = "095.935.130-27"
        restTemplate.postForEntity("/funcionarios", funcionario, Funcionario::class.java)

        val result = restTemplate.postForEntity("/patinhasBank", request, Any::class.java)

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.statusCode)
    }

    @Test
    fun `Falha na atribuição de benefícios - Tio Patinhas Bank - peso inválido`() {

        val headers = HttpHeaders()
        headers.contentType = MediaType.MULTIPART_FORM_DATA

        val map: MultiValueMap<String, Any> = LinkedMultiValueMap()

        map.add("cpf", "673.888.930-59") // VÁLIDO
        map.add("peso", 0) // INVÁLIDO
        map.add("altura", 1.60) // VÁLIDO
        map.add("horasMeditadas", 5.2) // VÁLIDO

        val request = HttpEntity<MultiValueMap<String, Any>>(map, headers)

        // Cria funcionário temporário
        funcionario.cpf = "673.888.930-59"
        restTemplate.postForEntity("/funcionarios", funcionario, Funcionario::class.java)

        val result = restTemplate.postForEntity("/patinhasBank", request, Any::class.java)

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.statusCode)
    }

    @Test
    fun `Falha na atribuição de benefícios - Tio Patinhas Bank - altura inválida`() {

        val headers = HttpHeaders()
        headers.contentType = MediaType.MULTIPART_FORM_DATA

        val map: MultiValueMap<String, Any> = LinkedMultiValueMap()

        map.add("cpf", "836.376.330-60") // VÁLIDO
        map.add("peso", 62.0) // VÁLIDO
        map.add("altura", -424.32) // INVÁLIDO
        map.add("horasMeditadas", 5.2) // VÁLIDO

        val request = HttpEntity<MultiValueMap<String, Any>>(map, headers)

        // Cria funcionário temporário
        funcionario.cpf = "836.376.330-60"
        restTemplate.postForEntity("/funcionarios", funcionario, Funcionario::class.java)

        val result = restTemplate.postForEntity("/patinhasBank", request, Any::class.java)

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.statusCode)
    }

    @Test
    fun `Falha na atribuição de benefícios - Tio Patinhas Bank - horas medidatas inválida`() {

        val headers = HttpHeaders()
        headers.contentType = MediaType.MULTIPART_FORM_DATA

        val map: MultiValueMap<String, Any> = LinkedMultiValueMap()

        map.add("cpf", "672.991.360-66") // VÁLIDO
        map.add("peso", 62.0) // VÁLIDO
        map.add("altura", 1.60) // VÁLIDO
        map.add("horasMeditadas", 0) // INVÁLIDO

        val request = HttpEntity<MultiValueMap<String, Any>>(map, headers)

        // Cria funcionário temporário
        funcionario.cpf = "672.991.360-66"
        restTemplate.postForEntity("/funcionarios", funcionario, Funcionario::class.java)

        val result = restTemplate.postForEntity("/patinhasBank", request, Any::class.java)

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.statusCode)
    }
}