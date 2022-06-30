package br.uema.site.controller;

import br.uema.site.model.Admin;
import br.uema.site.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Notação usada para direcionar o fluxo da aplicação mapeando e direcionado as ações recebida pela camada da apresentação para os respectivos serviços da aplicação
@Controller
// Rota que vai ser vinculada a este controller
@RequestMapping("/admin-login")
public class AdminController {
    @Autowired
    private AdminRepository repository;

    @GetMapping("/login")
    // Model é a classe Entidade que será carregada na visão
    public String showAdmList(Model model) {
        //vai resgatar todos as informações da base de dados
        model.addAttribute("admin", repository.findAll());
        // html que ele utilizará para decorar esses dados
        return "admin-login/login";
    }


    @GetMapping("/add-admin")
    public String showCreateForm(Admin admin, Model model){

        admin = new Admin();
        model.addAttribute("admin", admin);
        return "/admin-login/add-admin";
    }

    @PostMapping("/cadastrar")
    public String addAdmin(@Valid Admin admin, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/admin-login/add-admin";
        }

        repository.save(admin);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Admin admin = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
        model.addAttribute("admin", admin);
        return "/admin-login/update-admin";
    }

    @PostMapping("/update/{id}")
    public String updateAdmin(@PathVariable("id") long id, @Valid Admin admin, BindingResult result, Model model){
        if(result.hasErrors()){
            admin.setId(id);
            return "/admin-login/update-admin";
        }

        repository.save(admin);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable("id") long id, Model model) {
        Admin admin = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id: " + id));
        repository.delete(admin);
        return "redirect:/";
    }
}
