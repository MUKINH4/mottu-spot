# MottuSpot

## Descrição

O objetivo deste projeto é organizar os pátios da empresa Mottu fornecendo rastreamento em tempo real das motos estacionadas. Para isso, propomos instalar um dispositivo de localização em cada moto. Esse dispositivo permite:

- Acionar um alarme sonoro (beep) para facilitar a localização da moto no pátio.
- Acender uma luz indicadora para sinalizar visualmente sua posição.

A API Spring Boot gerencia as operações de criação, listagem, atualização e remoção de pátios e motos, além de armazenar informações em um banco de dados relacional.

## Integrantes

- Samuel Heitor – RM 556731
- Lucas Nicolini – RM 557613
- Renan Olivi – RM 557680

## Instruções

### Build

1. Clone este repositório:
   ```bash
   git clone https://github.com/MUKINH4/mottu-spot.git
   cd mottu-spot
   ```
2. Gere o artefato JAR:
   ```bash
   ./mvnw clean package
   ```
   ou
   ```bash
   ./mvn clean package
   ```


### Executando com Docker

1. Construa a imagem:
   ```bash
   docker build -t mottu-spot-api/<nome_docker_hub> .
   ```
2. Dê push para o Docker local
    ```bash
    docker push mottu-spot-api/<nome_docker_hub>
    ```

### Criação da VM no Azure

1. Rode o script para criar a VM que está dentro da pasta scripts:

```bash
bash scripts/criar-vm.sh
```

- O script cria o resource group, VM Ubuntu, abre portas 22 e 8080 e exibe o IP público.

2. Envie o script *install_docker.sh* que está na pasta scripts para a VM:
```bash
scp scripts/install_docker.sh azureuser@<IP_PÚBLICO>:
```
- O IP deve ser o que foi dado depois de rodar o script de criação de VM.
- Pedirá uma senha depois de rodar, a senha é encontrada dentro do script *criar-vm.sh*

3. Logue na VM:
```bash
ssh azureuser@<IP_PÚBLICO>
```

4. Rode o script de instalação do Docker:
```bash
bash install_docker.sh
```

5. Pegue a imagem construida:
```bash
docker pull mottu-spot-api/<nome_docker_hub>
```
6. Rode o container em background
```bash
docker run -p 8080:8080 -d mottu-spot-api/<nome_docker_hub>
```

*Depois de rodar todos os comandos pegue o IP Público para testar os endpoints*

## Uso dos Endpoints


- `POST   /patio`            – Cria um novo pátio
- `GET    /patio`            – Lista pátios (com paginação)
- `GET    /patio/{id}`       – Busca pátio por ID
- `PUT    /patio/{id}`       – Atualiza pátio
- `DELETE /patio/{id}`       – Remove pátio

- `POST   /moto`             – Registra uma nova moto (associa a um pátio)
- `GET    /moto`             – Lista motos
- `GET    /moto/{id}`        – Busca moto por ID
- `GET    /moto/patio/{id}`  – Lista motos de um pátio
- `PUT    /moto/{id}`        – Atualiza moto
- `DELETE /moto/{id}`        – Remove moto

---

© 2025 MottuSpot