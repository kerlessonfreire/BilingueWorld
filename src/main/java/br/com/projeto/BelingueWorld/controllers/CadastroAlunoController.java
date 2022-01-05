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
public class CadastroAlunoController {
		@Autowired // 
	    private BelingueWorldServiceLogin service;
		
		@GetMapping("/cadastroAlunoinicio")
		public String login(Model model) {
			model.addAttribute("usuario", new LoginDTO());
			return "cadastro/tela_cadastro";
		}
		
		 @PostMapping("/cadastroAlunoadd")
		 public String greetingSubmit(@ModelAttribute LoginDTO usuario, Model model) {
		 service.add(usuario);
		 return "aluno/aluno_inicio";
		  }
		 
		 @GetMapping("/cadastroAlunoIdioma")
			public String idioma(Model model) {
				return "aluno/perfil_aluno";
			}
}