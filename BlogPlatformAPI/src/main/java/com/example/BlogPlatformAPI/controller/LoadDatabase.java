package com.example.BlogPlatformAPI.controller;

import com.example.BlogPlatformAPI.model.BlogPost;
import com.example.BlogPlatformAPI.repository.BlogPostRepository;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class LoadDatabase {
    private static final Logger log = (Logger) LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BlogPostRepository blogPostRepository) {
        return args -> {
            blogPostRepository.save(new BlogPost("The Art of Unplugging: Embracing the Offline World", "Practicing the concept of digital detoxification", "Jennifer Dawson"));
            blogPostRepository.save(new BlogPost("Beneath the Stars: A Novice's Guide to Astrophotography", "A beginner's guide to astrophotography", "Sam Perkins"));
            blogPostRepository.save(new BlogPost("Green Thumbs, Clear Minds: The Therapeutic Power of Gardening", "Exploring the Therapeutic benefits of Gardening", "Angela Foster"));

            blogPostRepository.findAll().forEach(blogPost -> {
                log.info("Preloaded " + blogPost);
            });
        };
    }
}
