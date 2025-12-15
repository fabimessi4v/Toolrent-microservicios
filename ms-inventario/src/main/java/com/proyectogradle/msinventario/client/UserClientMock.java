package com.proyectogradle.msinventario.client;

import com.proyectogradle.msinventario.DTO.UserDTO;
import org.springframework.stereotype.Component;

@Component // ¡Importante! Esto le dice a Spring: "Usa esta clase cuando pidan un UserClient"
public class UserClientMock implements com.proyectogradle.msinventario.client.UserClient {

    @Override
    public UserDTO getCurrentUserAuthenticated() {
        // Simulamos la respuesta que nos daría el otro microservicio
        UserDTO fakeUser = new UserDTO();
        fakeUser.setId("5310f2ee-81f8-11f0-8b09-00155d4a2cda");
        fakeUser.setUsername("admin1");

        // CAMBIA ESTO PARA PROBAR TUS REGLAS:
        // Pon "ADMIN" para que te deje borrar.
        // Pon "USER" para que lance la excepción de seguridad.
        fakeUser.setRole("ADMIN");

        return fakeUser;
    }
}