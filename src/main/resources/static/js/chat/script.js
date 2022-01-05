const ROTA_PADRAO = "http://localhost:8080"
const ROTA_CHAT = `${ROTA_PADRAO}/chat`

function MensagemComponente({ id, conteudo, nomeRemetente, data, eMinhaMensagem }){
  if(eMinhaMensagem){
    return `
    <div data-id=${id} class="mensagem minha-mensagem">
    <label class="nome">Eu</label>
    <p class="conteudo">${conteudo}</p>
    <p class="data">${data}</p>
    </div>
    `
  }else{
    return `
    <div data-id="${id}" class="mensagem outro-mensagem">
    <label class="nome">${nomeRemetente}</label>
    <p class="conteudo">${conteudo}</p>
    <p class="data">${data}</p>
    </div>
    `
  }
}

function DigitandoComponente({ nome }){
  return `
  <label class="estao-digitando">
    ${nome} está digitando
    <div class="ponto a">
      .
    </div>
    <div class="ponto b">
      .
    </div>
    <div class="ponto c">
      .
    </div>
  </label>
  `
}

function Chat(){
  const chat = document.querySelector('.chat')
  const divNiveis = document.querySelector('.niveis')
  const divNivel = document.querySelectorAll('.nivel')
  const botaoFecharChat = document.querySelector('.fechar-chat')
  const botaoEnviarMensagem = document.querySelector('.enviar-mensagem')
  const inputCampoTexto = document.querySelector('.campo-texto')
  const divMensagens = document.querySelector('.mensagens')
  const divDigitando = document.querySelector('.estao-digitando-container')
  const labelNomeChat = document.querySelector('.chat .cabecalho label')

  const meuId = new Number(document.body.dataset.meuId)
  const meuNome = document.body.dataset.meuNome
  let idNivel

  const sock = new SockJS(ROTA_CHAT)
  const stompClient = Stomp.over(sock);
  let tempoDigitando

  function iniciar(){
    iniciarConexaoChat()
    adicionarEventos()
  }

  function adicionarEventos(){
    inputCampoTexto.addEventListener('keyup', (evento) => {
      if(evento.key === 'Enter' || evento.keyCode === 13){
        enviarMensagem()
      }
    })
    botaoFecharChat.addEventListener('click', fecharChat)
    botaoEnviarMensagem.addEventListener('click', enviarMensagem)
    divNivel.forEach(elm => elm.addEventListener('click', abrirNivel))
    inputCampoTexto.addEventListener('keyup', enviarQuemEstaDigitando)

  }

  function enviarQuemEstaDigitando(){
    stompClient.send(
      `/app/chat/estao-digitando/${idNivel}`,
      {},
      JSON.stringify(meuNome)
    );
  }

  async function carregarMensagens(id, idNivel){
    // Buscar mensagens do backend por id e id do contato clicado
    const { data: mensagens } = await fetch(`${ROTA_CHAT}/${idNivel}/listar-mensagens`)
      .then(resp => resp.json())
    if(!mensagens) return []
    return []
  }

  async function abrirNivel(evento){
    const { idNivel: id, nome } = evento.target.dataset

    if(idNivel){
      stompClient.unsubscribe(`/queue/public/${idNivel}`, {}, () => {})
      stompClient.unsubscribe(`/queue/public/${idNivel}/estao-digitando`, {}, () => {})
    }

    idNivel = id
    labelNomeChat.innerHTML = `Chat do ${nome}`

    stompClient.subscribe(`/queue/public/${idNivel}`, ({ body }) => {
      const mensagem = JSON.parse(body)
      if(meuId == mensagem.idRemetente) return
      adicionarMensagem(mensagem)
    })

    stompClient.subscribe(`/queue/public/${idNivel}/estao-digitando`, ({ body }) => {
      const nome = JSON.parse(body)
      if(meuNome === nome) return
      mostrarDigitando(nome)
    })

    const mensagens = await carregarMensagens(meuId, idNivel)
    mensagens.forEach(mensagem => {
      adicionarMensagem(mensagem)
    })
    abrirChat()
  }

  function iniciarConexaoChat(){
    stompClient.connect({}, () => {
      console.log(`Usuário ${meuId} Conectado!`)
      limparChat()
    }, () => {
      console.log('Erro ao conectar ao chat.')
    })
  }

  function enviarMensagem(){
    const conteudo = inputCampoTexto.value
    if(conteudo === "") return
    if(!idNivel) return

    const mensagem = {
      id: undefined,
      conteudo,
      data: new Date().toLocaleDateString("pt-br", {
        day: "2-digit",
        month: '2-digit',
        year: '2-digit'
      }),
      nomeRemetente: meuNome,
      idRemetente: meuId,
      idNivel
    }

    adicionarMensagem(mensagem)

    stompClient.send(
      `/app/chat(idNivel=${idNivel})`,
      {},
      JSON.stringify(mensagem)
    );

    limparCampo()
  }

  function limparCampo(){
    inputCampoTexto.value = ""
  }

  function limparChat(){
    limparCampo()
    divMensagens.innerHTML = `<label>Conectado como ${meuNome}. </label>`
  }

  function fecharChat(){
    limparChat()
    chat.classList.add('oculto')
    divNiveis.classList.remove('oculto')
  }

  function abrirChat(){
    chat.classList.remove('oculto')
    divNiveis.classList.add('oculto')
  }

  function mostrarDigitando(nome){
    if(divDigitando.classList.contains('oculto')){
      divDigitando.innerHTML = DigitandoComponente({ nome })
      divDigitando.classList.remove('oculto')
    }

    if(tempoDigitando){
      clearTimeout(tempoDigitando)
    }

    tempoDigitando = setTimeout(() => {
      divDigitando.classList.add('oculto')
      clearTimeout(tempoDigitando)
    }, 3000)
  }

  function adicionarMensagem(mensagem){
    const { id , nomeRemetente, conteudo, data, idRemetente } = mensagem

    const eMinhaMensagem = idRemetente === meuId

    divMensagens.innerHTML += MensagemComponente({
      id,
      conteudo,
      nomeRemetente,
      data,
      eMinhaMensagem
    })
  }

  return {
    iniciar
  }
}

const chat = Chat()
chat.iniciar()