package com.system.smartevents.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "/index";
    }


    @GetMapping("/cadastro-agencia")
    public String cadastroAgencia() {
        return "/cadastro-agencia";
    }
    

    @GetMapping("/cadastro-membro")
    public String cadastroMembro() {
        return "/cadastro-membro";
    }
   
    
    @GetMapping("/cadastro-evento")
    public String cadastroEvento() {
        return "/cadastro-evento";
    }
    

    @GetMapping("/cadastro-agendamento")
    public String cadastroAgendamento() {
        return "/cadastro-agendamento";
    }
    
    @GetMapping("/consulta-membro")
    public String consultaMembro() {
        return "/consulta-membro";
    }
    
    @GetMapping("/consulta-agencia")
    public String consultaAgencia() {
        return "/consulta-agencia";
    }
    
    
    
    @GetMapping("/consulta-evento")
    public String consultaEvento() {
        return "/consulta-evento";
    }
    

    @GetMapping("/consulta-agendamento")
    public String consultaAgendamento() {
        return "/consulta-agendamento";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("msgBemvindo","Seja Bem-Vindo ao SmartEvents");
        return "/login";
    }
    
    
  
}