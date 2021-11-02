package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String showPosts() {
        return "posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String showPostId(@PathVariable long id) {
        return "Showing post # " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPost(@PathVariable long id) {
        return "";
    }

}


