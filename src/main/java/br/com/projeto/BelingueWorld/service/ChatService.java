package br.com.projeto.BelingueWorld.service;

import br.com.projeto.BelingueWorld.dto.MensagemDTO;

import java.util.List;

public interface ChatService {
	
	List<MensagemDTO> list();

	Boolean add(MensagemDTO mensagem);

	Boolean edit(String id,MensagemDTO mensagem);

	Boolean delete(String id);
}
