package br.com.projeto.BelingueWorld.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

import br.com.projeto.BelingueWorld.dto.MensagemDTO;
import br.com.projeto.BelingueWorld.firebase.FirebaseInitializer;

@Service
public class BelingueWorldServiceImplChat implements ChatService {

	@Autowired
    private FirebaseInitializer firebase;

	@Override
    public List<MensagemDTO> list() {
        List<MensagemDTO> response = new ArrayList<>();
        MensagemDTO post;

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
        try {
            for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
                post = doc.toObject(MensagemDTO.class);
                post.setId(doc.getId());
                response.add(post);
            }
            return response;
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public Boolean add(MensagemDTO post) {
        Map<String, Object> docData = getDocData(post);

        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document().create(docData);

        try {
            if(null != writeResultApiFuture.get()){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }


    @Override
    public Boolean edit(String id, MensagemDTO post) {
        Map<String, Object> docData = getDocData(post);
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).set(docData);
        try {
            if(null != writeResultApiFuture.get()){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean delete(String id) {
        ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).delete();
        try {
            if(null != writeResultApiFuture.get()){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    private CollectionReference getCollection() {
        return firebase.getFirestore().collection("Chat");
    }

    private Map<String, Object> getDocData(MensagemDTO msg) {
        Map<String, Object> docData = new HashMap<>();
        docData.put("conteudo", msg.getConteudo());
        docData.put("data", msg.getData());
        docData.put("idNivel", msg.getIdNivel());
        docData.put("idRemetente", msg.getIdRemetente());
        docData.put("nomeRemetente", msg.getNomeRemetente());
        
        return docData;
    }
}