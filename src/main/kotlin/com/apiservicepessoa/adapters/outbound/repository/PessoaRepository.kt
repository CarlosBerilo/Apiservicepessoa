package com.apiservicepessoa.adapters.outbound.repository

import com.apiservicepessoa.adapters.inbound.entity.PessoaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PessoaRepository : JpaRepository<PessoaEntity, Long>{
    fun findAllByNome(nome: String): PessoaEntity
    fun findByEmail(email: String): PessoaEntity
    fun existsByEmail(email: String): Boolean
}