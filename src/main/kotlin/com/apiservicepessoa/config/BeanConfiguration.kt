package com.apiservicepessoa.config

import com.apiservicepessoa.adapters.inbound.mapper.PessoaMapper
import com.apiservicepessoa.application.core.domainservice.PessoaService
import com.apiservicepessoa.application.ports.outPort.PessoaPersistencePort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfiguration {
    @Bean
    fun pessoaService(pessoaPersistencePort: PessoaPersistencePort, pessoaMapper: PessoaMapper): PessoaService {
        return PessoaService(pessoaPersistencePort, pessoaMapper)
    }
}