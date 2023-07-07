package com.example.BlogPlatformAPI.repository;

import com.example.BlogPlatformAPI.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}
