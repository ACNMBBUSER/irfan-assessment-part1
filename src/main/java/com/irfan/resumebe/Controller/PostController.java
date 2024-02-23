package com.irfan.resumebe.Controller;

import com.irfan.resumebe.Model.Post;
import com.irfan.resumebe.Service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    List<Post> findAll() {
        return postService.findAll();
    }
}
