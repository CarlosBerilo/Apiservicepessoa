package com.apiservicepessoa.application.ports.outPort

import com.apiservicepessoa.adapters.inbound.entity.PessoaEntity

interface PessoaPersistencePort {
    fun getById(id: Long): PessoaEntity
    fun getByEmail(email: String): PessoaEntity
    fun save(pessoaEntity: PessoaEntity): PessoaEntity
    fun lisPessoas(): List<PessoaEntity>
    fun delete(id: Long)
    fun existsByEmail(email: String): Boolean
}