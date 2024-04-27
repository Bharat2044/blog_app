package com.myblog.service.impl;

import com.myblog.entity.Comment;
import com.myblog.entity.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.CommentDto;
import com.myblog.repository.CommentRepository;
import com.myblog.repository.PostRepository;
import com.myblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post Not Found with Id: " + postId)
        );

        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);

        return dto;
    }

    @Override
    public void deleteCommentById(long id) {
        boolean result = commentRepository.existsById(id);

        if (result) {
            commentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Comment Not Found with Id: " + id);
        }
    }

    @Override
    public CommentDto updateCommentById(long id, CommentDto commentDto) {

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Comment Not Found with Id: " + id)
        );

        Comment c = mapToEntity(commentDto);
        c.setId(comment.getId());
        c.setPost(comment.getPost());

        Comment savedComment = commentRepository.save(c);
        CommentDto dto = mapToDto(savedComment);

        return dto;
    }

    public CommentDto mapToDto(Comment comment) {
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;

        /*
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setText(comment.getText());
        dto.setEmail(comment.getEmail());

        return  dto;
         */
    }

    public Comment mapToEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;

        /*
        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setEmail(commentDto.getEmail());

        return comment;
        */
    }
}
