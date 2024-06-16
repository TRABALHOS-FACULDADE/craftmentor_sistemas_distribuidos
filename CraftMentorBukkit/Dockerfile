FROM ubuntu:latest

COPY /BukkitFiles /mnt/CraftMentorBukkit/

RUN /bin/bash
RUN apt-get update

## Instalação do Java
RUN apt-get install -y default-jre
RUN java -version

## Execução do script que inicializa o servidor Bukkit
CMD ["bash", "/mnt/CraftMentorBukkit/start.sh"]

EXPOSE 8000
