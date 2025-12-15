package com.proyectogradle.msinventario.client;

import com.proyectogradle.msinventario.DTO.UserDTO;

public interface UserClient {
    // Definimos qué método necesitamos llamar
    UserDTO getCurrentUserAuthenticated();
}