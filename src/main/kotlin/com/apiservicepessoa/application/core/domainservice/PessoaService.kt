package com.apiservicepessoa.application.core.domainservice

import com.apiservicepessoa.adapters.inbound.dto.PessoaRequestDTO
import com.apiservicepessoa.adapters.inbound.dto.PessoaResponseDTO
import com.apiservicepessoa.adapters.inbound.entity.PessoaEntity
import com.apiservicepessoa.adapters.inbound.mapper.PessoaMapper
import com.apiservicepessoa.application.core.exception.EmailException
import com.apiservicepessoa.application.core.exception.PessoaNotFoundException
import com.apiservicepessoa.application.ports.inPort.PessoaServicePort
import com.apiservicepessoa.application.ports.outPort.PessoaPersistencePort
import java.util.*

class PessoaService(
    private val pessoaPersistencePort: PessoaPersistencePort,
    private val pessoaMapper: PessoaMapper
) : PessoaServicePort {

    /**
     *  Buscar Pessoa por ID
     *  @param (id: Long)
     *  @return PessoaResponseDTO
     */
    override fun getById(id: Long): PessoaResponseDTO? =
        pessoaMapper.convertDomainToDto(pessoaMapper.convertEntityToDomain(pessoaPersistencePort.getById(id)))

    /**
     *  Buscar Pessoa por Email
     *  @param (email: String)
     *  @return PessoaResponseDTO
     */
    override fun getByEmail(email: String): PessoaResponseDTO? =
        pessoaMapper.convertDomainToDto( pessoaMapper.convertEntityToDomain(pessoaPersistencePort.getByEmail(email)))

    /**
     *  Verificar se email já é cadastrado e
     *  Gera o códido UUID
     *  @param (pessoaRequestDTO: PessoaRequestDTO)
     *  @return PessoaResponseDTO
     */
    override fun save(pessoaRequestDTO: PessoaRequestDTO): PessoaResponseDTO? {
        if (pessoaPersistencePort.existsByEmail(pessoaRequestDTO.email)) {
            throw EmailException("Email já cadastrado.")
        } else {
            val pessoaDomain = pessoaMapper.convertDtoToDomain(pessoaRequestDTO)
            pessoaDomain.codido = gerarUUID()
            val pessoaEntitySave = pessoaPersistencePort.save(pessoaMapper.convertDomainToEntity(pessoaDomain))
            return pessoaMapper.convertDomainToDto(pessoaMapper.convertEntityToDomain(pessoaEntitySave))
        }
    }

    /**
     *  Atualiza Email e Nome
     *  @param  id: Long, pessoaDTO: PessoaRequestDTO
     *  @return PessoaResponseDTO
     */
    override fun update(id: Long, pessoaRequestDTO: PessoaRequestDTO): PessoaResponseDTO? {
        var pessoaEntity: PessoaEntity? = null
        pessoaEntity = pessoaPersistencePort.getById(id)
        if (pessoaEntity != null) {
            if (pessoaPersistencePort.existsByEmail(pessoaRequestDTO.email)) {
                throw EmailException("Email já cadastrado.")
            } else {
                val pessoaDomainUpdate = pessoaMapper.convertEntityToDomain(pessoaEntity)
                pessoaDomainUpdate.nome = pessoaRequestDTO.nome
                pessoaDomainUpdate.email = pessoaRequestDTO.email
                val pessoaEntitySave = pessoaPersistencePort.save(pessoaMapper.convertDomainToEntity(pessoaDomainUpdate))
                return pessoaMapper.convertDomainToDto(pessoaMapper.convertEntityToDomain(pessoaEntitySave))
            }
        } else {
            throw PessoaNotFoundException("Not Found.")
        }
    }

    /**
     *  Listar Pessoas
     *  @param id:Long, pessoaDTO:PessoaRequestDTO
     *  @return List<'PessoaResponseDTO'>
     */
    override fun lisPessoas(): List<PessoaResponseDTO> = pessoaMapper.convertListEntityToDto(pessoaPersistencePort.lisPessoas())

    /**
     *  Exclui Pessoa pelo ID
     *  @param id: Long
     */
    override fun delete(id: Long): Unit = pessoaPersistencePort.delete(id)

    private fun gerarUUID(): UUID = UUID.randomUUID()

}