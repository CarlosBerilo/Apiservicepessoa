package com.apiservicepessoa.adapters.inbound.dto

import com.apiservicepessoa.application.core.domain.PessoaDomain
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDateTime
import java.util.*

class PessoaResponseDTO(
    var id: Long? = null,
    var codido: UUID? = null,
    var nome: String? = "",
    var email: String? = "",
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var createdAt: LocalDateTime? = null,
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var updateAt: LocalDateTime? = null
){
    constructor(pessoaDomain: PessoaDomain) : this(
        pessoaDomain.id,
        pessoaDomain.codido,
        pessoaDomain.nome,
        pessoaDomain.email,
        pessoaDomain.createdAt,
        pessoaDomain.updateAt
    )
}