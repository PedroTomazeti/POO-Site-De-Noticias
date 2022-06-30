package br.uema.site.controller;

import br.uema.site.model.Admin;
import br.uema.site.model.Noticia;
import br.uema.site.repository.NoticiasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

// Notação usada para direcionar o fluxo da aplicação mapeando e direcionado as ações recebida pela camada da apresentação para os respectivos serviços da aplicação
@Controller
// Rota que vai ser vinculada a este controller
@RequestMapping("/main-noticias")
public class NoticiasController {
    @Autowired
    private NoticiasRepository repository;

    @GetMapping("/menu-noticias")
    // Model é a classe Entidade que será carregada na visão
    public String showNoticiasList(Model model) {
        //vai resgatar todos as informações da base de dados
        model.addAttribute("noticia", repository.findAll());
        // html que ele utilizará para decorar esses dados
        return "main-noticias/menu-noticias";
    }

    @GetMapping("/new-noticias")
    // Model é a classe Entidade que será carregada na visão
    public String showList(Model model) {
        //vai resgatar todos as informações da base de dados
        model.addAttribute("noticia", repository.findAll());
        // html que ele utilizará para decorar esses dados
        return "main-noticias/new-noticias";
    }

    @GetMapping("/add-noticias")
    public String showCreateNoticia(Noticia noticia, Model model){

        noticia = new Noticia();
        model.addAttribute("admin", noticia);
        return "/main-noticias/add-noticias";
    }

    @PostMapping("/cadastrar")
    public String addNoticia(@Valid Noticia noticia, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/main-noticias/add-noticias";
        }

        repository.save(noticia);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateNoticia(@PathVariable("id") long id, Model model) {
        Noticia noticia= repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
        model.addAttribute("noticia", noticia);
        return "/main-noticias/edit-noticias";
    }

    @PostMapping("/update/{id}")
    public String updateNoticia(@PathVariable("id") long id, @Valid Noticia noticia, BindingResult result, Model model){
        if(result.hasErrors()){
            noticia.setId(id);
            return "/main-noticias/edit-noticias";
        }

        repository.save(noticia);
        return "redirect:/main-noticias/menu-noticias";
    }

    @GetMapping("/delete/{id}")
    public String deleteNoticia(@PathVariable("id") long id, Model model) {
        Noticia noticia = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
        repository.delete(noticia);
        return "redirect:/main-noticias/menu-noticias";
    }

}
