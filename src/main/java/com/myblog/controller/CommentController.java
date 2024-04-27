package com.myblog.controller;

import com.myblog.payload.CommentDto;
import com.myblog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // POST http://localhost:8080/api/comments?postId={postId}
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @RequestParam long postId) {
        CommentDto dto = commentService.createComment(commentDto, postId);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // DELETE http://localhost:8080/api/comments/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable long id) {
        commentService.deleteCommentById(id);

        return new ResponseEntity<>("Comment is Deleted!!", HttpStatus.OK);
    }

    // PUT http://localhost:8080/api/comments/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable long id, @RequestBody CommentDto commentDto) {
        CommentDto dto = commentService.updateCommentById(id, commentDto);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
