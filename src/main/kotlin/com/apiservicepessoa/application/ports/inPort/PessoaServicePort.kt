package com.apiservicepessoa.application.ports.inPort

import com.apiservicepessoa.adapters.inbound.dto.PessoaRequestDTO
import com.apiservicepessoa.adapters.inbound.dto.PessoaResponseDTO

interface PessoaServicePort {
    fun getById(id: Long): PessoaResponseDTO?
    fun getByEmail(email: String): PessoaResponseDTO?
    fun save(pessoaRequestDTO: PessoaRequestDTO): PessoaResponseDTO?
    fun update(id: Long, pessoaRequestDTO: PessoaRequestDTO): PessoaResponseDTO?
    fun lisPessoas(): List<PessoaResponseDTO>
    fun delete(id: Long): Unit
}