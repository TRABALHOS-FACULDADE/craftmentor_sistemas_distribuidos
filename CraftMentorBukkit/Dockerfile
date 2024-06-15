FROM ubuntu:latest

COPY /BukkitFiles /mnt/CraftMentorBukkit/

RUN /bin/bash
RUN apt-get update
RUN apt-get install -y default-jre

RUN java -version

CMD ["bash", "/mnt/CraftMentorBukkit/start.sh"]

EXPOSE 8000
