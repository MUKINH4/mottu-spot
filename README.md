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

## Instruções para rodar sem Docker

Se não precisa do docker e quer apenas testar localmente, basta apenas seguir essas instruções:

1. Clonar o repositório e entrar na raiz do projeto:
```bash
git clone https://github.com/MUKINH4/mottu-spot.git
cd mottu-spot
```
2. Rodar o seguindo comando:
```bash
mvnw spring-boot:run
```
ou
```bash
mvn spring-boot:run
```

## Instruções para Rodar com Docker dentro da VM

### Build

1. Clone este repositório:
   ```bash
   git clone https://github.com/MUKINH4/mottu-spot.git
   cd mottu-spot
   ```
2. Gere o artefato JAR:
   ```bash
   mvnw clean package
   ```
   ou
   ```bash
   mvn clean package
   ```


### Executando com Docker

1. Construa a imagem:
   ```bash
   docker build -t <nome_docker_hub>/backend .
   ```
2. Dê push para o Docker local
    ```bash
    docker push <nome_docker_hub>/backend
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
docker pull <nome_docker_hub>/backend
```
6. Rode o container em background
```bash
docker run -p 8080:8080 -d <nome_docker_hub>/backend
```

*Depois de rodar todos os comandos pegue o IP Público para testar os endpoints*

## Uso dos Endpoints

### Endpoints para o pátio
- `POST   /patio` – Cria um novo pátio
```bash
curl localhost:8080/patio
```
Exemplo de uso:
```javascript
{
   "nome": "Pátio 6",
   "logradouro": "Rua dos Alienados",
   "numero": 32,
   "cidade": "Itaquaquecetuba",
   "bairro": "Bairro dos baianos",
   "estado": "SE", 
   "pais": "Brasil",
   "cep": "12312-321"
}
```
- `GET    /patio`            – Lista pátios (com paginação)

Exemplo de uso:
```bash
curl localhost:8080/patio
```
Exemplo de resposta:
```javascript
"content": [
   {
      "id": 1,
      "nome": "Patio 1",
      "endereco": {
            "id": 1,
            "cep": "12345-678",
            "logradouro": "Rua Principal",
            "numero": 100,
            "bairro": "Centro",
            "cidade": "São Paulo",
            "estado": "SP",
            "pais": "Brasil"
      },
      "dataAdicao": "2025-05-23T08:25:15.539662",
      "motos": [
            {
               "id": 1,
               "placa": "ABC1234",
               "descricao": "Nova",
               "status": "ATIVO",
               "dataAdicao": "2025-05-23T08:25:15.542662",
               "dispositivo": {
                  "id": "b85785d0-c587-4dc8-b768-0e5fcb7fe3a3",
                  "ativo": false
               }
            }
         ]
         {
            "id": 2,
            "nome": "Patio 2",
            "endereco": {
                "id": 2,
                "cep": "32813-343",
                "logradouro": "Rua Secundaria",
                "numero": 2,
                "bairro": "Jabaquara",
                "cidade": "São Paulo",
                "estado": "SP",
                "pais": "Brasil"
            },
            "dataAdicao": "2025-05-23T08:25:15.539662",
            "motos": []
        }
   }
]
```
- `GET    /patio/{id}`       – Busca pátio por ID

Exemplo de uso:
```bash
curl localhost:8080/patio/1
```
Resposta

