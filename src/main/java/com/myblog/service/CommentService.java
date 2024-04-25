package com.myblog.service;

import com.myblog.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId);

    void deleteCommentById(long id);

    CommentDto updateCommentById(long id, CommentDto commentDto);
}
