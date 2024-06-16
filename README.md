![CraftMentor](screenshots/craftmentor.png)

## Discentes

- Felipe Azevedo Ribeiro
- Guilherme Henriques Almeida

## Docente

- Edson Mota da Cruz

## O projeto

Consiste em um sistema que monitora o inventário dos jogadores dentro de um servidor de Minecrat (**Bukkit hospedado em um container Docker**) e, após <ins>10 segundos</ins>, um cálculo é feito para atualizar a pontuação de cada jogador com base nos tipos de itens coletados. Os blocos e suas respectivas pontuações podem ser visualizados [**aqui**](https://github.com/TRABALHOS-FACULDADE/craftmentor_sistemas_distribuidos/blob/main/CraftMentor/src/main/java/dev/feliperf/craftmentor/Presenter/Controllers/BlockRankingController.kt).

### Docker

No Docker, estão os seguintes containers:

- Servidor Bukkit (porta 8000)
- PostgreSQL (porta 5435)
- Adminer (porta 8082)

![](screenshots/1.png)

O Adminer é uma interface que permite interagir com o banco de dados do PostgreSQL e criar/deletar/alterar tabelas dentro dele.

### Backend Dart

Na porta 8001, estará rodando o backend em Dart na máquina local. Em [**seu código**](https://github.com/TRABALHOS-FACULDADE/craftmentor_sistemas_distribuidos/blob/main/craftmentor_backend/bin/craftmentor_backend.dart), será estabelecida a comunicação com o banco de dados do PostgreSQL (que estará rodando no container Docker).
