package com.example.demo;

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

@Controller
public class AutorController {
	 @Autowired AutorService service;

	
    @GetMapping("/admin/create-autor")
    public String createAutorForm(Model model) {
    	
    	model.addAttribute("autors", service.getAutors());
        model.addAttribute("autor", new Autor());
        return "autor";
    }

    @PostMapping("/admin/save-autor")
    public String saveProjectSubmission(@ModelAttribute("autor") @Validated Autor autor ,BindingResult bindingResult) {
    	 if (bindingResult.hasErrors()) {
             return "result";
          }
    	 
    	if (autor!=null && autor.getId()!=null) {
    		try {
    			service.modify(autor);
    			System.out.println("autor modificado");
			} catch (Exception e) {
				System.out.println("error");
				 return "result";
			}
    		
    	}else {
    		service.add(autor);
    		System.out.println("nuevo guardado");
    	}
   
    	

        return "result";
    }
    
    @GetMapping("/edit/admin/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Autor autor = service.getAutorById(id);
        model.addAttribute("autors", service.getAutors());
        model.addAttribute("autor", autor);
        return "autor";
    }
    
    @GetMapping("/delete/admin/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
    	Autor autor = service.getAutorById(id);
    	service.delete(autor.getId());
        return "redirect:/create-autor";
    }
}