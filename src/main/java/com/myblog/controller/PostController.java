package com.myblog.controller;

import com.myblog.payload.PostDto;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    //  @Autowired
    //  private PostService postService;

    // Constructor based Dependency Injection
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // POST http://localhost:8080/api/posts
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto dto = postService.createPost(postDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // GET http://localhost:8080/api/posts?id={id}
    // @GetMapping
    @GetMapping(params = "id")
    public ResponseEntity<PostDto> getPostById(@RequestParam long id) {
        PostDto dto = postService.getPostById(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // GET http://localhost:8080/api/posts
    // @GetMapping(params = "all")
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Pagination Concept
    // GET http://localhost:8080/api/posts?pageNo=0&pageSize=3
    @GetMapping(params = {"pageNo", "pageSize"})
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize
    ) {
        List<PostDto> posts = postService.getAllPosts(pageNo, pageSize);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Pagination Concept with Sorting -> Ascending Order Bydefault
    // GET http://localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy=title
    @GetMapping(params = {"pageNo", "pageSize", "sortBy"})
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy
    ) {
        List<PostDto> posts = postService.getAllPosts(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // Pagination Concept with Sorting with Directtion
    // GET http://localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy=title&sortDir=desc
    @GetMapping(params = {"pageNo", "pageSize", "sortBy", "sortDir"})
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3") int pageSize,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue = "id") String sortDir
    ) {
        List<PostDto> posts = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
