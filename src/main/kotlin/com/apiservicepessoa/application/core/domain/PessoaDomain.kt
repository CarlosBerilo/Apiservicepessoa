package com.apiservicepessoa.application.core.domain

import com.apiservicepessoa.adapters.inbound.dto.PessoaRequestDTO
import com.apiservicepessoa.adapters.inbound.dto.PessoaResponseDTO
import com.apiservicepessoa.adapters.inbound.entity.PessoaEntity
import java.time.LocalDateTime
import java.util.*

class PessoaDomain(
    var id: Long? = null,
    var codido: UUID? = null,
    var nome: String,
    var email: String,
    var createdAt: LocalDateTime? = null,
    var updateAt: LocalDateTime? = null
) {

    constructor(pessoaRequestDTO: PessoaRequestDTO) : this (
        null,
        null,
        pessoaRequestDTO.nome.orEmpty(),
        pessoaRequestDTO.email.orEmpty(),
        null,
        null
    )

    constructor(pessoaDTO: PessoaResponseDTO) : this (
        pessoaDTO.id,
        pessoaDTO.codido,
        pessoaDTO.nome.orEmpty(),
        pessoaDTO.email.orEmpty(),
        pessoaDTO.createdAt,
        pessoaDTO.updateAt
    )

    constructor(pessoaEntity: PessoaEntity) : this (
        pessoaEntity.id,
        pessoaEntity.codigo,
        pessoaEntity.nome,
        pessoaEntity.email,
        pessoaEntity.createdAt,
        pessoaEntity.updateAt
    )

}