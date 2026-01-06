import axios from 'axios';
import keycloak from '../keycloak'; // asegúrate de la ruta correcta
// Asegúrate de que tu API de Spring Boot corre en el puerto 8080
const apiClient = axios.create({
  baseURL: 'http://192.168.39.36:30080/api',
  headers: {
    'Content-Type': 'application/json'
  }
});
// Interceptor para siempre usar token actualizado
apiClient.interceptors.request.use(
  async (config) => {
    if (keycloak?.token) {
      // Refresca el token si queda menos de 60 segundos
      await keycloak.updateToken(60);
      config.headers.Authorization = `Bearer ${keycloak.token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);
// Función que usará tu lista para obtener los datos
export const getAllCustomers = () => {
  return apiClient.get('/customers');
};

// Función que usará el formulario para crear nuevos clientes
export const createCustomer = (customerData) => {
  return apiClient.post('/customers', customerData);
};

// Función para obtener datos del DTO
export const getAllCustomersDTO = () => {
  return apiClient.get('/customers/dto');
};