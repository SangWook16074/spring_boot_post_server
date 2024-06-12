package com.example.msyql_example.member.controller

import com.example.msyql_example.common.dto.BaseResponse
import com.example.msyql_example.common.dto.TokenInfo
import com.example.msyql_example.member.dto.LoginDto
import com.example.msyql_example.member.dto.MemberRequestDto
import com.example.msyql_example.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member")
class MemberController(
    private val memberService: MemberService
) {
    /**
     * 회원가입 Api
     */
    @PostMapping("/join")
    private fun signUp(@Valid @RequestBody memberRequestDto: MemberRequestDto)
    : ResponseEntity<BaseResponse<String>> {
        val result = memberService.signUp(memberRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(BaseResponse(data = result))
    }

    /**
     * 로그인 Api
     */
    @PostMapping("/login")
    private fun login(@Valid @RequestBody loginDto: LoginDto)
    : ResponseEntity<BaseResponse<TokenInfo>> {
        val tokenInfo = memberService.login(loginDto)
        return ResponseEntity.status(HttpStatus.OK)
            .body(BaseResponse(data = tokenInfo))
    }
}