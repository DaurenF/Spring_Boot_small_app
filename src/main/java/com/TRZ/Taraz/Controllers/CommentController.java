package com.TRZ.Taraz.Controllers;

import com.TRZ.Taraz.models.Comment;
import com.TRZ.Taraz.models.News;
import com.TRZ.Taraz.models.User;
import com.TRZ.Taraz.reprository.CommentRepository;
import com.TRZ.Taraz.reprository.NewsRepository;
import com.TRZ.Taraz.reprository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Controller
public class CommentController {
    @Autowired
    private NewsRepository  newsRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/news/{id}")
    public String comments(@PathVariable(value = "id") long id, Model model, Comment comment, Principal principal, Map<String, Object> modelMap) {
        News news1 = newsRepository.findById(id).orElseThrow();
        news1.setViews(news1.getViews() + 1);
        newsRepository.save(news1);
        model.addAttribute("news1", news1);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User user = userRepository.findByUsername(login);
        comment.setUser(user);

        News news = newsRepository.findById(id).orElseThrow();
        comment.setNews(news);
        /*Setting current date*/
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(); // This object contains the current date value
        String current_date = formatter.format(date);
        comment.setComment_date(current_date);

        commentRepository.save(comment);
        /*OUTPUT COMMENTS*/
        News output_news = newsRepository.findById(id).orElseThrow();
        Iterable<Comment> comments = commentRepository.findAllByNews(output_news);
        model.addAttribute("comments", comments);
        return "news";
    }

}
