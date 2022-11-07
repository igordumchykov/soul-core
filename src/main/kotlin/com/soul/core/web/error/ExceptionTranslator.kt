package com.soul.core.web.error

import com.amazonaws.services.s3.model.AmazonS3Exception
import com.soul.core.service.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait


@ControllerAdvice
@ResponseBody
class ExceptionTranslator : SecurityAdviceTrait {

    @ExceptionHandler(EmailAlreadyUsedException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleEmailAlreadyUsed(e: EmailAlreadyUsedException) =
        ErrorResponse(e.message!!, HttpStatus.BAD_REQUEST.value())

    @ExceptionHandler(LoginAlreadyUsedException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleLoginAlreadyUsed(e: LoginAlreadyUsedException) =
        ErrorResponse(e.message!!, HttpStatus.BAD_REQUEST.value())

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNotFound(e: UserNotFoundException) = ErrorResponse(e.message!!, HttpStatus.NOT_FOUND.value())

    @ExceptionHandler(MenuNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleMenuNotFound(e: MenuNotFoundException) = ErrorResponse(e.message!!, HttpStatus.NOT_FOUND.value())

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValid(e: MethodArgumentNotValidException): ErrorResponse {
        val detail = e.bindingResult.fieldErrors.map {
            it.defaultMessage
        }.joinToString(separator = ",")
        return ErrorResponse("Invalid request body", HttpStatus.BAD_REQUEST.value(), detail)
    }

    @ExceptionHandler(AmazonS3Exception::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleAmazonS3Exception(e: AmazonS3Exception) = ErrorResponse(e.message!!, HttpStatus.INTERNAL_SERVER_ERROR.value())
}