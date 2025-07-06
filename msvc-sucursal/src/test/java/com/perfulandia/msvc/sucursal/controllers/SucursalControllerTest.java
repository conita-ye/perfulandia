package com.perfulandia.msvc.sucursal.controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.perulandia.msvc.sucursal.model.entities.Sucursal;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SucursalControllerTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllSucursalesWhenListIsRequested(){
        ResponseEntity<String> response = restTemplate.getForEntity("api/v1v1/sucursales", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int sucursalCount = documentContext.read("$.length()");
        assertThat(sucursalCount).isEqualTo(2);

        JSONArray ids = documentContext.read("$.[*].id");
        assertThat(ids).containsExactlyInAnyOrder(1,2);

        JSONArray names = documentContext.read("$..nombre");
        assertThat(names).containsExactlyInAnyOrder("");

    }

   @Test
    public void shouldReturnASucursalWhenFindById(){
        ResponseEntity<String> response = restTemplate.getForEntity("api/v1/sucursales/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int sucursalCount = documentContext.read("$.length()");
        assertThat(sucursalCount).isEqualTo(1);

        String nombre = documentContext.read("$.nombre");
        assertThat(nombre).isEqualTo("Sucursal 1");

        String direccion = documentContext.read("$.direccion");
        assertThat(direccion).isEqualTo("Calle 1 #1");

        String telefono = documentContext.read("$.telefono");
        assertThat(telefono).isEqualTo("123456789");

        String email = documentContext.read("$.email");

   }
   
    @Test
    public void shouldReturnAnMedicoWithUnknownId(){
        ResponseEntity<String> response = restTemplate.getForEntity("api/v1/sucursales/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number status = documentContext.read("$.status");
        assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewMedico(){
        Sucursal sucursal = new Sucursal();
        ResponseEntity<String> response = restTemplate.postForEntity("api/v1/sucursales/1",sucursal, String.class);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idMedico = documentContext.read("$.idMedico");
        assertThat(idMedico).isEqualTo(3);
    }
}