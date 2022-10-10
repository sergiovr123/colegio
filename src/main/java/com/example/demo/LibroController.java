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

import com.example.demo.model.Libro;
import com.example.demo.model.LibroService;

@Controller
public class LibroController {
	 @Autowired LibroService service;

	
    @GetMapping("/admin/create-libro")
    public String createLibroForm(Model model) {
    	
    	model.addAttribute("libros", service.getLibros());
        model.addAttribute("libro", new Libro());
        return "libro";
    }

    @PostMapping("/admin/save-libro")
    public String saveProjectSubmissionLibro(@ModelAttribute("libro") @Validated Libro libro ,BindingResult bindingResult) {
    	 if (bindingResult.hasErrors()) {
             return "result";
          }
    	 
    	if (libro!=null && libro.getId()!=null) {
    		try {
    			service.modify(libro);
    			System.out.println("libro modificado");
			} catch (Exception e) {
				System.out.println("error");
				 return "result";
			}
    		
    	}else {
    		service.add(libro);
    		System.out.println("nuevo guardado");
    	}
   
    	

    	return "redirect:/create-libro";
    }
    
    @GetMapping("/edit/libro/admin/{id}")
    public String showUpdateFormLibro(@PathVariable("id") long id, Model model) {
        Libro libro = service.getLibroById(id);
        model.addAttribute("libros", service.getLibros());
        model.addAttribute("libro", libro);
        return "libro";
    }
    
    @GetMapping("/delete/libro/admin/{id}")
    public String deleteUserLibro(@PathVariable("id") long id, Model model) {
    	Libro libro = service.getLibroById(id);
    	service.delete(libro.getId());
        return "redirect:/create-libro";
    }
}