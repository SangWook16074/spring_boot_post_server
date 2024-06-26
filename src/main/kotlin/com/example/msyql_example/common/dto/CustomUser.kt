package com.example.msyql_example.common.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class CustomUser(
    val id : Long,
    email : String,
    password : String,
    authorities : Collection<GrantedAuthority>
) : User(email, password, authorities)