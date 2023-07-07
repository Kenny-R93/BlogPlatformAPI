package com.example.BlogPlatformAPI.controller;


import com.example.BlogPlatformAPI.model.BlogPost;
import com.example.BlogPlatformAPI.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class BlogPostController {
    private final BlogPostService blogPostService;

    private final BlogPostModelAssembler blogPostModelAssembler = null;

    @Autowired
    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
        this.blogPostModelAssembler = blogPostModelAssembler;
    }

    // Create
    @PostMapping("/posts")
    public EntityModel<BlogPost> createPost(@RequestBody BlogPost blogPost) {
        BlogPost createdPost = blogPostService.createPost(blogPost);

        return blogPostModelAssembler.toModel(createdPost);
    }

    // Read
    @GetMapping("/posts")
    public CollectionModel<EntityModel<BlogPost>> getAllPosts() {
        List<EntityModel<BlogPost>> blogPosts = blogPostService.getAllPosts().stream()
                .map(blogPostModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(blogPosts,
                linkTo(methodOn(BlogPostController.class).getAllPosts()).withSelfRel());
    }

    @GetMapping("/posts/{id}")
    public EntityModel<BlogPost> getPostsById(@PathVariable Long id) {
        BlogPost blogPost = blogPostService.getPostById(id)
                .orElseThrow(() -> new BlogPostNotFoundException(id));

        return blogPostModelAssembler.toModel(blogPost);
    }

    // Update
    @PutMapping("/posts/{id}")
    public EntityModel<?> updatePost(@PathVariable Long id, @RequestBody BlogPost updatedPost) {
        BlogPost updated = blogPostService.updatePost(id, updatedPost);

        return blogPostModelAssembler.toModel(updated);
    }

    // Delete
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        BlogPost blogPost = blogPostService.getPostById(id)
                .orElseThrow(() -> new BlogPostNotFoundException(id));
        blogPostService.deletePost(id);

        return ResponseEntity.noContent().build();
    }

}


