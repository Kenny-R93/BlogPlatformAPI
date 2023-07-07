package com.example.BlogPlatformAPI.service;

import com.example.BlogPlatformAPI.controller.BlogPostNotFoundException;
import com.example.BlogPlatformAPI.model.BlogPost;
import com.example.BlogPlatformAPI.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {
    private final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostService (BlogPostRepository blogPostRepository) {
    this.blogPostRepository = blogPostRepository;
    }


    // Create

    public BlogPost createPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }


    // Read
    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost getPostById(Long id) {
        return blogPostRepository.findById(id).orElse(null);
    }

    // Update
    public BlogPost updatePost(Long id, BlogPost blogPost) {
        BlogPost existingPost = getPostById(id);
        if (existingPost != null) {
            existingPost.setTitle(blogPost.getTitle());
            existingPost.setContent(blogPost.getContent());
            existingPost.setAuthor(blogPost.getAuthor());
            return blogPostRepository.save(existingPost);
        }else {
            throw new BlogPostNotFoundException(id);
        }

    }

    public void deletePost(Long id) {
        blogPostRepository.deleteById(id);
    }

}