package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postsDao;

    public PostController(PostRepository postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping("/posts")
    public String showPosts(Model model) {
        Post post1 = new Post("Xbox", "New in the box, $300");
        Post post2 = new Post("Ipad", "Ipad 2, 16gig, $100");
        Post post3 = new Post("Laptop", "Old laptop, works, $100");
        Post post4 = new Post("Nissan Maxima", "1989, Runs well, cold AC, $2000");
        Post post5 = new Post("Skateboard", "Used, new wheels $30");
        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);
        posts.add(post5);
        model.addAttribute("posts", posts);
        return "/posts/index";
    }

    @GetMapping("/posts/{id}/edit")
    public String returnEditView(@PathVariable long id, Model viewModel) {


        viewModel.addAttribute("post", postsDao.getById(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public  String updatePost(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
        Post post = postsDao.getById(id);
        post.setTitle(title);
        post.setBody(body);
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{title}")
    public String show(@PathVariable String title, Model model) {
        Post post = new Post("Xbox", "New in the box, $300");
        model.addAttribute("title", post.getTitle());
        model.addAttribute("body", post.getBody());
        return "/posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String create(@PathVariable long id) {
        return "Here is the post create form...";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String insert() {
        return "New post saved...";
    }


}


