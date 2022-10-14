package com.apiservicepessoa.adapters.outbound.repository

import com.apiservicepessoa.adapters.inbound.entity.PessoaEntity
import com.apiservicepessoa.application.ports.outPort.PessoaPersistencePort
import org.springframework.stereotype.Component

@Component
class PessoaPersistencePortImpl(val pessoaRepository: PessoaRepository) : PessoaPersistencePort {

    override fun getById(id: Long): PessoaEntity = pessoaRepository.findById(id).get()

    override fun getByEmail(email: String): PessoaEntity = pessoaRepository.findByEmail(email)

    override fun save(pessoaEntity: PessoaEntity): PessoaEntity = pessoaRepository.save(pessoaEntity)

    override fun lisPessoas(): List<PessoaEntity> = pessoaRepository.findAll()

    override fun delete(id: Long) = pessoaRepository.deleteById(id)

    override fun existsByEmail(email: String): Boolean = pessoaRepository.existsByEmail(email)

}