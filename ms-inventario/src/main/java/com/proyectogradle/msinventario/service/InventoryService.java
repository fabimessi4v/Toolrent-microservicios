package com.proyectogradle.msinventario.service;

import com.proyectogradle.msinventario.DTO.UserDTO;
import com.proyectogradle.msinventario.client.UserClient; // Asegúrate de crear este paquete
import com.proyectogradle.msinventario.entity.Kardex;
import com.proyectogradle.msinventario.entity.Tools;
import com.proyectogradle.msinventario.repository.KardexRepository;
import com.proyectogradle.msinventario.repository.ToolsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class InventoryService {
    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);
    private final ToolsRepository toolsRepository;
    private final KardexRepository kardexRepository;
    private final UserClient userClient; // Ahora sí está inyectado

    public InventoryService(ToolsRepository toolsRepository, KardexRepository kardexRepository, UserClient userClient) {
        this.toolsRepository = toolsRepository;
        this.kardexRepository = kardexRepository;
        this.userClient = userClient;
    }

    // --- CREATE TOOL ---
    @Transactional
    public Tools createTool(Tools tool) {
        if (tool.getId() == null) {
            tool.setId(UUID.randomUUID().toString());
        }

        Tools savedTool = toolsRepository.save(tool);

        // Llamada al cliente (que simularemos)
        UserDTO currentUser = userClient.getCurrentUserAuthenticated();

        if (currentUser == null) {
            throw new IllegalStateException("No se pudo obtener el usuario del MS de Auth");
        }

        log.info("Usuario obtenido: ID={}, Role={}", currentUser.getId(), currentUser.getRole());

        createKardexEntry(savedTool, currentUser.getId(), null, "Registro nuevo", 1, "Alta inicial");

        return savedTool;
    }

    // --- DELETE TOOL ---
    @Transactional
    public boolean deleteTool(String id) {
        UserDTO currentUser = userClient.getCurrentUserAuthenticated();

        if (!"ADMIN".equalsIgnoreCase(currentUser.getRole())) {
            throw new SecurityException("Solo los administradores pueden dar de baja herramientas");
        }

        try {
            if (toolsRepository.existsById(id)) {
                toolsRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error("Error eliminando herramienta", e);
            return false;
        }
    }

    private void createKardexEntry(Tools tool, String userId, String loanId, String type, int qty, String comments) {
        Kardex kardex = new Kardex();
        kardex.setId(UUID.randomUUID().toString());
        kardex.setTool(tool);
        kardex.setUserId(userId);
        kardex.setLoanId(loanId);
        kardex.setType(type);
        kardex.setQuantity(qty);
        kardex.setMovementDate(LocalDate.now());
        kardex.setComments(comments);
        kardexRepository.save(kardex);
    }

    // --- GET ALL TOOLS ---
    public List<Tools> getAllTools() {
        log.info("MS-Inventory: Obteniendo lista completa de herramientas");
        return toolsRepository.findAll();
    }
}