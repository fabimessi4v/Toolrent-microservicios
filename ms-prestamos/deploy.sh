#!/bin/bash
# deploy. sh - Automatiza build, push y deploy


SERVICE_NAME="ms-prestamos"
DOCKER_USER="fabimessidev"
IMAGE_TAG="latest"

# Colores para output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${BLUE}🔨 1. Construyendo imagen ${SERVICE_NAME}... ${NC}"
docker build -t $DOCKER_USER/$SERVICE_NAME:$IMAGE_TAG . 

if [ $? -ne 0 ]; then
    echo -e "${YELLOW}❌ Error al construir la imagen${NC}"
    exit 1
fi

echo -e "${BLUE}📤 2. Subiendo a Docker Hub...${NC}"
docker push $DOCKER_USER/$SERVICE_NAME:$IMAGE_TAG

if [ $? -ne 0 ]; then
    echo -e "${YELLOW}❌ Error al subir la imagen.  ¿Hiciste 'docker login'?${NC}"
    exit 1
fi

echo -e "${BLUE}🔄 3. Reiniciando deployment en Kubernetes...${NC}"
kubectl rollout restart deployment/$SERVICE_NAME

echo -e "${BLUE}⏳ 4. Esperando que el pod esté listo... ${NC}"
kubectl rollout status deployment/$SERVICE_NAME --timeout=120s

if [ $? -ne 0 ]; then
    echo -e "${YELLOW}⚠️  Timeout esperando el pod. Verifica manualmente.${NC}"
    kubectl get pods -l app=$SERVICE_NAME
    exit 1
fi

echo -e "${GREEN}📊 5. Estado actual:${NC}"
kubectl get pods -l app=$SERVICE_NAME

echo ""
echo -e "${GREEN}📝 6. Logs del nuevo pod (Ctrl+C para salir):${NC}"
sleep 2
kubectl logs -f deployment/$SERVICE_NAME

echo -e "${GREEN}✅ Deploy completado! ${NC}"
