package com.apiservicepessoa.adapters.outbound.rest

import com.apiservicepessoa.adapters.inbound.dto.PessoaRequestDTO
import com.apiservicepessoa.adapters.inbound.dto.PessoaResponseDTO
import com.apiservicepessoa.application.core.domainservice.PessoaService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api(value = "Api pessoas", description = "Rest Api pessoas")
@RestController
@RequestMapping("/pessoas")
class PessoaController(private val pessoaService: PessoaService) {

    @ApiOperation(value = "Get lista de pessoas")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "OK"),
        ApiResponse(code = 404, message = "Not Found")
    ])
    @GetMapping("")
    fun getPessoaList(): ResponseEntity<List<PessoaResponseDTO>> =
        ResponseEntity.status(HttpStatus.OK).body(pessoaService.lisPessoas())

    @ApiOperation(value = "Get pessoa por id")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "OK"),
        ApiResponse(code = 400, message = "Dado não encontrado."),
        ApiResponse(code = 404, message = "Dados incorretos.")
    ])
    @GetMapping("/{id}")
    fun getPessoaById(@PathVariable id: Long): ResponseEntity<PessoaResponseDTO> =
        ResponseEntity.ok().body(pessoaService.getById(id))

    @ApiOperation(value = "Get pessoa pro email")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "OK"),
        ApiResponse(code = 404, message = "Dados incorretos.")
    ])
    @GetMapping("/email/{email}")
    fun getPessoaByEmail(@PathVariable email: String): ResponseEntity<PessoaResponseDTO> =
        ResponseEntity.status(HttpStatus.OK).body(pessoaService.getByEmail(email))

    @ApiOperation(value = "Post salvar pessoa")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "OK"),
        ApiResponse(code = 404, message = "Not Found"),
        ApiResponse(code = 403, message = "Formato de dados incorreto."),
        ApiResponse(code = 500, message = "Email já cadastrado.")
    ])
    @PostMapping("/pessoas")
    fun postPessoa(@RequestBody pessoaRequestDTO: PessoaRequestDTO): ResponseEntity<PessoaResponseDTO> =
        ResponseEntity.ok(pessoaService.save(pessoaRequestDTO))

    @ApiOperation(value = "Put atualizar pessoa")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Pessoa atualizada com sucesso."),
        ApiResponse(code = 404, message = "Not Found ou Dado não encontrado."),
        ApiResponse(code = 403, message = "Formato de dados incorreto."),
        ApiResponse(code = 500, message = "Email já cadastrado.")
    ])
    @PutMapping("/pessoas/{id}")
    fun putPessoa(@PathVariable id: Long, @RequestBody pessoaDTO: PessoaRequestDTO): ResponseEntity<PessoaResponseDTO> =
        ResponseEntity.ok(pessoaService.update(id, pessoaDTO))

    @ApiOperation(value = "Delete pessoa")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Dado excluido com sucesso."),
        ApiResponse(code = 400, message = "Dado não encontrado."),
        ApiResponse(code = 404, message = "Dados incorretos.")
    ])
    @DeleteMapping("/{id}")
    fun deletePessoa(@PathVariable id: Long): ResponseEntity<String> =
        pessoaService.delete(id).let { ResponseEntity.ok("{'msg':'Dado excluido com sucesso.'}")}

}