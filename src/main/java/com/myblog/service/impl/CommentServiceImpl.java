package com.myblog.service.impl;

import com.myblog.entity.Comment;
import com.myblog.entity.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.CommentDto;
import com.myblog.repository.CommentRepository;
import com.myblog.repository.PostRepository;
import com.myblog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post Not Found with Id: " + postId)
        );

        Comment comment = mapToEntity(commentDto, post);

        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);

        return dto;
    }

    public CommentDto mapToDto(Comment comment) {
        CommentDto dto = new CommentDto();

        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setEmail(comment.getEmail());

        return  dto;
    }

    public Comment mapToEntity(CommentDto commentDto, Post post) {
        Comment comment = new Comment();

        comment.setText(commentDto.getText());
        comment.setEmail(commentDto.getEmail());
        comment.setPost(post);

        return comment;
    }
}
