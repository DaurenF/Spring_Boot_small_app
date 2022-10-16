package com.TRZ.Taraz.Controllers;

import com.TRZ.Taraz.models.Comment;
import com.TRZ.Taraz.models.News;
import com.TRZ.Taraz.models.User;
import com.TRZ.Taraz.models.Vacancy;
import com.TRZ.Taraz.reprository.CommentRepository;
import com.TRZ.Taraz.reprository.NewsRepository;
import com.TRZ.Taraz.reprository.UserRepository;
import com.TRZ.Taraz.reprository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        Iterable<News> news = newsRepository.findAll();
        model.addAttribute("news", news);

        return "main.html";
    }


    @GetMapping("/admin")
    public String admin(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        Iterable<News> news = newsRepository.findAll();
        model.addAttribute("news", news);

        return "admin";
    }

    @GetMapping("/about_center")
    public String about_center(Model model) {
        return "about_center";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        Iterable<News> news = newsRepository.findAll();
        model.addAttribute("news", news);

        /*GET CURRENT USER AS OBJECT*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User user = userRepository.findByUsername(login);
        model.addAttribute("user", user);
        return "home";
    }



    @GetMapping("/news")
    public String news(Model model) {
        Iterable<News> news = newsRepository.findAll();
        model.addAttribute("news", news);
        return "news";
    }

    @GetMapping("/news/{id}")
    public String news(@PathVariable(value = "id") long id, Model model) {
        News news1 = newsRepository.findById(id).orElseThrow();
        news1.setViews(news1.getViews() + 1);
        newsRepository.save(news1);
        model.addAttribute("news1", news1);

        News output_news = newsRepository.findById(id).orElseThrow();
        Iterable<Comment> comments = commentRepository.findAllByNews(output_news);
        model.addAttribute("comments", comments);
        return "news";

    }





}
