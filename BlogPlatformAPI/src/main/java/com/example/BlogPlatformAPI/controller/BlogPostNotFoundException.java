package com.example.BlogPlatformAPI.controller;

public class BlogPostNotFoundException extends RuntimeException {
    public BlogPostNotFoundException (Long id) {
        super("Could not find Blog Post " + id);
    }
}
