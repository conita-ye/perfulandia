package com.perfulandia.msvc.sucursal.controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.perulandia.msvc.sucursal.model.entities.Sucursal;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SucursalControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllSucursalesWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/sucursales", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int sucursalCount = documentContext.read("$.length()");
        assertThat(sucursalCount).isGreaterThanOrEqualTo(0);
    }

    // CREAR UNA SUCURSAL
    @Test
    public void shouldReturnASucursalWhenFindById() {
        Sucursal newSucursal = new Sucursal();
        newSucursal.setNombre("Sucursal Test");
        newSucursal.setDireccion("Calle Test 123");
        newSucursal.setCiudad("Ciudad Test");

        ResponseEntity<String> createResponse = restTemplate.postForEntity("/api/v1/sucursales", newSucursal, String.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        DocumentContext createContext = JsonPath.parse(createResponse.getBody());
        Number sucursalId = createContext.read("$.idSucursal");

        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/sucursales/" + sucursalId, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.idSucursal");
        assertThat(id).isEqualTo(sucursalId);
        String nombre = documentContext.read("$.nombre");
        assertThat(nombre).isEqualTo("Sucursal Test");
        String direccion = documentContext.read("$.direccion");
        assertThat(direccion).isEqualTo("Calle Test 123");
        String ciudad = documentContext.read("$.ciudad");
        assertThat(ciudad).isEqualTo("Ciudad Test");
    }

    @Test
    public void shouldReturnNotFoundForUnknownId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/sucursales/999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number status = documentContext.read("$.status");
        assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewSucursal() {
        Sucursal newSucursal = new Sucursal();
        newSucursal.setNombre("Nueva Sucursal");
        newSucursal.setDireccion("Nueva Dirección 456");
        newSucursal.setCiudad("Nueva Ciudad");

        ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/sucursales", newSucursal, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idSucursal = documentContext.read("$.idSucursal");
        assertThat(idSucursal).isNotNull();

        String nombre = documentContext.read("$.nombre");
        assertThat(nombre).isEqualTo("Nueva Sucursal");
    }

    @Test
    @DirtiesContext
    public void shouldUpdateAnExistingSucursal() {
        Sucursal newSucursal = new Sucursal();
        newSucursal.setNombre("Sucursal Original");
        newSucursal.setDireccion("Dirección Original");
        newSucursal.setCiudad("Ciudad Original");

        ResponseEntity<String> createResponse = restTemplate.postForEntity("/api/v1/sucursales", newSucursal, String.class);
        DocumentContext createContext = JsonPath.parse(createResponse.getBody());
        Number sucursalId = createContext.read("$.idSucursal");

        Sucursal updatedSucursal = new Sucursal();
        updatedSucursal.setNombre("Sucursal Actualizada");
        updatedSucursal.setDireccion("Dirección Actualizada");
        updatedSucursal.setCiudad("Ciudad Actualizada");
        HttpEntity<Sucursal> request = new HttpEntity<>(updatedSucursal);
        ResponseEntity<String> response = restTemplate.exchange("/api/v1/sucursales/" + sucursalId,
                HttpMethod.PUT, request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        String nombre = documentContext.read("$.nombre");
        assertThat(nombre).isEqualTo("Sucursal Actualizada");
    }

    @Test
    @DirtiesContext
    public void shouldDeleteASucursal() {

        Sucursal newSucursal = new Sucursal();
        newSucursal.setNombre("Sucursal a Eliminar");
        newSucursal.setDireccion("Dirección a Eliminar");
        newSucursal.setCiudad("Ciudad a Eliminar");

        ResponseEntity<String> createResponse = restTemplate.postForEntity("/api/v1/sucursales", newSucursal, String.class);
        DocumentContext createContext = JsonPath.parse(createResponse.getBody());
        Number sucursalId = createContext.read("$.idSucursal");

        ResponseEntity<String> deleteResponse = restTemplate.exchange("/api/v1/sucursales/" + sucursalId,
                HttpMethod.DELETE, null, String.class);

        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        ResponseEntity<String> getResponse = restTemplate.getForEntity("/api/v1/sucursales/" + sucursalId, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldFindSucursalesByNombre() {
        Sucursal newSucursal = new Sucursal();
        newSucursal.setNombre("Centro Comercial");
        newSucursal.setDireccion("Av. Centro 123");
        newSucursal.setCiudad("Santiago");

        restTemplate.postForEntity("/api/v1/sucursales", newSucursal, String.class);

        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/sucursales/search/nombre/Centro", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray nombres = documentContext.read("$[*].nombre");
        assertThat(nombres).isNotEmpty();
    }

    @Test
    public void shouldFindSucursalesByCiudad() {

        Sucursal newSucursal = new Sucursal();
        newSucursal.setNombre("Sucursal Valparaíso");
        newSucursal.setDireccion("Puerto Principal 456");
        newSucursal.setCiudad("Valparaíso");

        restTemplate.postForEntity("/api/v1/sucursales", newSucursal, String.class);


        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/sucursales/search/ciudad/Valparaíso", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray ciudades = documentContext.read("$[*].ciudad");
        assertThat(ciudades).isNotEmpty();
    }
}
