package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Autor;
import com.example.demo.model.AutorService;
import com.example.demo.model.Usuario;

@Controller
public class ReportController {
	 @Autowired AutorService service;

	
    @GetMapping("/empleado/create-autor")
    public String createAutorForm(Model model) {
    	
    	model.addAttribute("autors", service.getAutors());
        model.addAttribute("autor", new Autor());
        return "report";
    }


    
 
    
    @GetMapping("/search/usuario/empleado/{id}")
    public String showUpdateFormUsuario(@PathVariable("id") long id, Model model) {
    	 List<Autor> autor = service.getAutorBycedula(String.valueOf(id));
         if(autor!=null && !autor.isEmpty()) {
     		System.out.println("tiene informacion");
     		model.addAttribute("autors", autor);
     	}else {
     		System.out.println("NO tiene informacion");
     	}
         model.addAttribute("autor", new Autor());
        return "report";
    }
    
}