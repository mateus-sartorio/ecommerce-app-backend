<p align="center"><img align="center" width="280" src="./.github/logo-light.svg#gh-light-mode-only"/></p>
<p align="center">
  <img src="https://skillicons.dev/icons?i=java,spring,docker,postgresql" /> <br/>
  <a href="https://github.com/mateus-sartorio/ecommerce-app"><kbd>🔵 GitHub</kbd></a>
  <a href="https://github.com/mateus-sartorio/ecommerce-app"><kbd>🟢 Website</kbd></a>
</p>

# 🏢 Ecommerce App Backend

### Open your store online, no limits!

<br/>

## 🔥 Features

### 📲 Minimalistic interface

<div align="center">
  <img src="https://github.com/mateus-sartorio/ecommerce-app/blob/main/assets/images/pic1.png" alt="" width="30%"/>
  <img src="https://github.com/mateus-sartorio/ecommerce-app/blob/main/assets/images/pic2.png" alt="" width="30%"/>
</div>
<br/>


## 🍄 Our philosophy

This project is meant to be simple and easy to use. Features are kept minimal but polished.

<br/>

## ⚙️ Local setup

### Prerequisites:

- Node.js 16 (caso deseje rodar a aplicação nativamente)
- Docker

Antes de começar, certifique-se que tenha todos os pré-requisitos para rodar o projeto.

Para checar a versão do Node.js instalada em sua máquina, execute o seguinte comando:

```bash
node --version
```

Caso sua versão não seja a 16, recomenda-se utilizar o `nvm`, que permite instalar e gerenciar várias versões do Node.js em sua máquina ([Repositório com instruções para instalação](https://github.com/nvm-sh/nvm)).

Para o Docker, instruções de instalação para cada sistema operacional são encontradas em sua [documentação oficial](https://docs.docker.com/engine/install/).


### Running locally

Clone o repositório localmente, usando `--recuse-submodules`, para garantir que o os repositórios do frontend e do backend sejam clonados corretamente. Em seguida, entre no diretório do respositório clonado:

```bash
git clone https://github.com/mateus-sartorio/corenotes --recurse-submodules
cd corenotes
```

Além disso, é necessário criar um aquivo de variáveis de ambiente (`.env`) no diretório raíz do backend para configurar as URLs de conexão com o banco de dados:

```bash
cd corenotes-backend
touch .env
```

No arquivo criado, crie as seguintes variáveis ambientes (`MONGODB_ENDPOINT` é utilizada pela aplicação em funcionamento normal e `MONGODB_MOCK_ENDPOINT` para testes unitários):

```
MONGODB_ENDPOINT="mongodb://mongo:27017/corelab"
MONGODB_MOCK_ENDPOINT="mongodb://mongo:27017/corelab_mock"
```

Caso prefira utilizar o MongoDB na nuvem, obtenha uma string de conexão em [MongoDB Atlas](https://www.mongodb.com/atlas/database), e use a string de conexão para inicializar as variáveis de ambiente, no formato mostrado abaixo:

```
MONGODB_ENDPOINT="mongodb+srv://<username>:<password>@<cluster>.mongodb.net/corelab/?retryWrites=true&w=majority"
MONGODB_MOCK_ENDPOINT="mongodb+srv://<username>:<password>@<cluster>.mongodb.net/corelab_mock/?retryWrites=true&w=majority"
```

Neste caso, recomenda-se também remover o serviço `mongo` em `docker-compose.yml` encontrado na pasta raíz do projeto, uma vez que o banco de dados não precisa mais ser executado localmente.


#### Rodando com Docker

Caso prefira rodar a aplicação com Docker, basta inicializar o Docker Engine, e rodar o seguinte comando na pasta raíz do projeto:

```bash
docker compose up
```

Caso tudo tenha ocorrido conforme o esperado, o frontend deve estar rodando em `localhost:3000` e o backend em `localhost:4000`.


#### Rodando nativamente

Caso prefira rodar a aplicação nativamente, na pasta raíz do backend, instale as dependências do Node.js e depois inicialize o servidor, com os seguintes comandos:

```bash
npm install
npm start
```

Já na pasta raíz do frontend, as dependências também devem ser instaladas antes de inicializar o servidor:

```bash
npm install
npm run dev
```

Caso queira gerar uma build de produção para o frontend, execute em sua pasta raíz:

```bash
npm run build
```

O servidor com a build de produção pode ser inicializado com:

```bash
npm start
```

> [!TIP]
> Ao instalar dependências do Node.js e inicializar os servidores nativamente, sempre certifique-se de estar usando a versão 16 do Node.js
> 
> Para verificar qual versão do Node.js está usando, execute `node --version`
> 
> Caso esteja usando `nvm`, pode-se selectionar a versão 16 do Node.js com o comando `nvm use 16`
> 
> Com o `nvm` é possível também instalar a versão 16 do Node.js, caso ainda não esteja instalada, com o comando `nvm install 16`

<br/>

## ⚠️ Limitations

- O sistema ainda não possui um sistema de cadastro e login de usuários.


## 🐞 Known bugs

- A paleta de selecão de cores (ColorPicker) pode dar overflow na tela caso seja aberta no cartão de uma tarefa muito próxima à margem direita da tela.

<br/>

## ⚖️ License:

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see [https://www.gnu.org/licenses](https://www.gnu.org/licenses).

This program is released under license GNU GPL v3+ license.


## 🔧 Support:

Kindly report any issues with the application on its official repo on [github.com/mateus-sartorio/ecommerce-app](https://github.com/mateus-sartorio/ecommerce-app).
