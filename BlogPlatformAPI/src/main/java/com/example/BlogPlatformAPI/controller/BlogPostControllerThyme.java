package com.example.BlogPlatformAPI.controller;

import com.example.BlogPlatformAPI.model.BlogPost;
import com.example.BlogPlatformAPI.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/view")
public class BlogPostControllerThyme {

    private final BlogPostService blogPostService;
    @Autowired
    BlogPostThymeController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    // Create
    @GetMapping("/create")
    public String createPostForm(Model model) {
        model.addAttribute("blogPost", new BlogPost());
        return "CreateForm";
    }

    @PostMapping("")
    public String createPost(@ModelAttribute("blogPost") BlogPost blogPost) {
        blogPostService.createPost(blogPost);
        return "redirect:/view";
    }

    // Read
    @GetMapping("")
    public String getAllPosts(Model model) {
        List<BlogPost> blogPostList = blogPostService.getAllPosts();
        model.addAttribute("blogPosts", blogPostList);
        return "PostList";
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable("id") Long id, Model model) {
        Optional<BlogPost> blogPost = Optional.ofNullable(blogPostService.getPostById(id));
        if(blogPost.isPresent()) {
            model.addAttribute("blogPost", blogPost.get());
            return "Post";
        } else {
            return "Error";
        }
    }


    // Update
    @GetMapping("/update/{id}")
    public String updatePostForm(@PathVariable("id") Long id, Model model) {

        Optional<BlogPost> blogPost = Optional.ofNullable(blogPostService.getPostById(id));
        if (blogPost.isPresent()) {
            model.addAttribute("blogPost", blogPost.get());
            return "UpdateForm";
        } else {
            return "Error";
        }
    }

    @PostMapping("/{id}")
    public String updatePost(@PathVariable("id") Long id, @ModelAttribute("blogPost") BlogPost updatedBlogPost) {
        Optional<BlogPost> existingPost = Optional.ofNullable(blogPostService.getPostById(id));
        if (existingPost == null) {
            return "Error";
        }
        blogPostService.updatePost(id, updatedBlogPost);
        return "redirect:/view";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        blogPostService.deletePost(id);
        return "redirect:/view";
    }

}