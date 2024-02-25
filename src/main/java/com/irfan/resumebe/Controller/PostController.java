package com.irfan.resumebe.Controller;

import com.irfan.resumebe.Model.Post;
import com.irfan.resumebe.Service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    Post findById(@PathVariable Integer id) {
        return postService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Post create(@RequestBody Post post) {
        return postService.create(post);
    }

    @PutMapping
    Post update (@PathVariable Integer id, @RequestBody Post post) {
        return postService.update(id, post);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        postService.delete(id);
    }
}
