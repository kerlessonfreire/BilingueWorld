package br.com.projeto.BelingueWorld.controllers;

import br.com.projeto.BelingueWorld.dto.MensagemDTO;
import br.com.projeto.BelingueWorld.dto.UsuarioDTO;
import br.com.projeto.BelingueWorld.service.ChatService;
import br.com.projeto.BelingueWorld.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@CrossOrigin(origins = { "http://localhost:8081", "http://localhost:8080" })
public class ChatController {

	private int idMsg=0;
	private int idU=0;
    @Autowired
    private SimpMessageSendingOperations template;

    @Autowired
    private ChatService chatService;

    @Autowired
    private UsuarioService usuarioService;

    @MessageMapping("/chat/{idNivel}") // Enviar mensagens para todos os usuários cadastrados nesse tópico
    public void enviarMensagem(@DestinationVariable("idNivel") String idNivel, @Payload MensagemDTO mensagem){
    	chatService.add(mensagem);
        template.convertAndSend("/queue/public/" + idNivel, mensagem);
    }

    @MessageMapping("/chat/estao-digitando/{idNivel}") // Enviar o nome do usuário que está digitando para todos os usuários cadastrados nesse tópico
    public void estaoDigitando(@DestinationVariable("idNivel") String idNivel, @Payload String nome){
        template.convertAndSend("/queue/public/" + idNivel + "/estao-digitando", nome);
    } 
   
    @GetMapping("/chat/{idNivel}/listar-mensagens") // Buscar mensagens por nível
    public List<MensagemDTO> listarMensagens(@PathVariable ("idNivel") String idNivel){
    	idMsg=buscarID(idNivel);
    	 List<MensagemDTO> listMsgId = new ArrayList<>();
    	 MensagemDTO msg = null;
    	 msg.setId(chatService.list().get(idMsg).getId());
    	 msg.setConteudo(chatService.list().get(idMsg).getConteudo());
    	 msg.setData(chatService.list().get(idMsg).getData());
    	 msg.setIdNivel(chatService.list().get(idMsg).getIdNivel());
    	 msg.setIdRemetente(chatService.list().get(idMsg).getIdRemetente());
    	 msg.setNomeRemetente(chatService.list().get(idMsg).getNomeRemetente());
    	 listMsgId.add(msg);
    	 return listMsgId;
    }
    

    @GetMapping("/chat") // Retorna a página do Chat (Requer o id do usuário)
    public ModelAndView retornarPagina(){
        ModelAndView mv = new ModelAndView("chat/chat");
        idU= buscarIDUsuario("1");
        UsuarioDTO usuario = usuarioService.list().get(idU);
        mv.addObject("usuario", usuario);
        return mv;
    }
    
    public int buscarID(String id) {
    	boolean verificar;
    	int achou=0;
    	for(int i=0; i<chatService.list().size(); i++) {
    		verificar = chatService.list().get(i).getId().contains(id);
    		if(verificar==true) {
    			achou=i;
    		}
    	}
    	return achou;
    }
    
    public int buscarIDUsuario(String id) {
    	boolean verificar;
    	int achou=0;
    	for(int i=0; i<usuarioService.list().size(); i++) {
    		verificar = usuarioService.list().get(i).getId().contains(id);
    		if(verificar==true) {
    			achou=i;
    		}
    	}
    	return achou;
    }
}
