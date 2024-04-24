package com.myblog.service;

import com.myblog.entity.Post;
import com.myblog.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostDto getPostById(long id);

    List<PostDto> getAllPosts();

    List<PostDto> getAllPosts(int pageNo, int pageSize);

    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy);

    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
