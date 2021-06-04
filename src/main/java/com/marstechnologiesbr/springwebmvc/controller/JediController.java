package com.marstechnologiesbr.springwebmvc.controller;

import com.marstechnologiesbr.springwebmvc.model.Jedi;
import com.marstechnologiesbr.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class JediController {

    @Autowired
    private JediRepository repository; // repositório de Jedis

    @GetMapping("/jedi")
    public ModelAndView jedi() {

        final ModelAndView modelAndView = new ModelAndView();
        // define o nome da view
        modelAndView.setViewName("jedi");

        // adiciona um objeto/modelo à view
        modelAndView.addObject("allJedi", repository.findAll());

        // retorna o ModelAndView
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam(value = "name") final String name) {

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");

        // atualiza o modelo de dados da view com dados que contenham a string passada no atributo 'name'
        modelAndView.addObject("allJedi", repository.findByNameContainingIgnoreCase(name));

        return modelAndView;
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
        repository.save(jedi);

        // Adiciona um atributo message ao redirect que exibirá uma mensagem de sucesso na página destino
        redirectAttributes.addFlashAttribute("message",
                "Jedi successfully registered!");

        return "redirect:jedi"; // redireciona o usuário para a página 'jedi'
    }

    @GetMapping("/jedi/{id}/delete")
    public String deleteJedi(@PathVariable("id") final Long id, RedirectAttributes redirectAttributes) {

        final Optional<Jedi> jedi = repository.findById(id); // recupera o jedi se houver

        repository.delete(jedi.get()); // deleta o jedi recuperado da base de dados

        // informa o sucesso da operação de remoção para o usuário
        redirectAttributes.addFlashAttribute("message",
                "Jedi successfully removed.");

        return "redirect:/jedi"; // redireciona a plicação de volta para a página 'jedi'
    }

    @GetMapping("/jedi/{id}/update")
    public String updateJedi(@PathVariable("id") final Long id, Model model) {

        final Optional<Jedi> jedi = repository.findById(id); // recupera o jedi se houver

        model.addAttribute("jedi", jedi.get()); // atribuí os dados do jedi a um modelo

        return "edit-jedi"; // redireciona a aplicação para a página 'edit-jedi'
    }

}
