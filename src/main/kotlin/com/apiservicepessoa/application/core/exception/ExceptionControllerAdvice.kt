package com.apiservicepessoa.application.core.exception

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.LocalDate

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handlerBadRequestExceptions(ex: MethodArgumentTypeMismatchException, webRequest: WebRequest):ResponseEntity<MsgExceptionResponse>{
        var exceptionMessage: MsgExceptionResponse = MsgExceptionResponse(LocalDate.now(), HttpStatus.BAD_REQUEST.value(),ex.message, webRequest.getDescription(false))
        return ResponseEntity(exceptionMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NumberFormatException::class)
    fun handleNumberFormatException(ex: NumberFormatException, webRequest: WebRequest): ResponseEntity<MsgExceptionResponse>{
        val exceptionMessage = MsgExceptionResponse(LocalDate.now(), HttpStatus.BAD_REQUEST.value(),"Dados incorretos.", webRequest.getDescription(false))
        return ResponseEntity(exceptionMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(ex: NoSuchElementException, webRequest: WebRequest): ResponseEntity<MsgExceptionResponse>{
        val exceptionMessage = MsgExceptionResponse(LocalDate.now(), HttpStatus.NOT_FOUND.value(),"Dado não encontrado.", webRequest.getDescription(false))
        return ResponseEntity(exceptionMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun handleEmptyResultDataAccessException(ex: EmptyResultDataAccessException, webRequest: WebRequest): ResponseEntity<MsgExceptionResponse>{
        val exceptionMessage = MsgExceptionResponse(LocalDate.now(), HttpStatus.NOT_FOUND.value(),"Dado não encontrado.", webRequest.getDescription(false))
        return ResponseEntity(exceptionMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(EmailException::class)
    fun handleEmailException(ex: EmailException, webRequest: WebRequest): ResponseEntity<MsgExceptionResponse>{
        val exceptionMessage = MsgExceptionResponse(LocalDate.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.message, webRequest.getDescription(false))
        return ResponseEntity(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(ex: HttpRequestMethodNotSupportedException, webRequest: WebRequest): ResponseEntity<MsgExceptionResponse>{
        val exceptionMessage = MsgExceptionResponse(LocalDate.now(), HttpStatus.FORBIDDEN.value() , "Formato de dados incorreto.", webRequest.getDescription(false))
        return ResponseEntity(exceptionMessage, HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(PessoaNotFoundException::class)
    fun handlePessoaNotFoundException(ex: PessoaNotFoundException, webRequest: WebRequest): ResponseEntity<MsgExceptionResponse>{
        val exceptionMessage = MsgExceptionResponse(LocalDate.now(), HttpStatus.NOT_FOUND.value() , ex.message, webRequest.getDescription(false))
        return ResponseEntity(exceptionMessage, HttpStatus.NOT_FOUND)
    }

}