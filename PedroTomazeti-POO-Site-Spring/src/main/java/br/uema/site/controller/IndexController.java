/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.uema.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author pltf0
 */
// Notação usada para direcionar o fluxo da aplicação mapeando e direcionado as ações recebida pela camada da apresentação para os respectivos serviços da aplicação
@Controller
public class IndexController {
    // Rota que vai ser vinculada a este controller
    @GetMapping("/")
    public String main(){
        return "index";
    }
}
