package br.com.projeto.BelingueWorld.service;

import java.util.List;

import br.com.projeto.BelingueWorld.dto.LoginDTO;

public  interface BelingueWorldServiceLogin {
	List<LoginDTO> list();

	Boolean add(LoginDTO login);

	Boolean edit(String id,LoginDTO login);

	Boolean delete(String id);

}
