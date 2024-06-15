docker build -t craftmentor_bukkit .
docker run --name craftmentor_bukkit_server --add-host host.docker.internal:host-gateway -it -p 8000:8000 craftmentor_bukkit