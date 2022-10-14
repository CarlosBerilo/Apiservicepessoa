package com.apiservicepessoa.adapters.inbound.mapper

import com.apiservicepessoa.adapters.inbound.dto.PessoaRequestDTO
import com.apiservicepessoa.adapters.inbound.dto.PessoaResponseDTO
import com.apiservicepessoa.adapters.inbound.entity.PessoaEntity
import com.apiservicepessoa.application.core.domain.PessoaDomain
import org.springframework.stereotype.Component

@Component
class PessoaMapper {

    fun convertDtoToDomain(pessoaDTO: PessoaRequestDTO): PessoaDomain = PessoaDomain(pessoaDTO)

    fun convertDomainToDto(pessoaDomain: PessoaDomain): PessoaResponseDTO = PessoaResponseDTO(pessoaDomain)

    fun convertEntityToDomain(pessoaEntity: PessoaEntity): PessoaDomain = PessoaDomain(pessoaEntity)

    fun convertDomainToEntity(pessoaDomain: PessoaDomain): PessoaEntity = PessoaEntity(pessoaDomain)

    fun convertListEntityToDto(listEntity: List<PessoaEntity>): List<PessoaResponseDTO> {
        var listPessoasDTO = mutableListOf<PessoaResponseDTO>()
        for(pessoaEntity in listEntity){
            listPessoasDTO.add(convertDomainToDto(convertEntityToDomain(pessoaEntity)))
        }
        return listPessoasDTO
    }

}