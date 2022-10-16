package com.TRZ.Taraz.Controllers;

import com.TRZ.Taraz.models.News;
import com.TRZ.Taraz.models.Vacancy;
import com.TRZ.Taraz.reprository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VacancyController {
    @Autowired
    private VacancyRepository vacancyRepository;

    @GetMapping("/vacancy")
    public String vacancy(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {

        Iterable<Vacancy> vacancy = vacancyRepository.findAll();
        model.addAttribute("vacancy", vacancy);
        return "vacancy";
    }

    @GetMapping("/vacancy/{id}")
    public String vacancy(@PathVariable(value = "id") long id, Model model) {

        Vacancy vacancy1 = vacancyRepository.findById(id).orElseThrow();
        vacancyRepository.save(vacancy1);
        model.addAttribute("vacancy1", vacancy1);

        return "vacancy_detail";
    }
}
