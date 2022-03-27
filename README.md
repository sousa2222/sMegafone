# sMegafone
Um simples plugin de megafone com acesso a vários recursos interessantes:

- Mensagem totalmente configurável;
- Sons ao anunciar configurável;
- Title ao anunciar configurável;
- Sistema de cooldown;
- Preço para utilizar o comando.

### Pré-requisitos
* Java 8+
* [Spigot 1.8.8](https://cdn.getbukkit.org/spigot/spigot-1.8.8-R0.1-SNAPSHOT-latest.jar)

### Configuração padrão
```yml
#          _____                       _____
#  ______ /     \   ____   _________ _/ ____\____   ____   ____
# /  ___//  \ /  \_/ __ \ / ___\__  \\   __\/  _ \ /    \_/ __ \
# \___ \/    Y    \  ___// /_/  > __ \|  | (  <_> )   |  \  ___/
#/____  >____|__  /\___  >___  (____  /__|  \____/|___|  /\___  >
#     \/        \/     \/_____/     \/                 \/     \/
#                       Versão: 1.1.0
#                Autor: sousa222 (@sousa222#6969)

# Cooldown em segundos
cooldown: 60

# Quantia em dinheiro que o jogador irá pagar para usar o comando (/megafone)
quantia: 500

#Som que será reproduzido para todos os jogadores ao receber um anuncio
# Sons disponíveis da 1.8: http://docs.codelanx.com/Bukkit/1.8/org/bukkit/Sound.html
som_anuncio: ORB_PICKUP

# Mensagem que aparece na tela dos jogadores quando alguem utiliza o megafone;
#Linha1 - título; Linha2 - Subtitulo; placeholders: %jogador% retorna o nome do utilizador do megafone
title:
  linha1: "&a%player%"
  linha2: "&futilizou o megafone!"
# %jogador% - retorna o nome do player que executou o comando
# %anuncio% - retorna o anuncio que o jogador inseriu

anuncio:
  - ""
  - "&e&lMEGAFONE"
  - ""
  - "&fJogador: &7%jogador%"
  - "&fAnuncio: &7%anuncio%"
  - ""

#Mensagens
mensagem_reload: "&fsMegafone &afoi recarregado com sucesso!"
sem_money: "&c&lERRO &cVocê percisa de %quantia%"
erro_sem_argumentos: "&cDesculpe, mas você tem que escrever uma mensagem para anunciar!"
erro_sem_permissao: "&cVocê não tem permissão para executar esse comando."
erro_console: "Você não pode executar esse comando pelo console!"
```
### Comando
<table>
 <tr>
  <th>Comando</th>
  <th>Permissão</th>
  <th>Descrição</th>
 </tr>
 <tr>
  <td>/megafone</td>
  <td>smegafone.usar</td>
  <td>Usar o megafone.</td>
 </tr>
 <tr>
  <td>/smegafone</td>
  <td>smegafone.admin</td>
  <td>Administra o plugin.</td>
 </tr>
</table>

## Permissões adicionais

- Permissão para ignorar o cooldown: `smegafone.bypass`

## Download

- Clique [**aqui**](https://github.com/diogox2705/sMegafone/releases/) para baixar o plugin diretamente.

## Dependências
- [Vault](https://github.com/MilkBowl/VaultAPI) 
<table>
  <tr>
    <td align="center"><a href="https://github.com/diogox2705">
<img src="https://avatars.githubusercontent.com/u/59032835?s=400&u=2f044498e72bb88cae24c81891f4248e15c6b9e9&v=4" width="100px;" alt=""/><br /><sub><b>Sousa</b></sub></a><br /><a href="https://github.com/diogox2705/sMegafone/commits?author=diogox2705" title="Code">💻</a></td>
  </tr>
</table>
