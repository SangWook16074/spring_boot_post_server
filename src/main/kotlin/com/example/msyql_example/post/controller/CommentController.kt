package com.example.msyql_example.post.controller

import com.example.msyql_example.common.dto.BaseResponse
import com.example.msyql_example.post.dto.CommentRequestDto
import com.example.msyql_example.post.dto.CommentResponseDto
import com.example.msyql_example.post.service.CommentService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SecurityRequirement(name = "BearerAuth")
@Tag(name = "댓글 Api 컨트롤러", description = "댓글 등록, 조회 Api 명세서입니다.")
@RestController
@RequestMapping("/api/posts/comments")
class CommentController {

    @Autowired
    private lateinit var commentService: CommentService

    /**
     * 전체 댓글 조회 Api
     */
    @GetMapping
    private fun getComments() :
            ResponseEntity<BaseResponse<List<CommentResponseDto>>> {
        val result = commentService.getComments()
        return ResponseEntity
            .status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    /**
     * 게시판 댓글 등록 Api
     */
    @PostMapping("/{id}")
    private fun postComment(@PathVariable id : Long, @RequestBody commentRequestDto: CommentRequestDto) :
            ResponseEntity<BaseResponse<CommentResponseDto>> {
        val result = commentService.postComment(id, commentRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse(data = result))
    }
}