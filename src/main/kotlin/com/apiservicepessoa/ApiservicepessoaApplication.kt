package com.apiservicepessoa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class ApiservicepessoaApplication

fun main(args: Array<String>) {
	runApplication<ApiservicepessoaApplication>(*args)
}