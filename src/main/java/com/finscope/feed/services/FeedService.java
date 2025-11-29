package com.finscope.feed.services;

import com.finscope.feed.entities.Post;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class FeedService {

    public List<Post> getFeed() {
        // Return dummy list of posts for now
        Post post1 = new Post(
                1L,
                "First Post",
                "https://example.com/image1.jpg",
                Arrays.asList("technology", "java", "spring"),
                "This is the content of the first post",
                101L,
                LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(1)
        );

        Post post2 = new Post(
                2L,
                "Second Post",
                "https://example.com/image2.jpg",
                Arrays.asList("coding", "backend"),
                "This is the content of the second post",
                102L,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now()
        );

        Post post3 = new Post(
                3L,
                "Third Post",
                null,
                Arrays.asList("tutorial", "guide"),
                "This is the content of the third post without media",
                103L,
                LocalDateTime.now().minusHours(5),
                LocalDateTime.now().minusHours(5)
        );

        return Arrays.asList(post1, post2, post3);
    }
}
