#!/bin/bash

# ============================================
# TOOLRENT - DEV LOOP
# Desarrollo rápido con hot reload
# ============================================

SERVICE=$1

if [ -z "$SERVICE" ]; then
    echo "❌ Uso: ./dev-loop.sh [servicio]"
    echo ""
    echo "Servicios disponibles:"
    echo "  - config-server"
    echo "  - eureka-server"
    echo "  - api-gateway"
    echo "  - ms-inventory"
    echo "  - ms-prestamos"
    exit 1
fi

GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m'

echo -e "${BLUE}═══════════════════════════════════════${NC}"
echo -e "${BLUE}🚀 DEV LOOP:  $SERVICE${NC}"
echo -e "${BLUE}═══════════════════════════════════════${NC}"

cd $SERVICE || exit 1

# Función para limpiar al salir
cleanup() {
    echo -e "\n${YELLOW}🧹 Limpiando port-forwards...${NC}"
    pkill -f "kubectl port-forward" 2>/dev/null
    exit 0
}

trap cleanup INT TERM

echo -e "${BLUE}1️⃣  Creando port-forwards de dependencias...${NC}"

# Port-forwards en background
kubectl port-forward service/mysql-service 3306:3306 > /dev/null 2>&1 &
kubectl port-forward service/eureka-server 8761:8761 > /dev/null 2>&1 &
kubectl port-forward service/config-server 8888:8888 > /dev/null 2>&1 &

sleep 3

echo -e "${BLUE}2️⃣  Iniciando aplicación en modo desarrollo...${NC}"
echo -e "${YELLOW}   📝 Los cambios se recargarán automáticamente${NC}"
echo -e "${YELLOW}   🛑 Presiona Ctrl+C para detener y deployar a K8s${NC}"
echo ""

# Variables de entorno para desarrollo local
export DB_HOST=localhost
export DB_PASSWORD=root123
export EUREKA_URL=http://localhost:8761/eureka/
export CONFIG_SERVER_URL=http://localhost:8888

# Ejecutar con Gradle (hot reload con Spring DevTools)
./gradlew bootRun --continuous

# Al salir (Ctrl+C), preguntar si deploy a K8s
echo ""
echo -e "${YELLOW}¿Deployar a Kubernetes? (y/n)${NC}"
read -r response

if [[ "$response" =~ ^[Yy]$ ]]; then
    echo -e "${BLUE}3️⃣  Building y deploying a Kubernetes...${NC}"
    ./deploy.sh
fi

cleanup
