package com.TRZ.Taraz.Controllers;

import com.TRZ.Taraz.models.Role;
import com.TRZ.Taraz.models.User;
import com.TRZ.Taraz.reprository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/signUp")
    public String signUp(Model model) {
        return "signUp";
    }


    @PostMapping("/signUp")
    public String addUser(User user, Map<String, Object> model) {
         User UserFromDb = userRepo.findByUsername(user.getUsername());
         if(UserFromDb != null){
            model.put("message", "User exists");
            return "signUp";
         }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));


        userRepo.save(user);
        System.out.println("---------------------------------------" + user.getUsername());
        return "redirect:/login";
    }
}
