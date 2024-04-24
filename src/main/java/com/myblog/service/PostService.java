package com.myblog.service;

import com.myblog.entity.Post;
import com.myblog.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<Post> getAllPosts();
}
