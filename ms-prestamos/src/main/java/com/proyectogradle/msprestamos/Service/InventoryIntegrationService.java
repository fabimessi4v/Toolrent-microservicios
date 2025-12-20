package com.proyectogradle.msprestamos.Service; // package declaration for the service classes

import com.proyectogradle.msprestamos.Entities.Loans;
import org.springframework.beans.factory.annotation.Autowired; // import for the @Autowired annotation
import org.springframework.beans.factory.annotation.Value; // import for the @Value annotation
import org.springframework.stereotype.Service; // import for the @Service annotation
import org.springframework.web.client.RestTemplate; // import for RestTemplate HTTP client
import com.proyectogradle.msprestamos.DTO.InventoryMovementDTO; // import for the DTO used in registerMovement

@Service // Marks this class as a Spring service component
public class InventoryIntegrationService { // Service that integrates with the inventory microservice

    @Autowired // Injects a RestTemplate bean for making HTTP calls
    private RestTemplate restTemplate; // RestTemplate used to call the inventory service
    @Value("${services.inventory.url}")
    private String inventoryServiceUrl;

    public boolean checkAvailability(String toolId) { // Checks if a tool is available in inventory
        try { // Start try block to catch call failures
            // Construye: http://localhost:8081/api/tools/{id}/availability
            String url = inventoryServiceUrl + toolId + "/availability"; // Build the availability endpoint URL
            System.out.println(">>>> MS_PRESTAMOS: Intentando llamar a: " + url);
            Boolean response = restTemplate.getForObject(url, Boolean.class); // Perform GET and expect a Boolean response
            System.out.println(">>>> MS_PRESTAMOS: Respuesta recibida: " + response);
            return response != null && response; // Return true only if the response is non-null and true
        } catch (Exception e) { // If any exception occurs during the HTTP call
            System.err.println(">>>> MS_PRESTAMOS: Error en la llamada HTTP: " + e.getMessage());
            return false; // Treat errors as not available and return false
        } // end catch
    } // end checkAvailability

    public void registerMovement(String toolId, int quantity, String type, String loansId) { // Registers an inventory movement
        String url = inventoryServiceUrl + "movement"; // Build the endpoint URL for registering movements
        InventoryMovementDTO request = new InventoryMovementDTO(toolId, quantity, type, loansId); // Create DTO payload for the request

        // POST al otro microservicio
        restTemplate.postForEntity(url, request, Void.class); // Send POST to inventory service; response body ignored
    } // end registerMovement
} // end InventoryIntegrationService
