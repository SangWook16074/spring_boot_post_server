package com.example.msyql_example.post.controller

import com.example.msyql_example.common.dto.BaseResponse
import com.example.msyql_example.post.dto.PostRequestDto
import com.example.msyql_example.post.dto.PostResponseDto
import com.example.msyql_example.post.service.PostService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SecurityRequirement(name = "BearerAuth")
@Tag(name = "게시판 Api 컨트롤러", description = "게시판 글 전제조회, 특정 글 조회, 생성, 수정, 삭제 Api 명세서입니다.")
@RestController
@RequestMapping("/api/posts")
class PostController {

    @Autowired
    private lateinit var postService: PostService


    /**
     * 모든 게시글을 가져오는 Api
     */
    @GetMapping
    private fun getPosts() : ResponseEntity<BaseResponse<List<PostResponseDto>>> {
        val result = postService.getPosts()
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    /**
     * id를 통해 게시글을 가져오는 Api
     */
    @GetMapping("/{id}")
    private fun getPostsById(@PathVariable id : Long) : ResponseEntity<BaseResponse<PostResponseDto>> {
        val result = postService.getPostsById(id)
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    /**
     * 사용자 ID에 해당하는 게시글 가져오는 Api
     */
    @GetMapping("/user/{id}")
    private fun getPostByUserId(@PathVariable id : Long) :
            ResponseEntity<BaseResponse<PostResponseDto>> {
        val result = postService.getPostByUserId(id)
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    /**
     * 게시글을 생성하는 Api
     */
    @PostMapping
    private fun postPost(@Valid @RequestBody postRequestDto: PostRequestDto) :
            ResponseEntity<BaseResponse<PostResponseDto>> {
        val result = postService.postPosts(postRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse(data = result))
    }

    /**
     * 게시글 수정 Api
     */
    @PutMapping("/{id}")
    private fun putPost(@Valid @RequestBody postRequestDto: PostRequestDto, @PathVariable id : Long) :
            ResponseEntity<BaseResponse<PostResponseDto>> {
        val result = postService.putPost(postRequestDto, id)
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    /**
     * id를 통해 게시글을 삭제하는 Api
     */
    @DeleteMapping("/{id}")
    private fun deletePost(@PathVariable id : Long) : ResponseEntity<BaseResponse<Any>> {
        postService.deletePost(id)
        return ResponseEntity.ok().body(BaseResponse(data = null))
    }
}