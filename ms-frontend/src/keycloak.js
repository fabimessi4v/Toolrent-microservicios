// src/keycloak.js
import Keycloak from 'keycloak-js';

// Configuración de Keycloak apuntando al NodePort
const keycloakConfig = {
  url: 'http://192.168.39.54:30081',  // NodePort de Keycloak
  realm: 'toolrent',
  clientId: 'toolrent-frontend',
};

// Crea una instancia de Keycloak
const keycloak = new Keycloak(keycloakConfig);

export default keycloak;