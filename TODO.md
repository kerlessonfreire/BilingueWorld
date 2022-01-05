# Coisas a fazer - Chat

- Banco de Dados
  - Criar tabela de ``Mensagens`` com a seguinte estrutura:
  ```sql
    Mensagens(
     id, // integer
     nome, // string
     data, // string
     idRemetente, // integer
     nomeRemetente, // integer
     idNivel // integer
    )
    ```
- Java
  - Conexão com o Firebase:
    - Implementar a interface de serviço de chat ``ChatService`` em ``src/main/java/br/com/projeto/BelingueWorld/service``
    - Implementar a interface de serviço de usuário ``UsuarioService`` em ``src/main/java/br/com/projeto/BelingueWorld/service``
- Interface
  - Estilização do Chat (``src/main/resources/static/chat.css``)

## Observações
- Para chamar a rota do chat, é necessário o id do usuário logado (``http://locahost:8080/chat/{idUsuario}``)
- Chat funciona apenas de forma local (ver sobre ``hospedagem``)
- Funcionalidades do Chat:
  - Mandar e receber mensagens em um nível
  - Ler quando um usuário está digitando
  - Persistência dos dados (mensagens)
> Boa Sorte! :)