package com.example.msyql_example.post.entity

import com.example.msyql_example.post.dto.PostResponseDto
import jakarta.persistence.*

@Entity
class Post (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id : Long?,

    @Column(nullable = false, length = 100)
    var title : String,

    @Column(nullable = false, length = 2000)
    var post : String,

    @Column(nullable = false, length = 50)
    var userId : Long,

    @Column(nullable = false, length = 10)
    var isPublic : Boolean = true
) {

    fun toResponse() : PostResponseDto = PostResponseDto(
        id = id,
        title = title,
        post = post,
        userId = userId,
        isPublic = isPublic,
    )
}