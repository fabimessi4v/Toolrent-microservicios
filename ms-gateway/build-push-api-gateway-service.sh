#!/bin/bash

# ============================================
# TOOLRENT API-GATEWAY-SERVICE - BUILD & PUSH
# ============================================

GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m'

# ⚠️ CONFIGURACIÓN
DOCKER_USERNAME="fabimessidev"
DOCKER_REPO="api-gateway-service"

echo -e "${BLUE}⚡ BUILD & PUSH - API-GATEWAY-SERVICE${NC}"

# Verificar directorio
if [ ! -f "pom.xml" ] && [ ! -f "build.gradle" ]; then
    echo -e "${RED}❌ Ejecuta desde el directorio api-gateway-service/${NC}"
    exit 1
fi

# Generar tag con timestamp
TIMESTAMP=$(date +%Y%m%d-%H%M%S)
IMAGE_TAG="${DOCKER_USERNAME}/${DOCKER_REPO}:${TIMESTAMP}"
IMAGE_LATEST="${DOCKER_USERNAME}/${DOCKER_REPO}:latest"

# 1. Build de la app (Maven/Gradle)
if [ -f "pom.xml" ]; then
    echo -e "${BLUE}1/4 Compilando con Maven...${NC}"
    mvn clean package -DskipTests || { echo -e "${RED}❌ Build falló${NC}"; exit 1; }
    echo -e "${GREEN}   ✅ Build exitoso${NC}"
elif [ -f "build.gradle" ]; then
    echo -e "${BLUE}1/4 Compilando con Gradle...${NC}"
    ./gradlew build -x test || { echo -e "${RED}❌ Build falló${NC}"; exit 1; }
    echo -e "${GREEN}   ✅ Build exitoso${NC}"
fi

# 2. Docker build
echo -e "${BLUE}2/4 Creando imagen Docker...${NC}"
docker build -t ${IMAGE_TAG} -t ${IMAGE_LATEST} . -q || { echo -e "${RED}❌ Docker build falló${NC}"; exit 1; }
echo -e "${GREEN}   ✅ Imagen creada${NC}"

# 3. Login (si es necesario)
echo -e "${BLUE}3/4 Verificando Docker Hub...${NC}"
if ! docker info 2>/dev/null | grep -q "Username: ${DOCKER_USERNAME}"; then
    echo -e "${YELLOW}   Iniciando sesión...${NC}"
    docker login || { echo -e "${RED}❌ Login falló${NC}"; exit 1; }
fi
echo -e "${GREEN}   ✅ Autenticado${NC}"

# 4. Push
echo -e "${BLUE}4/4 Subiendo a Docker Hub...${NC}"
docker push ${IMAGE_TAG} || { echo -e "${RED}❌ Push falló${NC}"; exit 1; }
docker push ${IMAGE_LATEST} || { echo -e "${RED}❌ Push latest falló${NC}"; exit 1; }

echo ""
echo -e "${GREEN}═══════════════════════════════════════${NC}"
echo -e "${GREEN}✅ IMAGEN SUBIDA!${NC}"
echo -e "${GREEN}═══════════════════════════════════════${NC}"
echo -e "${GREEN}📦 ${IMAGE_TAG}${NC}"
echo -e "${GREEN}📦 ${IMAGE_LATEST}${NC}"
echo -e "${GREEN}═══════════════════════════════════════${NC}"
echo ""
echo -e "${YELLOW}Para deployar en K8s:${NC}"
echo -e "  kubectl set image deployment/api-gateway api-gateway=${IMAGE_TAG}"
echo -e "  kubectl rollout restart deployment/api-gateway"
echo ""
