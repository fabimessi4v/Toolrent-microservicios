package com.proyectogradle.msprestamos.Service;

import com.proyectogradle.msprestamos.DTO.CustomerDTO;
import org.springframework.stereotype.Service;

@Service
public class CustomerIntegrationService {

    /**
     * MOCK: Simula la llamada al microservicio de clientes.
     * No usa RestTemplate, devuelve datos fijos para pruebas.
     */
    public CustomerDTO getCustomerById(String id) {

        // ESCENARIO 1: Cliente con multas (Para probar la excepción de "Multas impagas")
        if ("CLIENTE_DEUDOR".equals(id)) {
            return new CustomerDTO(id, "Juan Moroso", 1); // 1 = Tiene multas
        }

        // ESCENARIO 2: Cliente que no existe (Para probar validación de "Cliente no encontrado")
        if ("CLIENTE_FANTASMA".equals(id)) {
            return null;
        }

        // ESCENARIO 3: Cliente Feliz (Caso de éxito por defecto)
        // Cualquier otro ID será tratado como un cliente válido y limpio.
        return new CustomerDTO(id, "Cliente Ejemplar", 0); // 0 = Sin multas
    }
}