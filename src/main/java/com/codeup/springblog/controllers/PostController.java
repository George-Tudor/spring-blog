package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostImage;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailServices;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private PostRepository postsDao;
    private UserRepository usersDao;
    private EmailServices emailService;

    public PostController(PostRepository postsDao, UserRepository usersDao, EmailServices emailService) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
    }


    @GetMapping("/posts")
    public String showPosts(Model viewModel) {
//        Post post1 = new Post("Xbox", "New in the box, $300");
//        Post post2 = new Post("Ipad", "Ipad 2, 16gig, $100");
//        Post post3 = new Post("Laptop", "Old laptop, works, $100");
//        Post post4 = new Post("Nissan Maxima", "1989, Runs well, cold AC, $2000");
//        Post post5 = new Post("Skateboard", "Used, new wheels $30");
//        List<Post> posts = new ArrayList<>();
//        posts.add(post1);
//        posts.add(post2);
//        posts.add(post3);
//        posts.add(post4);
//        posts.add(post5);
        List<Post> posts = postsDao.findAll();
        viewModel.addAttribute("posts", posts);
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
    public String create(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

//    @PostMapping("/posts/create")
//    public String insert(@RequestParam String title, @RequestParam String body, @RequestParam List<String> urls) {
//        List<PostImage> images = new ArrayList<>();
//        User author = usersDao.getById(1L);
//        Post post = new Post(title, body);
//
//        // create list of post image objects to pass to the new post constructor
//        for (String url : urls) {
//            PostImage postImage = new PostImage(url);
//            postImage.setPost(post);
//            images.add(postImage);
//        }
//
//        post.setImages(images);
//
//        post.setUser(author);
//
//        // save a post object with images
//
//        postsDao.save(post);
//
//        // modify the post index view to display post images
//        return "redirect:/posts";
//    }
@PostMapping("/posts/create")
    public String insert(@ModelAttribute Post post) {
    User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User author = usersDao.getById(principal.getId());

        //Post post = new Post(title, body);

        // create list of post image objects to pass to the new post constructor
//        for (String url : urls) {
//            PostImage postImage = new PostImage(url);
//            postImage.setPost(post);
//            images.add(postImage);
//        }
//
//        post.setImages(images);

        post.setUser(author);

        // save a post object with images

        postsDao.save(post);
        emailService.prepareAndSend(post, "You created " + post.getTitle(), post.getBody());
        // modify the post index view to display post images
        return "redirect:/posts";
    }



    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        postsDao.deleteById(id);
        return "redirect:/posts";
    }
}


