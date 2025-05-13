nome_vm="vm-mottu-spot"

localizacao="brazilsouth"

grupo_recurso="rg-mottu-spot"

imagem="Ubuntu2404"

usuario="azureuser"

senha="Muk1nha2005!@"

echo "Criando grupo de recursos..."
az group create --name $grupo_recurso --location $localizacao

echo "Criando a máquina virtual..."
az vm create \
  --resource-group $grupo_recurso \
  --name $nome_vm \
  --image $imagem \
  --admin-username $usuario \
  --admin-password $senha

echo "Abrindo a porta 22 para SSH..."
az vm open-port --port 22 --resource-group $grupo_recurso --name $nome_vm
az vm open-port --port 8080 --resource-group $grupo_recurso --name $nome_vm

echo "Máquina virtual criada com sucesso!"