package br.com.projeto.BelingueWorld.service;

import java.util.List;

import br.com.projeto.BelingueWorld.dto.UsuarioDTO;


public interface UsuarioService {
	List<UsuarioDTO> list();

	Boolean add(UsuarioDTO mensagem);

	Boolean edit(String id,UsuarioDTO mensagem);

	Boolean delete(String id);
}
