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
import com.example.demo.model.Usuario;
import com.example.demo.model.UsuarioService;

@Controller
public class UsuarioController {
	 @Autowired UsuarioService service;

	
    @GetMapping("/admin/create-usuario")
    public String createUsuarioForm(Model model) {
    	
    	model.addAttribute("usuarios", service.getUsuarios());
        model.addAttribute("usuario", new Usuario());
        return "usuario";
    }

    @PostMapping("/admin/save-usuario")
    public String saveProjectSubmissionUsuario(@ModelAttribute("usuario") @Validated Usuario usuario ,BindingResult bindingResult) {
    	 if (bindingResult.hasErrors()) {
             return "result";
          }
    	 
    	if (usuario!=null && usuario.getId()!=null) {
    		try {
    			service.modify(usuario);
    			System.out.println("usuario modificado");
			} catch (Exception e) {
				System.out.println("error");
				 return "result";
			}
    		
    	}else {
    		service.add(usuario);
    		System.out.println("nuevo guardado");
    	}
   
    	

    	return "redirect:/create-usuario";
    }
    
    @GetMapping("/edit/usuario/admin/{id}")
    public String showUpdateFormUsuario(@PathVariable("id") long id, Model model) {
        Usuario usuario = service.getUsuarioById(id);
        model.addAttribute("usuarios", service.getUsuarios());
        model.addAttribute("usuario", usuario);
        return "usuario";
    }
    
    @GetMapping("/delete/usuario/admin/{id}")
    public String deleteUserLibro(@PathVariable("id") long id, Model model) {
    	Usuario usuario = service.getUsuarioById(id);
    	service.delete(usuario.getId());
        return "redirect:/create-usuario";
    }
}