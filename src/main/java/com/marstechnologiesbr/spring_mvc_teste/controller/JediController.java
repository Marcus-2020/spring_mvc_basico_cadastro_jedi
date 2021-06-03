package com.marstechnologiesbr.spring_mvc_teste.controller;

import com.marstechnologiesbr.spring_mvc_teste.model.Jedi;
import com.marstechnologiesbr.spring_mvc_teste.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class JediController {

    @Autowired
    private JediRepository repository; // repositório de Jedis

    @GetMapping("/jedi")
    public ModelAndView jedi() {

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi"); // define o nome da view

        modelAndView.addObject("allJedi", repository.getAllJedi()); // adiciona um objeto/modelo à view

        return modelAndView; // retorna o ModelAndView
    }

    @GetMapping("/new-jedi")
    public ModelAndView newJedi() {

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("new-jedi"); // define o nome da view

        modelAndView.addObject("jedi", new Jedi()); // adiciona um objeto/modelo à view

        return modelAndView; // retorna o ModelAndView
    }

    @PostMapping("/jedi")
    public String createJedi(@Valid @ModelAttribute Jedi jedi, BindingResult result,
                             RedirectAttributes redirectAttributes){

        // Se houverem erros mantem-se na página atual para exibi-los para o usuário
        if (result.hasErrors()) {
            return "new-jedi";
        }

        // Se não houverem errros adiciona o novo jedi ao repositório
        repository.add(jedi);

        // Adiciona um atributo message ao redirect que exibirá uma mensagem de sucesso na página destino
        redirectAttributes.addFlashAttribute("message", "Jedi successfully registered!");

        return "redirect:jedi"; // redireciona o usuário para a página 'jedi'
    }
}
