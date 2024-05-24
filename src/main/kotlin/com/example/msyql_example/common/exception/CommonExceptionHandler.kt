package com.example.msyql_example.common.exception

import com.example.msyql_example.common.dto.BaseResponse
import com.example.msyql_example.common.enums.ResultStatus
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class CommonExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException::class)
    protected fun notFoundApiUrlExceptionHandler(exception: NoHandlerFoundException) :
        ResponseEntity<BaseResponse<Any>> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            BaseResponse(status = ResultStatus.ERROR.name, resultMsg = "존재하지 않는 Api Url 입니다.")
        )
    }

    @ExceptionHandler(Exception::class)
    protected fun defaultExceptionHandler(exception: Exception) :
            ResponseEntity<BaseResponse<Any>> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            BaseResponse(status = ResultStatus.ERROR.name, resultMsg = ResultStatus.ERROR.msg)
        )
    }
}