```javascript
{
    "id": 1,
    "nome": "Patio 1",
    "endereco": {
        "id": 1,
        "cep": "12345-678",
        "logradouro": "Rua Principal",
        "numero": 100,
        "bairro": "Centro",
        "cidade": "São Paulo",
        "estado": "SP",
        "pais": "Brasil"
    },
    "dataAdicao": "2025-05-23T08:25:15.539662",
    "motos": [
        {
            "id": 1,
            "placa": "ABC1234",
            "descricao": "Nova",
            "status": "ATIVO",
            "dataAdicao": "2025-05-23T08:25:15.542662",
            "dispositivo": {
                "id": "b85785d0-c587-4dc8-b768-0e5fcb7fe3a3",
                "ativo": false
            }
        },
        {
            "id": 2,
            "placa": "XYZ5678",
            "descricao": "Usada",
            "status": "INATIVO",
            "dataAdicao": "2025-05-23T08:25:15.542662",
            "dispositivo": {
                "id": "35dab0e5-2dfe-4f74-aab4-b9250ec80106",
                "ativo": false
            }
        }
    ]
}
```
- `PUT    /patio/{id}`       – Atualiza pátio
Exemplo de uso:
```bash
curl localhost:8080/patio/1
```
Alterar os dados que deseja no corpo da requisição:
```javascript
{
   "nome": "Pátio 10",
   "logradouro": "Rua Batista",
   "numero": 10,
   "cidade": "Itaquaquecetuba",
   "bairro": "Bairro dos baianos",
   "estado": "SE", 
   "pais": "Brasil",
   "cep": "12312-321"
}
```
- `DELETE /patio/{id}`       – Remove pátio
```bash
curl localhost:8080/patio/1
```
Isso deve retornar um HTTP Status 204

### Endpoints para a moto
- `POST   /moto`             – Registra uma nova moto (associa a um pátio)
```bash
curl localhost:8080/moto
```
Exemplo de uso:
```javascript
{
   "placa": "ACS1S23",
   "descricao": "Boa para uso",
   "status": "ATIVO",
   "patioId": 1   
}
```
- `GET    /moto`             – Lista motos
```bash
curl localhost:8080/moto
``` 
Exemplo de resposta:
```javascript
{
   "content": [
        {
            "id": 1,
            "placa": "ABC1234",
            "descricao": "Nova",
            "status": "ATIVO",
            "patioId": 1,
            "dispositivo": {
                "id": "b85785d0-c587-4dc8-b768-0e5fcb7fe3a3",
                "ativo": false
            }
        },
        {
            "id": 2,
            "placa": "XYZ5678",
            "descricao": "Usada",
            "status": "INATIVO",
            "patioId": 1,
            "dispositivo": {
                "id": "35dab0e5-2dfe-4f74-aab4-b9250ec80106",
                "ativo": false
            }
        }
   ]
}
```
- `GET    /moto/{id}`        – Busca moto por ID

Exemplo de uso:
```bash
curl localhost:8080/moto/1
```
Exemplo de resposta:
```javascript
{
   "id": 1,
    "placa": "ABC1234",
    "descricao": "Nova",
    "status": "ATIVO",
    "dataAdicao": "2025-05-23T08:25:15.542662",
    "dispositivo": {
        "id": "b85785d0-c587-4dc8-b768-0e5fcb7fe3a3",
        "ativo": false
    }
}
```
- `GET    /moto/patio/{id}`  – Lista motos de um pátio
```bash
curl localhost:8080/moto/patio/1
```
Exemplo de resposta:
```javascript
{
   "content": [
        {
            "id": 1,
            "placa": "ABC1234",
            "descricao": "Nova",
            "status": "ATIVO",
            "dataAdicao": "2025-05-23T09:11:25.25145",
            "dispositivo": {
                "id": "154c2fb9-2cf8-4654-b754-3f35958218b5",
                "ativo": false
            }
        },
        {
            "id": 2,
            "placa": "XYZ5678",
            "descricao": "Usada",
            "status": "INATIVO",
            "dataAdicao": "2025-05-23T09:11:25.25145",
            "dispositivo": {
                "id": "47e3ca4a-08f7-46bc-845f-f830ad1caedc",
                "ativo": false
            }
        }
    ]
}
```
- `PUT    /moto/{id}`        – Atualiza moto
```bash
curl localhost:8080/moto/1
```
Alterar os dados que deseja no corpo da requisição:
```javascript
{
   "placa": "LSK6Z12",
   "descricao": "Não está funcionando",
   "status": "INATIVO",
   "patioId": 1
}
```
- `DELETE /moto/{id}`        – Remove moto
```bash
curl localhost:8080/moto/1
```
Isso deve retornar um HTTP Status 204

---

© 2025 MottuSpot
