package br.com.projeto.BelingueWorld.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.BelingueWorld.dto.LoginDTO;
import br.com.projeto.BelingueWorld.dto.alunoDTO;
import br.com.projeto.BelingueWorld.service.BelingueWorldServiceLogin;
@Controller
public class LoginController {
		@Autowired // Permite que o Spring injete as dependências na classe do DentalSystemServicePaciente
	    private BelingueWorldServiceLogin service;
		
		@GetMapping("/inicio")
		public String login(Model model) {
			model.addAttribute("usuario", new LoginDTO());
			return "cadastro/tela_login";
		  }
		
		@GetMapping("/regra")
		public String regra(){
			return "Regra";
		}

		@GetMapping("/sobre")
		public String sobre(){
			return "sobre";
		}

		@GetMapping("/boletimaluno")
		public ModelAndView mostrarBoletim(){
			ModelAndView modelo = new ModelAndView("aluno/boletim");

			//lógica para buscar objeto do aluno e resgatar suas notas

			return modelo;
		}

		@GetMapping("/boletim")
		public ModelAndView boletim(){
			ModelAndView modelo = new ModelAndView("professor/boletim");

			//passar como objeto, um ArrayList da classe de alunos, e usar um th:each na view
			//modelo.addObject("alunos",alunos);

			return modelo;
		}

		@PostMapping("/boletim")
		public ModelAndView atualizarNotas(String dados){
			//manda pra uma página de teste para ver se não há problema
			ModelAndView modelo = new ModelAndView("boletim");

			//cria uma instância da biblioteca Gson
			Gson gson = new Gson();

			//cria uma ArrayList da classe de alunos (eu criei só pra testar)
			ArrayList<alunoDTO> notas = new ArrayList<>();
			//cria uma string q vai receber o objeto Json para fazer a conversão para a classe
			String objetoJson = "";

			//um for que percorre a String com os objetos Json
			for(int i = 0;i<dados.length();i++){

				//o que diferencia um objeto de outro é a separação por ';'
				if(dados.charAt(i) != ';'){

					//anexa ao objetoJson até antes do ;
					objetoJson += dados.charAt(i);
				}else{

					//salva na ArrayList o objeto de alunoDTO instanciado a partir dos dados do json
					notas.add(gson.fromJson(objetoJson, alunoDTO.class));
					//zera a String para receber o próximo objeto
					objetoJson = "";
				}
			}


			//adiciona a lista à página de teste
			modelo.addObject("alunos", notas);

			return modelo;
		}
}