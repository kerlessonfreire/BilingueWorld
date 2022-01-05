package br.com.projeto.BelingueWorld.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.projeto.BelingueWorld.dto.LoginDTO;
import br.com.projeto.BelingueWorld.service.BelingueWorldServiceLogin;
@Controller
public class CadastroProfController {
		@Autowired // Permite que o Spring injete as dependências na classe do DentalSystemServicePaciente
	    private BelingueWorldServiceLogin service;
		
		@GetMapping("/cadastroProf")
		public String login(Model model) {
			model.addAttribute("usuario", new LoginDTO());
			return "professor/cadastroProf";
		}
		
		 @PostMapping("/cadastroProfadd")
		 public String greetingSubmit(@ModelAttribute LoginDTO usuario, Model model) {
		 service.add(usuario);
		 return "professor/prof";
		  }
		 
		 @GetMapping("/cadastroProfIdioma")
			public String idioma(Model model) {
				return "professor/perfil_prof";
			}
			
}