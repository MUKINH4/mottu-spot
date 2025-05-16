GREEN="\033[0;32m"
NC="\033[0m"
YELLOW="\033[1;33m"

nome_vm="vm-mottu-spot"

localizacao="brazilsouth"

grupo_recurso="rg-mottu-spot"

imagem="Ubuntu2204"

usuario="azureuser"

echo "Criando grupo de recursos..."
az group create --name $grupo_recurso --location $localizacao

echo "Criando a máquina virtual..."
az vm create \
  --resource-group $grupo_recurso \
  --name $nome_vm \
  --image $imagem \
  --admin-username $usuario \
  --generate-ssh-keys \
  --public-ip-sku Standard

echo "Abrindo a porta 22 para SSH..."
az vm open-port --port 22 --resource-group $grupo_recurso --name $nome_vm --priority 1010

az vm open-port --port 80 --resource-group $grupo_recurso --name $nome_vm --priority 1020
az vm open-port --port 8080 --resource-group $grupo_recurso --name $nome_vm

echo "Máquina virtual criada com sucesso!"

echo -e "${GREEN}Obtendo o IP público da VM...${NC}"
public_ip=$(az vm show \
  --resource-group $grupo_recurso \
  --name $nome_vm \
  --show-details \
  --query publicIps \
  -o tsv)
echo -e "IP público da VM: ${YELLOW}$public_ip"