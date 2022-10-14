package com.apiservicepessoa.adapters.inbound.entity

import com.apiservicepessoa.application.core.domain.PessoaDomain
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "tb_pessoas")
data class PessoaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column
    var codigo: UUID? = null,
    @Column(nullable = false)
    var nome: String,
    @Column(nullable = false)
    var email: String
) {
    @CreatedDate
    @DateTimeFormat(pattern = "DD/MM/YYYY hh:mm:ss")
    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime? = null

    @LastModifiedDate
    @DateTimeFormat(pattern = "DD/MM/YYYY hh:mm:ss")
    @Column(name = "update_at")
    var updateAt: LocalDateTime? = null

    constructor(pessoaDomain: PessoaDomain): this(
        pessoaDomain.id,
        pessoaDomain.codido,
        pessoaDomain.nome,
        pessoaDomain.email
    )

}