package com.apiservicepessoa.adapters.inbound.dto

import com.apiservicepessoa.application.core.domain.PessoaDomain

class PessoaRequestDTO(
    var nome: String,
    var email: String
){
    constructor(pessoaDomain: PessoaDomain) : this(
        pessoaDomain.nome,
        pessoaDomain.email
    )
}